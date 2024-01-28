import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.*;
import java.util.*;
// import java.util.Arrays; // Do We Still Need This?
import java.io.*;
import java.net.*;


class TelemetryOOPS {
    // Array We Want To Send
    public static int selectedModes[] = new int[16];

    // Constants
    public static int channel = 16;
    public static int mode = 4;
    public static int serverPort = 12345;
    public static ServerSocket serverSocket;

    // Function To Edit The Selected Mode Array
    public static void setMode(int pos, int mode) { 
        selectedModes[pos] = mode;
    }



    
    // Class To Create Modes Label Panel ----> Mode 1 Mode 2 Mode 3 Mode 4
    static class modesLabelPanel extends JPanel {
        modesLabelPanel() {
            for (int i = 1; i <= mode; i++) {
                JLabel m = new JLabel("Mode " + i + " ");
                m.setFont(new Font("Arial", Font.PLAIN, 13));
                add(m);
            }
        }
    }
    


    
    // Class To Create Complete Set-Up For Set Of JRadioButton 
    static class setOfJRadioButtons extends JPanel {
        // Set Of JRadio Buttons
        public JRadioButton cM[] = new JRadioButton[]{new JRadioButton(), new JRadioButton(), new JRadioButton(), new JRadioButton()};
    
        // Button Group
        public ButtonGroup b = new ButtonGroup();
        
