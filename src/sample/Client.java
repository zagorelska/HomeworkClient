package sample;

import java.io.*;
import java.net.Socket;

public class Client implements Runnable {
    private Socket soc;
    private String ansv;
    private Thread t;

    public Client(Socket soc, String ansv) {
        super();
        this.soc = soc;
        this.ansv = ansv;
        t = new Thread(this);
        t.start();
    }

    public void run() {
        try (InputStream is = soc.getInputStream(); OutputStream os = soc.getOutputStream();
             PrintWriter pw = new PrintWriter(os)) {
            byte[] rec1 = new byte[is.available()];
            is.read(rec1);
            String response = "HTTP/1.1 200 OK\r\n" + "Server: My_Server\r\n" + "Content-Type: text/html\r\n"
                    + "Content-Length: " + "\r\n" + "Connection: close\r\n\r\n";
            pw.print(response + ansv);
            pw.flush();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

}