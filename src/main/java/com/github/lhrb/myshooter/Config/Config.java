package com.github.lhrb.myshooter.Config;

import java.util.Map;
import java.io.File;
import java.util.HashMap;

public class Config {
  private Map config;
  final String configFile = "config.yml";

  /**
    * Default Konstruktor der Config zum setzen der Einstellungen
  */
  public Config () {
    config = new HashMap();
    config.put("gameName", "myShooter");
    config.put("resolution_width", 1024);
    config.put("resolution_height", 768);
  }

  /**
    * Gibt die komplette Config als map zurück
    * @return gibt die als Private abgelegte Map zurück
  */
  public Map getConfig () {
    return config;
  }

  /**
    * Gibt die angeforderte Einstellung zurück
    * @param key Die Bezeichnung der Einstellung
    * @return Gibt die angeforderte Einstellung als Object zurück
  */
  public Object getSetting (String key) {
    return config.get(key);
  }

  /**
    * Setzt die angegebene Einstellung
    * @param key Die Bezeichnung der Einstellung
    * @param value Der Wert der Einstellung
  */  public void setSetting (String key, String value) {
    config.put(key, value);
  }
}
