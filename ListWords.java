import javax.rmi.CORBA.Util;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by ivan on 04/04/17.
 */

public class ListWords extends JPanel implements WindowListener {

    private Window window;
    private static JTextArea resultArea = new JTextArea();

    public ListWords(int w, int h) {
        window = new Window(w, h);

        configureBorder();
        configurePanel(w - 20, 300);
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
        tit = BorderFactory.createTitledBorder(borda, "List Words");
        tit.setTitleColor(Color.GRAY);
        tit.setTitleFont(new Font("Arial", Font.PLAIN, 12));
        setBorder(tit);
    }

    private void configureElements() {

        Utils.initArrays();

        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBounds(5, 20, getWidth() - 10, getHeight() - 30);

        resultArea.setText(Utils.wordList);
        add(scrollPane);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
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