        // Constructor
        setOfJRadioButtons(int ch) { 
            
            // Event Handler
            ItemListener cMiL = new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if (cM[0].isSelected()) {
                        System.out.println("Channel " + (ch + 1) + " ----> Mode 1");
                        System.out.println(1);
                        setMode(ch, 1);
                    }
                    else if (cM[1].isSelected()) {
                        System.out.println("Channel " + (ch + 1) + " ----> Mode 2");
                        System.out.println(2);
                        setMode(ch, 2);
                    }
                    else if (cM[2].isSelected()) {
                        System.out.println("Channel " + (ch + 1) + " ----> Mode 3");
                        System.out.println(3);
                        setMode(ch, 3);
                    }
                    else if (cM[3].isSelected()) {
                        System.out.println("Channel " + (ch + 1) + " ----> Mode 4");
                        System.out.println(4);
                        setMode(ch, 4);
                    }
                }
            };

            
            // Combining Elements
            for (int i = 0; i < mode; i++) {
                add(cM[i]);
                b.add(cM[i]);
                add(Box.createRigidArea(new Dimension(20, 0)));
                cM[i].addItemListener(cMiL);
            }
        }
    }

    // Alert Label 
    public static JLabel alert = new JLabel();
    

    // Check  If The User Assigned Mode To All The Channels
    public static boolean checkArray(int selectedModes[]) {
        for (int i = 0; i < 16; i++) {
            if (selectedModes[i] == 0) {
                System.out.println("Assign Mode To Channel " + (i + 1));
                alert.setText("Assign Mode To Channel " + (i + 1));
                alert.setForeground(Color.red);
                return false;
            }
        }
        return true;
    }




    // Main -----------------------------------------------------------
    public static void main(String args[]) throws Exception {

        //Starting Server
        try{
            serverSocket = new ServerSocket(serverPort);
            System.out.println("Server listening on port " + serverPort);
            InetAddress serverAddress = InetAddress.getLocalHost();
            System.out.println("Server IP address: " + serverAddress.getHostAddress());
        } catch(IOException e) {
            e.printStackTrace();
            // Create Server Error Label ------------------------------------------
        }

        // Creating Frame And Give Some Basic Functionalities
        JFrame frame = new JFrame("Telemetry Mode Selector");
        frame.setSize(540, 660);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());


        // Main Panel ----> This Will Contain All The Main Functionalities
        JPanel mainPanel = new JPanel();
        frame.add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(new GridLayout(1,2));


        // This Will Work As Borders
        JPanel pTop = new JPanel();
        frame.add(pTop, BorderLayout.NORTH);
        JPanel pLeft = new JPanel();
        frame.add(pLeft, BorderLayout.WEST);
        JPanel pRight = new JPanel();
        frame.add(pRight, BorderLayout.EAST);
        JPanel pBottom = new JPanel();
        frame.add(pBottom, BorderLayout.SOUTH);


        // System IP Address Label
        InetAddress localhost = InetAddress.getLocalHost();
        JLabel ip = new JLabel("System IP Address: " + (localhost.getHostAddress()).trim());
        pTop.add(ip);
        JButton refreshButton = new JButton("Refresh");
        pTop.add(refreshButton);

        
        // Inside Main Panel
        JPanel channelP = new JPanel();
        JPanel modeP = new JPanel();
        
        
        // Create Channel Border
        channelP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Channels", TitledBorder.LEFT, TitledBorder.TOP, new Font("Courier", Font.PLAIN, 20), Color.red));
         

        // Create Mode Border
        modeP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Modes", TitledBorder.LEFT, TitledBorder.TOP, new Font("Courier", Font.PLAIN, 20), Color.red));


        // Create Channel Number
        channelP.add(Box.createRigidArea(new Dimension(0, 37)));
        for (int i = 1; i <= 16; i++) {
            JLabel channel = new JLabel("Channel " + i);
            channelP.add(channel);
            channelP.add(Box.createRigidArea(new Dimension(0, 14)));
        }
        
        // Class To Create Modes Label Panel ----> Mode 1 Mode 2 Mode 3 Mode 4
        modesLabelPanel ml = new modesLabelPanel();
        modeP.add(ml);


        // Set-Up All The JRadioButtons
        for (int i = 0; i < channel; i++) {
            setOfJRadioButtons set = new setOfJRadioButtons(i);
            modeP.add(set);
        }
        

        // Set BoxLayout For Channel
        mainPanel.add(channelP);
        BoxLayout cBox = new BoxLayout(channelP, BoxLayout.Y_AXIS);
        channelP.setLayout(cBox);
        

        // Set GridLayout For Mode
        mainPanel.add(modeP);
        modeP.setLayout(new GridLayout(channel + 1, mode));
        
        
        // Sender Button
        JButton sender = new JButton("Send");
        pBottom.add(sender);

        sender.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (checkArray(selectedModes)) {
                    System.out.println(Arrays.toString(selectedModes));

                    // send(selectedModes);
                    Runtime runTime = Runtime.getRuntime();
                    try {
                        runTime.exec("java --add-exports=java.base/java.lang=ALL-UNNAMED --add-exports=java.desktop/sun.awt=ALL-UNNAMED --add-exports=java.desktop/sun.java2d=ALL-UNNAMED -jar TelemetryViewer.jar");
                    }
                    catch (IOException e1) {
                        System.out.println(e1);
                    }
                    System.exit(0);
                }
            }
        });  


        pBottom.add(alert);
        // Makes The Frame Visible
        frame.setVisible(true);
    }

    // IMPORTANT -----------------------------------------
    // Server
    public static void send(int [] dataArray) {

        try {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected from: " + clientSocket.getInetAddress());

            // Get the output stream to send data to the client
            OutputStream outputStream = clientSocket.getOutputStream();

            // Create a byte array to store the data
            byte[] byteArray = new byte[dataArray.length * 4]; // Assuming 4 bytes per int

            // Convert the array to bytes
            for (int i = 0; i < dataArray.length; i++) {
                int offset = i * 4;
                byteArray[offset] = (byte) (dataArray[i] >> 24);
                byteArray[offset + 1] = (byte) (dataArray[i] >> 16);
                byteArray[offset + 2] = (byte) (dataArray[i] >> 8);
                byteArray[offset + 3] = (byte) (dataArray[i]);
            }

            // Send the byte array to the client
            outputStream.write(byteArray);
            System.out.println("Array Send From Client: " + Arrays.toString(dataArray));

            // Close the output stream, client socket, and server socket
            outputStream.close();
            clientSocket.close();
            serverSocket.close();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Client
    // public static void send(int [] dataArray) {
    //     String serverHost = "127.0.0.1"; // Server hostname or IP address
    //     int serverPort = 12345; // Port to connect to

    //     try {
    //         // Create a socket to connect to the server
    //         Socket socket = new Socket(serverHost, serverPort);
    //         System.out.println("Connected to server: " + serverHost);

    //         // Get the output stream to send data to the server
    //         OutputStream outputStream = socket.getOutputStream();

    //         // Create an array of 16 integers to send to the server
    //         // int[] dataArray = {16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

    //         // Create a byte array to store the data
    //         byte[] byteArray = new byte[dataArray.length * 4]; // Assuming 4 bytes per int

    //         // Convert the array to bytes
    //         for (int i = 0; i < dataArray.length; i++) {
    //             int offset = i * 4;
    //             byteArray[offset] = (byte) (dataArray[i] >> 24);
    //             byteArray[offset + 1] = (byte) (dataArray[i] >> 16);
    //             byteArray[offset + 2] = (byte) (dataArray[i] >> 8);
    //             byteArray[offset + 3] = (byte) (dataArray[i]);
    //         }

    //         // Send the byte array to the server
    //         outputStream.write(byteArray);
    //         System.out.println("Array sent to server: " + Arrays.toString(dataArray));

    //         // Close the output stream and socket
    //         outputStream.close();
    //         socket.close();

    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }
}
