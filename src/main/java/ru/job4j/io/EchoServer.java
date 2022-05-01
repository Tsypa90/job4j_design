package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());
    public static void main(String[] args)  {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    var line = in.readLine();
                    String value = "What?";
                    if (line.contains("?msg=Hello")) {
                        value = "Hello!";
                    } else if (line.contains("?msg=Exit")) {
                        value = "Server closed!";
                        server.close();
                    }
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write(value.getBytes());
                    out.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("Exception in log", e);
        }
    }
}