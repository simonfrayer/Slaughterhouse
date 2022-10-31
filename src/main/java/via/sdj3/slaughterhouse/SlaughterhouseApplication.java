package via.sdj3.slaughterhouse;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import via.sdj3.slaughterhouse.protobuf.SlaughterhouseServerGrpc;

@SpringBootApplication
public class SlaughterhouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SlaughterhouseApplication.class, args);

    }

}
