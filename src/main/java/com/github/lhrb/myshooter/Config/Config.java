package com.github.lhrb.myshooter.Config;
import java.util.Map;
import java.io.File;
import java.util.HashMap;


public class Config {
  Map config;
  final String configFile = "config.yml";

  public Config () {
    config = new HashMap();
    config.put("resolution_width", 1024);
    config.put("resolution_height", 768);
  }

  public Map<String, Object> getConfig () {
    return config;
  }

  public Object getSetting (String key) {
    return config.get(key);
  }

  // sets a setting inside the config
  public void setSetting (String key, String value) {
    config.put(key, value);
  }
}
