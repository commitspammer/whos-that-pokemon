package WhosThatPokemon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    public static void main(String[] args) throws IOException {
        int port = 8080;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server running on port: " + port);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected!");

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String s;
            while ((s = reader.readLine()) != null) {
                System.out.println(s);
                if (s.isEmpty())
                    break;
            }

            HtmlGenerator generator = new HtmlGenerator();
            OutputStreamWriter writer = new OutputStreamWriter(clientSocket.getOutputStream());
            writer.write("HTTP/1.1 200 OK\r\n");
            writer.write("\r\n");
            writer.write(generator.generateHtml());
            writer.write("\r\n");
            //writer.write(fetcher.fetch(1));
            writer.write("\r\n\r\n");
            writer.flush();
            writer.close();

            // System.out.println("Trying to send response");
            // OutputStream writer = clientSocket.getOutputStream();
            // writer.write("HTTP/1.1 200 OK\r\n".getBytes());
            // writer.write("Date: Mon, 27 Jun 2009 12:28:53 GMT\r\n".getBytes());
            // writer.write("Server: Apache/2.2.14 (Win32)\r\n".getBytes());
            // writer.write("Last-Modified: Wed, 22 Jul 2009 19:15:56\r\n".getBytes());
            // writer.write("Content-Length: 88\r\n".getBytes());
            // writer.write("Content-Type: text/html\r\n".getBytes());
            // writer.write("Connection: Closed\r\n".getBytes());
            // writer.write("\r\n".getBytes());
            // writer.write("<html><body><h1>Hello, World!</h1></body></html>".getBytes());
            // writer.write("\r\n\r\n".getBytes());
            // writer.flush();

            System.out.println("Connection closed!");
            reader.close();
            writer.close();
            clientSocket.close();
        }

    }

}
