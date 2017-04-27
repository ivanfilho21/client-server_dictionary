import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.*;

/**
 * Created by ivan on 04/04/17.
 */

public class InsertWord extends JPanel implements ActionListener, KeyListener, WindowListener {

    private Window window;
    private JTextField wordTF;
    private JTextArea meaningA;

    public InsertWord(int w, int h) {
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
        tit = BorderFactory.createTitledBorder(borda, "Insert Word to Dictionary");
        setBorder(tit);
    }

    private void configureElements() {

        JLabel word = new JLabel("Word:");
        wordTF = new JTextField();
        JButton insertB = new JButton("Insert");
        JLabel meaning = new JLabel("Meaning:");
        meaningA = new JTextArea();
        meaningA.setLineWrap(true);

        word.setHorizontalAlignment(SwingConstants.RIGHT);
        meaning.setHorizontalAlignment(SwingConstants.RIGHT);

        int w = 100, h = 30, x = 0, y = 30;

        word.setBounds(x, y, w, h);
        wordTF.setBounds(x + word.getX() + word.getWidth() + 10, y, w * 2, h);
        meaning.setBounds(x, word.getHeight() + word.getY() + 20, w, h);
        JScrollPane scrollPane = new JScrollPane(meaningA);
        scrollPane.setBounds(20, y + 90, getWidth() - 40, 100);
        insertB.setBounds(getWidth() - w - 20, y, w, h);

        insertB.addActionListener(this);
        insertB.addKeyListener(this);
        wordTF.addKeyListener(this);
        meaningA.addKeyListener(this);

        add(word);
        add(wordTF);
        add(insertB);
        add(meaning);
        add(scrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String palavra_digitada = wordTF.getText();
        String significado = meaningA.getText();

        if (!palavra_digitada.isEmpty() && !significado.isEmpty()) {
            if (Utils.writeFile("dict/dictionary.txt", palavra_digitada, significado)) {
                JOptionPane.showMessageDialog(null, "Word inserted");
                window.dispose();
                Utils.initArrays();
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Fill all text boxes!", "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.VK_ENTER))
            actionPerformed(null);
    }

    @Override
    public void keyReleased(KeyEvent e) {

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
