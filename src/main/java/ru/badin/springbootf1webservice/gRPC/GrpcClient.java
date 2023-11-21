package ru.badin.springbootf1webservice.gRPC;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import ru.badin.springbootf1webservice.grpc.RacerOuterClass;
import ru.badin.springbootf1webservice.grpc.RacerServiceGrpc;

public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9000)
                .usePlaintext()
                .build();

        RacerServiceGrpc.RacerServiceBlockingStub client = RacerServiceGrpc.newBlockingStub(channel);

        RacerOuterClass.Racer request = RacerOuterClass.Racer.newBuilder()
                .setId(1)
                .setName("John")
                .setWins(10)
                .setChampionships(2)
                .setPoints(100.5F)
                .setCarId(100)
                .setTeamId(200)
                .build();

        RacerOuterClass.Response response = client.getNumber(request);
        System.out.println("\nReceived response: " + response.getStr() + "\n");

        channel.shutdown();
    }
}
