package t.education.starter.init;

import org.apache.commons.logging.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.logging.DeferredLogFactory;
import org.springframework.core.env.ConfigurableEnvironment;
import t.education.starter.exception.StartupException;


public class StarterPostProcessor implements EnvironmentPostProcessor {

    private final Log log;

    public StarterPostProcessor(DeferredLogFactory logFactory) {
        this.log = logFactory.getLog(StarterPostProcessor.class);
    }

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        log.info("Call StarterPostProcessor");
        String enablePropertyValue1 = environment.getProperty("starter.enabled");
        String enablePropertyValue2 = environment.getProperty("starter.handlerEnabled");
        boolean isBolValue1 = Boolean.TRUE.toString().equals(enablePropertyValue1) ||
                Boolean.FALSE.toString().equals(enablePropertyValue1);
        boolean isBolValue2 = Boolean.TRUE.toString().equals(enablePropertyValue2) ||
                Boolean.FALSE.toString().equals(enablePropertyValue2);
        if (!isBolValue1 || !isBolValue2) {
            throw new StartupException("Property 'starter.enabled' exception");
        }
    }
}
