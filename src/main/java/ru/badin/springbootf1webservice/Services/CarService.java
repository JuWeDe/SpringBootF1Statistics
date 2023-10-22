package ru.badin.springbootf1webservice.Services;

import ru.badin.springbootf1webservice.DTO.CarDto;

import java.util.List;
import java.util.Map;


public interface CarService {
    CarDto getCarById(Long id);

    List<CarDto> getAllCars();

    void createCar(CarDto carDto);

    void updateCar(Long id, CarDto carDto);

    void deleteCar(Long id);

    Map<String, Object> getAllCarsHal(int index, int count);
}
//    private final CarRepository carRepository;
//    private CarMapper carMapper;
//
//    public CarService(CarRepository carRepository) {
//        this.carRepository = carRepository;
//    }
//
//
//    public CarDto createCar(CarDto CarDto) {
//        Car car = new Car();
//        car.setName(CarDto.getName());
//        car.setEngine(CarDto.getEngine());
//        car.setHp(CarDto.getHp());
//        car.setCarNumber(CarDto.getCarNumber());
//        car.setTeam(new Team(CarDto.getTeamId()));
//        car = carRepository.save(car);
//        return new CarDto(car.getId(), car.getName(), car.getEngine(), car.getHp(),
//                car.getCarNumber(), car.getTeam().getId());
//    }
//
//    public List<CarDto> getAllCars() {
//        List<Car> cars = carRepository.findAll();
//        return cars.stream().map(car -> new CarDto(car.getId(), car.getName(), car.getEngine(), car.getHp(), car.getCarNumber()))
//                .collect(Collectors.toList());
//    }
//
//
//
//    public void deleteCar(Long id) {
//        carRepository.deleteById(id);
//    }
//
//    public CarDto updateCar(Long id, CarDto carDto) {
//        Optional<Car> optionalCar = carRepository.findById(id);
//        if (optionalCar.isPresent()) {
//            Car car = optionalCar.get();
//            car.setName(carDto.getName());
//            car.setEngine(carDto.getEngine());
//            car.setHp(carDto.getHp());
//            car.setCarNumber(carDto.getCarNumber());
//            Car updatedCar = carRepository.save(car);
//            return new CarDto(updatedCar.getId(), updatedCar.getName(), updatedCar.getEngine(),
//                    updatedCar.getHp(), updatedCar.getCarNumber());
//        }
//        return null;
//    }
//
//    public CarDto getCarById(Long id) {
//        Optional<Car> optionalCar = carRepository.findById(id);
//        return optionalCar.map(car -> carMapper.toDto(car)).orElse(null);
//    }
//
//
//}
//
