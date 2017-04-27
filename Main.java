import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by ivan on 19/04/17.
 */

public class Main implements WindowListener {

    private Window window = new Window(420, 280);

    public Main() {
        configureElements();
        window.addWindowListener(this);
        window.setVisible(true);
    }

    private void configureElements() {
        ConfigurationPanel configurationPanel = new ConfigurationPanel(window.getWidth() - 20, window.getHeight() - 40);
        window.add(configurationPanel);
    }

    public static void main(String[] args) {
        new Utils();
        new Main();
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        window.dispose();
        System.exit(1);
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
