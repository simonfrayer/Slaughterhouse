package via.sdj3.slaughterhouse.gRPCServer;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import via.sdj3.slaughterhouse.repository.AnimalRepository;
import via.sdj3.slaughterhouse.repository.AnimalRepositoryImpl;

import java.io.IOException;

public class gRPCServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        AnimalRepository repository = new AnimalRepositoryImpl();
        Server server = ServerBuilder
                .forPort(8060)
                .addService(new SlaughterhouseImpl(repository)).build();

        server.start();
        server.awaitTermination();
    }
}
