package via.sdj3.slaughterhouse.gRPCServer;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import via.sdj3.slaughterhouse.repository.AnimalRepository;
import via.sdj3.slaughterhouse.repository.ProductRepository;

import java.io.IOException;

public class gRPCServer {
    public static void main(String[] args) throws IOException, InterruptedException {
         AnimalRepository animalRepository;
         ProductRepository productRepository;

        Server server = ServerBuilder
                .forPort(8060)
                .addService(new SlaughterhouseImpl()).build();

        server.start();
        server.awaitTermination();
    }
}
