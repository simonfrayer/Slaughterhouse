package via.sdj3.slaughterhouse.gRPCServer;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class gRPCServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder
                .forPort(8060)
                .addService(new SlaughterhouseImpl()).build();

        server.start();
        server.awaitTermination();
    }
}
