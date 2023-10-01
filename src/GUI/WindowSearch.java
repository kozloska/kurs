package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.MouseListener;
import java.util.List;

public class WindowSearch extends Window{
    private JPanel panel = new JPanel();
    private JPanel panel2 = new JPanel();
    private JButton save = new JButton("Продолжить");
    private JList <String> listFormula = new JList();
    private JScrollPane jScrollPane= new JScrollPane(listFormula);
    private JTextField search = new JTextField("");
    private JLabel label = new JLabel(" Введите в поисковую строку название нужной формулы:");
    public WindowSearch(Controller.MyListener listener, Controller.MyKeyListener keyListener,
                        Controller.MyMouseListener mouseListener) {
        super(listener, keyListener, mouseListener);
        panel.setBackground(c1);
        panel2.setBackground(c1);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((screenSize.width - 800) / 2, (screenSize.height - 500) / 2, 800, 500);

        panel.setLayout(new GridLayout(3,1));
        panel.add(new JLabel());
        panel.add(label);
        panel.add(search);
        panel2.setLayout(new GridLayout(4,1));
        panel2.add(new JLabel());
        panel2.add(new JLabel());
        panel2.add(new JLabel());
        panel2.add(save);
        frame.setLayout(new GridLayout(2,1));
        frame.add(panel);
        frame.add(panel2);
        Font font = new Font("Times New Roman", Font.BOLD, 22);
        search.setFont(new Font("Times New Roman", Font.BOLD, 18));
        save.setFont(new Font("Times New Roman", Font.BOLD, 18));
        label.setFont(font);
        addListener();
    }

    @Override
    protected void addListener() {
        listFormula.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        search.setName("searchText");
        search.addKeyListener(keyListener);
        save.addActionListener(listener);
    }

    protected void formulaList(String[] data){
        frame.setVisible(false);
        listFormula = new JList<String>(data);
        jScrollPane = new JScrollPane(listFormula);
        panel2.removeAll();
        panel2.setLayout(new GridLayout(4,1));
        panel2.add(jScrollPane);
        panel2.add(new JLabel());
        panel2.add(new JLabel());
        panel2.add(save);
        listFormula.setFont(new Font("Times New Roman", Font.BOLD, 18));
        if (data.length == 0){
            jScrollPane.setVisible(false);
        }
        listFormula.addMouseListener(mouseListener);
        frame.setVisible(true);
    }
    protected String getFormula(){
        return search.getText();
    }
    protected void setSearch(){
        search.setText("");
    }
}
