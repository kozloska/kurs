package GUI;

import GUI.Controller;
import GUI.Window;

import javax.swing.*;
import java.awt.*;


public class WindowMain extends Window {

    private JPanel panel = new JPanel();
    private JButton search = new JButton("Поиск формул");
    private JButton unit = new JButton("Конверт единиц измерений");
    private JButton add = new JButton("Добавить новую формулу");
    private JButton rules = new JButton("О приложение");
    public WindowMain(Controller.MyListener _listener) {
        super( _listener);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel.setBackground(c1);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((screenSize.width - 350) / 2, (screenSize.height - 300) / 2, 350, 300);
        panel.setLayout(new GridLayout(3,1,10,10));
        search.setPreferredSize(new Dimension(200, 50));
        unit.setPreferredSize(new Dimension(200, 50));
        rules.setPreferredSize(new Dimension(200, 50));
        add.setPreferredSize(new Dimension(200, 50));
        panel.add(search);
        panel.add(unit);
        panel.add(add);
      //  panel.add(rules);
        frame.setLayout(new BorderLayout(10,10));
        frame.add(panel,BorderLayout.CENTER);
        Font labelFont = search.getFont();
        search.setFont(new Font(labelFont.getName(), Font.BOLD, 14));
        unit.setFont(new Font(labelFont.getName(), Font.BOLD, 14));
        add.setFont(new Font(labelFont.getName(), Font.BOLD, 14));
        addListener();
    }
    @Override
    protected void addListener() {
        search.addActionListener(listener);
        unit.addActionListener(listener);
        rules.addActionListener(listener);
        add.addActionListener(listener);
    }
}
