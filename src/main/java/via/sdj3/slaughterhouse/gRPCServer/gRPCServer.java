package via.sdj3.slaughterhouse.gRPCServer;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import via.sdj3.slaughterhouse.RestServer.service.AnimalServiceImpl;
import via.sdj3.slaughterhouse.repository.AnimalRepository;
import via.sdj3.slaughterhouse.repository.ProductRepository;

import java.io.IOException;

@GRpcService
public class gRPCServer {
    private static SlaughterhouseImpl slaughterhouse;
    public static void main(String[] args) throws IOException, InterruptedException {


        Server server = ServerBuilder
                .forPort(8060)
                .addService(slaughterhouse)
                .build();

        server.start();
        server.awaitTermination();
    }
}
