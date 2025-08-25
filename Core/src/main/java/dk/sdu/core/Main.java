package dk.sdu.core;

import dk.sdu.cbse.common.services.SplitProvider;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import dk.sdu.cbse.common.util.SplitProviderLocator;

import java.util.List;

public class Main extends Application {
    private AnnotationConfigApplicationContext context;

    @Override
    public void init() {
        context = new AnnotationConfigApplicationContext(SpringLoader.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println("Spring bean: " + beanName);
        }

        List<SplitProvider> splitProviders = SplitProviderLocator.INSTANCE.locateAll(SplitProvider.class);
        for (SplitProvider splitProvider : splitProviders) {
            System.out.println("Loaded provider: " + splitProvider.helloProvder());
        }

        Logic logic = context.getBean(Logic.class);
        logic.start(stage);
        logic.render();
    }

    @Override
    public void stop() {
        if (context != null) {
            context.close();
        }
    }

    public static void main(String[] args) { launch(args); }
}
