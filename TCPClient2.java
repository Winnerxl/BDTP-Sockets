/*
RDTP/1.0 Download Client Socket Program
Author: Mert Mermer / 39598285414
Also available at: https://github.com/Winnerxl/BDTP-Sockets
 */

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter server IP address. Press ENTER to use default value \"localhost\" :");
        String serverAddress = scanner.nextLine();

        int defaultPort = 14351;
        System.out.print("Enter server port number. Press ENTER to use default value \"14351\" :");
        String portInput = scanner.nextLine();
        int port = portInput.isEmpty() ? defaultPort : Integer.parseInt(portInput);

        System.out.print("Enter the directory path to download the file (Enter without using quotation marks): ");
        String directoryPath = scanner.nextLine();

        File directory = new File(directoryPath);
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                System.err.println("Failed to create directory: " + directoryPath);
                return;
            }
        }

        try (Socket socket = new Socket(serverAddress, port)) {
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            System.out.print("Enter the name for the downloaded file (i.e. test.txt): ");
            String fileName = scanner.nextLine();


            out.println(fileName);
            String outputFilePath = directoryPath + File.separator + fileName;

            try (FileOutputStream fos = new FileOutputStream(outputFilePath);
                 DataInputStream dis = new DataInputStream(socket.getInputStream())) {

                byte[] buffer = new byte[256];
                int bytesRead;
                while ((bytesRead = dis.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                    fos.flush();
                    if (dis.available() == 0) {
                        break;
                    }
                }

                System.out.println("File " + fileName + " has been successfully downloaded.");

            } catch (FileNotFoundException e) {
                System.err.println("Access is denied to: " + outputFilePath);
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
            System.exit(0);
        }
    }
}
