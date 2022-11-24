package via.sdj3.slaughterhouse.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import via.sdj3.slaughterhouse.RestServer.gRPCCLient.ServerInterface;
import via.sdj3.slaughterhouse.RestServer.gRPCCLient.gRPCClient;
import via.sdj3.slaughterhouse.RestServer.service.AnimalServiceImpl;
import via.sdj3.slaughterhouse.RestServer.service.ProductServiceImpl;
import via.sdj3.slaughterhouse.gRPCServer.SlaughterhouseImpl;

@Configuration
public class ServerConfig {
    @Bean public ProductServiceImpl getProductServiceImpl(){return new ProductServiceImpl();}
    @Bean public AnimalServiceImpl getAnimalServiceImpl(){return new AnimalServiceImpl();}

}
