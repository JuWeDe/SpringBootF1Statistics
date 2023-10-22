package ru.badin.springbootf1webservice.Services;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.badin.springbootf1webservice.DTO.CarDto;
import ru.badin.springbootf1webservice.HAL.HAL;
import ru.badin.springbootf1webservice.model.Car;
import ru.badin.springbootf1webservice.repostory.CarRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CarDto getCarById(Long id) {
        Optional<Car> carOptional = carRepository.findById(id);
        return carOptional.map(this::mapToDTO).orElse(null);
    }

    @Override
    public void createCar(CarDto carDTO) {
        Car car = mapToEntity(carDTO);
        carRepository.save(car);
    }


    @Override
    public List<CarDto> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return cars.stream().map(this::mapToDTO).collect(Collectors.toList());
    }


    @Override
    public void updateCar(Long id, CarDto carDTO) {
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isPresent()) {
            Car car = mapToEntity(carDTO);
            car.setId(id);
            carRepository.save(car);
        } else {
            throw new RuntimeException("Car not found: " + id);
        }
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }


    private CarDto mapToDTO(Car car) {
        return modelMapper.map(car, CarDto.class);
    }

    private Car mapToEntity(CarDto carDTO) {
        return modelMapper.map(carDTO, Car.class);
    }


    @Override
    public Map<String, Object> getAllCarsHal(int index, int count) {
        Page<CarDto> carPage = getAllCars(index, count);
        String baseUrl = "/api/cars";
        int pageNumber = carPage.getNumber();
        int pageSize = carPage.getSize();
        int total = carPage.getTotalPages();

        List<Map<String, Object>> embeddedRacers = carPage.getContent().stream()
                .map(this::createCarResource)
                .toList();

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("_embedded", Collections.singletonMap("racers", embeddedRacers));
        response.put("_links", HAL.paginateAsDictionary(baseUrl, pageNumber, pageSize, total));

        return response;
    }

    private Map<String, Object> createCarResource(CarDto car) {
        Map<String, Object> carResource = new LinkedHashMap<>();
        carResource.put("_links", createCarLinks(car));
        carResource.put("id", car.getId());
        carResource.put("name", car.getName());
        carResource.put("carNumber", car.getCarNumber());
        carResource.put("engine", car.getEngine());
        carResource.put("hp", car.getHp());

        return carResource;
    }

    private Map<String, Object> createCarLinks(CarDto car) {
        String baseUrl = "/api/cars";
        Long carId = car.getId();
        Map<String, Object> links = new LinkedHashMap<>();
        links.put("self", Collections.singletonMap("href", baseUrl + "/" + carId));
        links.put("team", Collections.singletonMap("href", "api/teams/" + car.getTeamId()));
        return links;
    }

    public Page<CarDto> getAllCars(int index, int count) {
        PageRequest pageable = PageRequest.of(index, count);
        Page<Car> carPage = carRepository.findAll(pageable);
        List<CarDto> carDtos = carPage.getContent().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(carDtos, pageable, carPage.getTotalElements());
    }
}
