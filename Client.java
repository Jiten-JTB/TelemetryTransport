import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.*;

public class Client {

    public static void main(String[] args) {
        try {
            // Connect to the server
            // Socket socket = new Socket("localhost", 12345); // Assuming the server is running on localhost and port 12345
            Socket socket = new Socket("192.168.151.16", 12345); 

            // Get the input stream to receive data from the server
            InputStream inputStream = socket.getInputStream();

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
            System.out.println("Array Received From Server: " + Arrays.toString(receivedArray));

            // Close the input stream and socket
            inputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

