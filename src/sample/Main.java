package sample;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        Integer idRequest = 0;
        String answer = "";
        String osName = System.getProperty("os.name");
        String osVer = System.getProperty("os.version");
        String osArch = System.getProperty("os.arch");
        answer += "<html><head><title></title> <meta charset='utf-8'></head><body><p>" +
                "<tr> Name of the OS: " + osName + "</tr><br/>"
                + "<tr> Version of the OS: " + osVer + "</tr><br/>"
                + "<tr> Architecture of the OS: " + osArch + "</tr></p><br/>";
        try (ServerSocket soc = new ServerSocket(8080)) {
            for (;;) {
                idRequest++;
                answer += "<tr>request: " + idRequest + "</tr><br/></body></html>";
                Socket clisoc = soc.accept();
                Client cli = new Client(clisoc, answer);
            }
        } catch (IOException e) {
            System.out.println("Error to server Socket Open!!!");
        }
    }
}
