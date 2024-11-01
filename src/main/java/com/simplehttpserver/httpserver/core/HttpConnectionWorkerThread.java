package com.simplehttpserver.httpserver.core;

import com.simplehttpserver.httpserver.util.ServerLogger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpConnectionWorkerThread extends Thread{

    private Socket socket;

    public HttpConnectionWorkerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
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

            ServerLogger.logInfos(" -> Process finished.");
        } catch (IOException e) {
            ServerLogger.logError("Communication problem in worker: ", e);
            //TODO handle error
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
