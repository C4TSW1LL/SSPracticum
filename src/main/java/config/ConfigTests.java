package config;

import org.aeonbits.owner.Config;
@Config.Sources({"classpath:config.properties"})
public interface ConfigTests extends Config {

    String url();
}
