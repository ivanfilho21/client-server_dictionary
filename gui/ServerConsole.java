package gui;

import ctrl.Server;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ivan on 19/04/17.
 */

public class ServerConsole extends JPanel implements WindowListener {

    private Window window;
    private static JTextArea resultArea = new JTextArea();
    private String serverPort;

    public ServerConsole(int w, int h, String serverPort) {
        window = new Window(w, h);
        this.serverPort = serverPort;

        configureBorder();
        configurePanel(w - 20, h-40);
        configureElements();

        window.add(this);
        window.addWindowListener(this);
        window.setVisible(true);
    }

    private void configurePanel(int w, int h) {
        setSize(w, h);
        setLocation(10, 10);
        setLayout(null);
    }

    private void configureBorder() {
        Border borda = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

        TitledBorder tit;
        tit = BorderFactory.createTitledBorder(borda, "Console");
        tit.setTitleColor(Color.GRAY);
        tit.setTitleFont(new Font("Arial", Font.PLAIN, 12));
        setBorder(tit);
    }

    private void configureElements() {

        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBounds(5, 20, getWidth() - 10, getHeight() - 30);

        resultArea.setText("");
        add(scrollPane);

        JLabel server_port = new JLabel("Server Port: " +serverPort);
        server_port.setBounds(0, window.getHeight() - 30, window.getWidth(), 30);
        server_port.setHorizontalAlignment(SwingConstants.CENTER);
        window.add(server_port);
    }

    public static void refreshConsole(String str) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println( sdf.format(cal.getTime()) );


        resultArea.setText( resultArea.getText() +sdf.format(cal.getTime()) +"\t" +str +"\n");
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        try {
            Server.serverSocket.close();
        } catch (IOException ignored) {}
        window.dispose();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
