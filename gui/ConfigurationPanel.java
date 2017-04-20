package gui;

import ctrl.Server;
import ctrl.Utils;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.ServerSocket;

/**
 * Created by ivan on 29/03/17.
 */

public class ConfigurationPanel extends JPanel implements ActionListener, KeyListener {

    private JTextField portTF;
    private JPanel searchPanel, bottomPanel;
    private static JTextArea resultArea = new JTextArea();

    public ConfigurationPanel(int w, int h) {
        configure(w, h);

        searchPanel = new JPanel(null);
        bottomPanel = new JPanel(null);

        configureBorder(searchPanel, "Settings");
        configureBorder(bottomPanel, "More Options");

        configureElements();

        add(searchPanel);
        add(bottomPanel);
    }

    private void configure(int w, int h) {
        setSize(w, h);
        setLocation(10, 10);
        setLayout(null);
    }

    private void configureBorder(JPanel panel, String title) {
        Border borda = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

        TitledBorder tit;
        tit = BorderFactory.createTitledBorder(borda, title);
        tit.setTitleColor(Color.GRAY);
        tit.setTitleFont(new Font("Arial", Font.PLAIN, 12));
        panel.setBorder(tit);
    }

    private void configureElements() {

        searchPanel.setBounds(0, 0, getWidth(), 80);
        bottomPanel.setBounds(0, 90, getWidth(), 120);

        //Search Panel Elements
        JLabel portLabel = new JLabel("Server Port:");
        portTF = new JTextField();
        JButton runServerB = new JButton("Run Server");

        portLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        int w = 150, h = 30, x = 0, y = 30;

        portLabel.setBounds(x, y, w - 50, h);
        portTF.setBounds(x + portLabel.getX() + portLabel.getWidth() + 10, y, w - 50, h);
        runServerB.setBounds(getWidth() - w - 20, y, w, h);

        runServerB.addActionListener(this);
        runServerB.addKeyListener(this);
        portTF.addKeyListener(this);

        searchPanel.add(portLabel);
        searchPanel.add(portTF);
        searchPanel.add(runServerB);

        //Bottom Panel Elements
        JButton listButton = new JButton("List Words");
        listButton.setBounds(20, (bottomPanel.getHeight()/2) - 15, 150, 30);
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListWords(480, 360);
            }
        });

        JButton insertButton = new JButton("Insert Word");
        insertButton.setBounds(bottomPanel.getWidth() - 170, (bottomPanel.getHeight()/2) - 15, 150, 30);
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InsertWord(480, 360);
            }
        });

        bottomPanel.add(insertButton);
        bottomPanel.add(listButton);
    }

    public static void setResult(String text) {
        resultArea.setText(text);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String palavra_digitada = portTF.getText();

        if (palavra_digitada.contains(")") || palavra_digitada.contains("/") || palavra_digitada.contains("\\n")) {
            JOptionPane.showMessageDialog(null, "Invalid characters!", "Error 0x001", JOptionPane.ERROR_MESSAGE);
        }
        else if (!palavra_digitada.isEmpty()) {
            int port = Integer.parseInt(palavra_digitada);
            ServerSocket serverSocket = Utils.runServer(port);
            if (serverSocket == null) {
                JOptionPane.showMessageDialog(null, "Port already being used", "Error 0x003", JOptionPane.ERROR_MESSAGE);
            } else {
                new Thread(new Server(serverSocket, port)).start();
                new ServerConsole(480, 360, palavra_digitada);
            }
        }
        else
            JOptionPane.showMessageDialog(null, "Invalid argument!", "Error 0x002", JOptionPane.ERROR_MESSAGE);

    }

    @Override
    public void keyTyped(KeyEvent e) {
        String permitted_characters = "0123456789";

        if (!permitted_characters.contains(e.getKeyChar()+""))
            e.consume();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.VK_ENTER))
            actionPerformed(null);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
