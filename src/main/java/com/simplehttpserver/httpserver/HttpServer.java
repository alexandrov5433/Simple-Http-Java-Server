package com.simplehttpserver.httpserver;

import com.simplehttpserver.httpserver.config.Configuration;
import com.simplehttpserver.httpserver.config.ConfigurationManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
* Driver Class for Http server.
* */
public class HttpServer {
    public static void main(String[] args) {
        System.out.println("Server Starting!");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        System.out.println("Using port: " + conf.getPort());
        System.out.println("Using WebRoot: " + conf.getWebroot());

        try {
            ServerSocket serverSocket = new ServerSocket(conf.getPort());
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            //read
            //write
            String html = "<html><head><title>Simple Http Server</title><body><h1>This is a simple response. :)</h1></body></head></html>";
            final String CRLF = "\n\r"; //ASCII 13, 10
            String response = "HTTP/2.0 200 OK" + CRLF +
                    "Content-Length: " + html.getBytes().length + CRLF +
                        CRLF +
                        html +
                        CRLF + CRLF;

            outputStream.write(response.getBytes());


            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}