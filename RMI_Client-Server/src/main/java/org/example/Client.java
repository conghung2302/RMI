package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client extends JFrame {

    private JLabel timeLabel;
    private JButton runButton, stopButton;
    private Timer timer;
    private int elapsedSeconds = 0;
    Registry registry;
    TimeRequest stub;
    public Client() throws IOException, NotBoundException {
        // Set the title and close operation
        registry = LocateRegistry.getRegistry("localhost", 1099);
        stub = (TimeRequest) registry.lookup("Hello");
        setTitle("Timer UI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);

        // Create the time label
        timeLabel = new JLabel("Time: 0", SwingConstants.CENTER);
        timeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Create the buttons
        runButton = new JButton("Run");
        stopButton = new JButton("Stop");

        // Panel to hold buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(stopButton);
        buttonPanel.add(runButton);

        // Layout for the frame
        setLayout(new BorderLayout());
        add(timeLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               try {
                   String response = stub.sayHello();
                   System.out.println("Response: " + response);
                   timeLabel.setText("Time: " + stub.sayHello());
               } catch (Exception ex) {
                   ex.printStackTrace();
               }
            }
        });

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.start();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
            }
        });

        // Set the visibility and location
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String[] args) throws NotBoundException, IOException {

        new Client();
    }
}
