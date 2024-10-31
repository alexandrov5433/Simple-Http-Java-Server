package com.simplehttpserver.httpserver.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.simplehttpserver.httpserver.util.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationManager {
    private static ConfigurationManager myConfigurationManager;
    private static Configuration myCurrentConfiguration;

    private ConfigurationManager() {
    }

    public static ConfigurationManager getInstance() {
        if (myConfigurationManager == null) {
            myConfigurationManager = new ConfigurationManager();
        }
        return myConfigurationManager;
    }

    /*
    * Load config file from path.
    */
    public void loadConfigurationFile(String filePath) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationExeption(e);
        }
        StringBuffer sb = new StringBuffer();
        int i;
        try {
            while ((i = fileReader.read()) != -1) {
                sb.append((char)i);
            }
        } catch (IOException e) {
            throw new HttpConfigurationExeption(e);
        }
        JsonNode conf;
        try {
            conf = Json.parse(sb.toString());
        } catch (IOException e) {
            throw new HttpConfigurationExeption("Error parsing the Configuration file.", e);
        }
        try {
            myCurrentConfiguration = Json.fromJson(conf, Configuration.class);
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationExeption("Error parsing the Configuration file internally.", e);
        }
    }
    /*
    * Returns current loaded config.
    * */
    public Configuration getCurrentConfiguration() {
        if (myCurrentConfiguration == null) {
            throw new HttpConfigurationExeption("No current configuration is set.");
        }
        return myCurrentConfiguration;
    }

}
