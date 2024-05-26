/*
RDTP/1.0 Upload Client Socket Program
Author: Mert Mermer / 39598285414
Also available at: https://github.com/Winnerxl/BDTP-Sockets
 */

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter server IP address. Press ENTER to use default value \"localhost\" :");
        String serverAddress = scanner.nextLine();

        int defaultPort = 14350;
        System.out.print("Enter server port number. Press ENTER to use default value \"14350\" :");
        String portInput = scanner.nextLine();
        int port = portInput.isEmpty() ? defaultPort : Integer.parseInt(portInput);

        System.out.print("Enter the path of the file to upload (Enter without using quotation marks): ");
        String filePath = scanner.nextLine();

        try (Socket socket = new Socket(serverAddress, port);
             FileInputStream fis = new FileInputStream(filePath);
             DataOutputStream dos = new DataOutputStream(socket.getOutputStream())) {

            byte[] buffer = new byte[256];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                dos.write(buffer, 0, bytesRead);
                dos.flush();
                socket.getInputStream().read();
            }

            System.out.println("File has been uploaded to the server successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
