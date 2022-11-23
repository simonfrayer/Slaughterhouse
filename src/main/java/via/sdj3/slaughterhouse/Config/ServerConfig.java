package via.sdj3.slaughterhouse.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import via.sdj3.slaughterhouse.gRPCServer.SlaughterhouseImpl;

@Configuration
public class ServerConfig {
    @Bean public SlaughterhouseImpl getSlaughterHouseImpl(){return new SlaughterhouseImpl();}
}
