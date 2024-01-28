import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server {

    public static void main(String[] args) {
        int serverPort = 12345; // Port to listen on

        try {
            // Create a server socket to listen for incoming connections
            ServerSocket serverSocket = new ServerSocket(serverPort);
            System.out.println("Server is listening on port: " + serverPort);

            // Accept a connection from a client
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected from: " + clientSocket.getInetAddress());

            // Get the input stream to receive data from the client
            InputStream inputStream = clientSocket.getInputStream();

            // Create a byte array to store the received data
            byte[] byteArray = new byte[1024]; // Adjust the size as needed

            // Read data from the input stream into the byte array
            int bytesRead = inputStream.read(byteArray);

            // Convert the byte array back to the original array of ints
            int[] receivedArray = new int[bytesRead / 4]; // Assuming 4 bytes per int
            for (int i = 0; i < receivedArray.length; i++) {
                int offset = i * 4;
                receivedArray[i] = (byteArray[offset] << 24)
                        | ((byteArray[offset + 1] & 0xFF) << 16)
                        | ((byteArray[offset + 2] & 0xFF) << 8)
                        | (byteArray[offset + 3] & 0xFF);
            }

            // Print the received array
            System.out.println("Array Received From Client: " + Arrays.toString(receivedArray));

            // Close the input stream, client socket, and server socket
            inputStream.close();
            clientSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

