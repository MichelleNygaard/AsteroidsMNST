package dk.sdu.core;

import java.util.List;
import java.util.ServiceLoader;

import dk.sdu.background.BackgroundSPI;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGameDataProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import static java.util.stream.Collectors.toList;

@Configuration
public class SpringLoader {

    @Bean
    public Logic game(){
        return new Logic(gamePluginServices(), entityProcessingServiceList(), postEntityProcessingServices(),
                backgroundService(), gameDataProcessingService());
    }

    @Bean
    public List<IEntityProcessingService> entityProcessingServiceList(){
        return ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public List<IGamePluginService> gamePluginServices() {
        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public List<IPostEntityProcessingService> postEntityProcessingServices() {
        return ServiceLoader.load(IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public List<BackgroundSPI> backgroundService() {
        return ServiceLoader.load(BackgroundSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public List<IGameDataProcessingService> gameDataProcessingService() {
        return ServiceLoader.load(IGameDataProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
