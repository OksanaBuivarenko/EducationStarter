package t.education.starter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "starter")
public class StarterProperties {

    private boolean enabled;

    private boolean handlerEnabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isHandlerEnabled() {
        return handlerEnabled;
    }

    public void setHandlerEnabled(boolean handlerEnabled) {
        this.handlerEnabled = handlerEnabled;
    }

}
