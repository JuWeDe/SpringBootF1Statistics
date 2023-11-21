package ru.badin.springbootf1webservice.gRPC;

import io.grpc.stub.StreamObserver;
import ru.badin.springbootf1webservice.grpc.RacerOuterClass;
import ru.badin.springbootf1webservice.grpc.RacerServiceGrpc;

public class RacerGrpcServiceImpl extends RacerServiceGrpc.RacerServiceImplBase {

    @Override
    public void getNumber (RacerOuterClass.Racer request, StreamObserver<RacerOuterClass.Response> responseObserver) {
        RacerOuterClass.Response response = RacerOuterClass.Response.newBuilder()
                .setStr(request.getName() + " probably will be champion. Response message. All ok")
                .build();
        System.out.println("Received Racer: \n" + request.getName() + "\n" + request.getChampionships() + "\n" + request.getPoints()+ "\n" + request.getWins() + "\n" + request.getCarId());
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

