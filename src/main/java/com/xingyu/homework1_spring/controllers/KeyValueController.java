package com.xingyu.homework1_spring.controllers;


import com.xingyu.homework1_spring.services.KeyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class KeyValueController {
    private final KeyValueService keyValueService;

    @Autowired
    public KeyValueController(KeyValueService keyValueService) {
        this.keyValueService = keyValueService;
    }

    public void startServer(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);
                new Thread(new ClientHandler(clientSocket, keyValueService)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;
        private final KeyValueService keyValueService;

        public ClientHandler(Socket clientSocket, KeyValueService keyValueService) {
            this.clientSocket = clientSocket;
            this.keyValueService = keyValueService;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Received from client: " + inputLine);
                    String[] parts = inputLine.split(" ");
                    if (parts.length < 2) {
                        out.println("Invalid command");
                        continue;
                    }
                    switch (parts[0]) {
                        case "PUT":
                            if (parts.length < 3) {
                                out.println("Invalid PUT command");
                                continue;
                            }
                            out.println(keyValueService.put(parts[1], parts[2]));
                            break;
                        case "GET":
                            out.println(keyValueService.get(parts[1]));
                            break;
                        case "DELETE":
                            out.println(keyValueService.delete(parts[1]));
                            break;
                        case "EXIT":
                            clientSocket.close();
                            return;
                        default:
                            out.println("Invalid command");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}