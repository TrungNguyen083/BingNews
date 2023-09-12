package org.example.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.controller.BingNewsController;
import org.example.model.SportInfo;

import java.io.IOException;
import java.io.OutputStream;

public class SportInfoHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("GET".equals(exchange.getRequestMethod())) {
            // Create ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Create your SportInfo object (you can replace this with your own data)
            BingNewsController bingNewsController = new BingNewsController();
            SportInfo sportInfo = null;
            try {
                sportInfo = bingNewsController.getSportInfoService().getSportInfo();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            // Convert SportInfo object to JSON
            String json = objectMapper.writeValueAsString(sportInfo);

            // Set response headers
            exchange.getResponseHeaders().set("Content-Type", "application/json");

            // Send the JSON as a response
            exchange.sendResponseHeaders(200, json.length());
            try (OutputStream os = exchange.getResponseBody()) {
                byte[] jsonDataBytes = json.getBytes();
                int chunkSize = 1024; // Adjust the chunk size as needed
                int offset = 0;

                while (offset < jsonDataBytes.length) {
                    int bytesToWrite = Math.min(chunkSize, jsonDataBytes.length - offset);
                    os.write(jsonDataBytes, offset, bytesToWrite);
                    os.flush();
                    offset += bytesToWrite;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Return a 405 Method Not Allowed response for non-GET requests
            exchange.sendResponseHeaders(405, -1);
        }
    }
}
