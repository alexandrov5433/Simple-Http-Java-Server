package com.simplehttpserver.httpserver;

import com.simplehttpserver.httpserver.config.Configuration;
import com.simplehttpserver.httpserver.config.ConfigurationManager;
import com.simplehttpserver.httpserver.core.ServerListenerThread;
import com.simplehttpserver.httpserver.util.ServerLogger;

import java.io.IOException;

/*
* Driver Class for Http server.
* */
public class HttpServer {

    public static void main(String[] args) {
        ServerLogger.logInfos("Server Starting!");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        ServerLogger.logInfos("Using port: " + conf.getPort());
        ServerLogger.logInfos("Using WebRoot: " + conf.getWebroot());

        try {
            ServerListenerThread serverListenerThread = new ServerListenerThread(conf.getPort(), conf.getWebroot());
            serverListenerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
            //TODO handle error
        }

    }
}