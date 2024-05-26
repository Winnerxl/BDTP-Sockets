/*
RDTP/1.0 Server Socket Program
Author: Mert Mermer / 39598285414
Also available at: https://github.com/Winnerxl/BDTP-Sockets
 */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TCPServer {

    private static final int PORT_CLIENT1 = 14350;
    private static final int PORT_CLIENT2 = 14351;
    private static final int BUFFER_SIZE = 12;  // buffer capacity of 12 packets
    private static final int PACKET_SIZE = 256; // each packet is 256 bytes

    public static void main(String[] args) {
        BlockingQueue<byte[]> buffer = new ArrayBlockingQueue<>(BUFFER_SIZE);

        System.out.println("Initializing server...");

        new Thread(new Client1Handler(PORT_CLIENT1, buffer)).start();
        new Thread(new Client2Handler(PORT_CLIENT2, buffer)).start();
    }
}

class Client1Handler implements Runnable {
    private int port;
    private BlockingQueue<byte[]> buffer;

    public Client1Handler(int port, BlockingQueue<byte[]> buffer) {
        this.port = port;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening for upload on port " + port);

            while (true) {
                try (Socket socket = serverSocket.accept();
                     DataInputStream dis = new DataInputStream(socket.getInputStream())) {

                    System.out.println("A client connected to upload a file.");

                    byte[] packet = new byte[256];
                    int bytesRead;
                    while ((bytesRead = dis.read(packet)) != -1) {
                        byte[] packetCopy = new byte[bytesRead];
                        System.arraycopy(packet, 0, packetCopy, 0, bytesRead);
                        buffer.put(packetCopy);
                        socket.getOutputStream().write(1);
                    }

                    System.out.println("File has been uploaded to buffer.");
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Client2Handler implements Runnable {
    private int port;
    private BlockingQueue<byte[]> buffer;

    public Client2Handler(int port, BlockingQueue<byte[]> buffer) {
        this.port = port;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening for download on port " + port);

            while (true) {
                try (Socket socket = serverSocket.accept();
                     DataOutputStream dos = new DataOutputStream(socket.getOutputStream())) {

                    System.out.println("A client connected to download buffer file.");

                    while (true) {
                        byte[] packet = buffer.take();
                        dos.write(packet);
                        dos.flush();
                        socket.getInputStream().read();
                    }

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("File has been downloaded from buffer.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
