import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import org.w3c.dom.Text;

import java.awt.event.*;
import java.util.*;
import java.util.Arrays; // Do We Still Need This?
import java.io.*;
import java.net.*;


class TelemetryOOPS {
    // Array We Want To Send
    public static int selectedModes[] = new int[16];

    // Constants
    public static int channel = 16;
    public static int mode = 4;
    public static int ServerPort = 8080;
    public static String clientAddress;

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
    
    // IP Octal Class
    class IPoctal extends JTextField {
        IPoctal() {
            
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


        // Set Input IP Address
        JTextField octal1 = new JTextField(3);
        pTop.add(octal1);
        JLabel d1 = new JLabel(".");
        pTop.add(d1);
        JTextField octal2 = new JTextField(3);
        pTop.add(octal2);
        JLabel d2 = new JLabel(".");
        pTop.add(d2);
        JTextField octal3 = new JTextField(3);
        pTop.add(octal3);
        JLabel d3 = new JLabel(".");
        pTop.add(d3);
        JTextField octal4 = new JTextField(3);
        pTop.add(octal4);

        // Set IP Address Button
        JButton setIP = new JButton("Set");
        pTop.add(setIP);

        setIP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Clicked");
                String s = e.getActionCommand();
                if (s.equals("Set")) {
                    clientAddress = octal1.getText() + "." + octal2.getText() + "." + octal3.getText() + "." + octal4.getText();
                    System.out.println(clientAddress);
                }
            }
        });


        
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
                    try {
                        ProcessBuilder processBuilder = new ProcessBuilder("java", "--add-exports=java.base/java.lang=ALL-UNNAMED", "--add-exports=java.desktop/sun.awt=ALL-UNNAMED", "--add-exports=java.desktop/sun.java2d=ALL-UNNAMED", "-jar", "TelemetryViewer.jar");
                        processBuilder.start();
                    } catch (IOException e1) {
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

    // Client
    public static void send(int [] dataArray) {
        String serverHost = clientAddress; // Server hostname or IP address
        int serverPort = 12345; // Port to connect to

        // Data to send (16-element array)
        // int[] dataArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7 };

        try {
            // Connect to the server
            Socket socket = new Socket(serverHost, serverPort);
            
            // Send data to the server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            for (int i = 0; i < dataArray.length; i++) {
                String dataToSend = String.valueOf(dataArray[i]);
                out.println(dataToSend);
                System.out.println("Sent: " + dataToSend);
                Thread.sleep(3000); // Small delay to ensure proper transmission
            }
            
            // Close the connection
            out.close();
            socket.close();
            System.out.println("Data sent to ESP8266.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
