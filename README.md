# BDTP/1.0 Client-Server Socket Program Documentation
## 1.	Introduction
The Buffered Data Transfer Protocol (BDTP) is a network protocol designed for efficient file transfer between clients through a server acting as an intermediary. BDTP enables clients to connect to the server and perform controlled data transfer operations, ensuring synchronization and flow control. This protocol uses a structured approach to divide files into 256-byte packets for transmission.

BDTP employs a distinct message format consisting of a header and message body. The header includes essential information about the packet type, operation, and tracking details, ensuring smooth communication between clients and the server. The message body contains the actual file data or control information.
## 2.	Program Overview
This Java application implements a client-server socket program designed to achieve the transfer of files between two clients via a server. “TCPClient1” uploads a file to the server in 256-byte packets, while “TCPClient2” downloads the file from the server. The server acts as an intermediary, buffering up to 12 packets at a time using a producer-consumer setup.  “TCPClient1” waits for acknowledgment from the server after each packet sent, ensuring synchronization and flow control. “TCPClient2” requests the file from the server, receives it in packets, and acknowledges each one, allowing “TCPClient1” to continue uploading. The server listens for connections from both clients on different ports, manages the buffer, and ensures efficient data transfer between clients. This client-server socket program facilitates efficient file management and allows users to remotely access and provide both upload and download the files between two clients over the server buffer. This setup ensures a controlled and synchronized file transfer process, leveraging Java's networking and threading capabilities to manage data flow efficiently.

The server component of the program listens for incoming client connections for uploading query on a specified port TCP-14350 and for downloading query, TCP-14351 is being used. Each query type corresponds to a specific file operation, enabling clients to interact with the server to perform file-related tasks remotely.

The client component provides an interface for interacting with the server. Users can input the server's IP address, port number, and the file’s directory for uploading and downloading operations. The client sends requests to the server, receives responses, and displays the results of each operation to the user.

## 3.	Dependencies
•	Java Development Kit (JDK) version 20 or higher. (Tested on OpenJDK Version 20)

•	Necessary Java class packages to import.

•	An IDE software is recommended to testing. (Tested on IntelliJ IDEA)

# 3.1.	Running the Server
# 3.1.1.	Running the Server Using Command Prompt/Terminal

1.	Extract “TCPServer.java” from the zipped folder.
2.	Open a terminal or command prompt.
3.	Navigate to the directory containing the server code “TCPServer.java”.
4.	Compile the server code using the following command: javac TCPServer.java
5.	Run the server using the following command: java TCPServer.java.
6.	The server will start listening on the default ports TCP-14350 and TCP-14351.

# 3.1.2.	Running the Server Using IntelliJ IDEA IDE
1.	Extract the zip file’s contents into a folder.
2.	Open IntelliJ IDEA, open the project folder, which contains the project files.
3.	Ensure that the necessary dependencies are set up correctly in the IntelliJ project (such as JDK version).
4.	Locate the TCPServer.java file in the project explorer.
5.	Right-click on the TCPServer.java file and select “Run TCPServer.main()” from the context menu, or open the file and click on the green play button next to the main method in the TCPServer class to run the server.
6.	The server will start listening on the default port TCP-14350 and TCP-14351.

# 3.2.	Running the Clients

# 3.2.1.	Running the Clients Using Command Prompt/Terminal
1.	Extract “TCPClient1.java” and “TCPClient2.java” from the zipped folder.
2.	Open a terminal or command prompt.
3.	Navigate to the directory containing the client codes.
4.	Compile the client code using the following command: javac TCPClientX.java
5.	Run the corresponding client using the following command: java TCPClientX.java.
   
# 3.2.2.	Running the Clients Using IntelliJ IDEA IDE
1.	Extract the zip file’s contents into a folder.
2.	Open IntelliJ IDEA, open the project folder, which contains the project files.
3.	Ensure that the necessary dependencies are set up correctly in the IntelliJ project (such as JDK version).
4.	Locate the TCPClientX.java file in the project explorer.
5.	Right-click on the TCPClientX.java file and select “Run TCPClientX.main()” from the context menu, or open the file and click on the green play button next to the main method in the TCPClient class to run the server.

# 3.3.	Notes About Running Clients and Server
•	Ensure that the server is running before attempting to run the client.

•	Modify the default port number in both the client and server code if necessary.

•	To use uploading process, use TCPClient1, and to use downloading process, use TCPClient2.

•	IntelliJ IDEA will handle the compilation and execution of the Java files internally, simplifying the process of running the server and clients within the IDE environment.
