package GUI;

import javax.sound.sampled.Control;
import javax.swing.*;
import java.awt.*;

public class WindowConversion extends Window{
    private JPanel panelText = new JPanel();
    private String nameFrame;
    private JPanel panelButton = new JPanel();
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JLabel labelName = new JLabel("Конверт единиц измерений",SwingConstants.CENTER);
    private JLabel nameValue;
    private JLabel labelText = new JLabel("    1.Найдите нужную единицу");
    private JLabel labelText2 = new JLabel("    2.Укажите её значение");
    private JLabel labelText3 = new JLabel("    3.Найдите нужную единицу результата");
    private JLabel labelText4 = new JLabel("    4.Нажмите кнопку 'Перевести' и получите результат");
    private JLabel label1 = new JLabel("Исходная величина",SwingConstants.CENTER);
    private JLabel label2 = new JLabel("Результат",SwingConstants.CENTER);
    private JLabel label3 = new JLabel("Единица измерений",SwingConstants.CENTER);
    private JTextField value1 = new JTextField();
    private JTextField value2 = new JTextField();
    private JComboBox units1;
    private JComboBox units2 ;
    private JButton result = new JButton("Перевести");
    private JButton delete = new JButton("Очистить поля");
    private JLabel sign = new JLabel("=",SwingConstants.CENTER);
    private String mas[];
    public WindowConversion(Controller.MyListener _listener, String m[], Controller.MyKeyListener _keylistener, String nameFrame) {
        super(_listener, _keylistener);
        this.mas = m;
        this.nameFrame = nameFrame;
        nameValue = new JLabel(nameFrame,SwingConstants.CENTER);
        units1 = new JComboBox(mas);
        units2 = new JComboBox(mas);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((screenSize.width - 800) / 2, (screenSize.height - 500) / 2, 800, 500);
        Font labelFont = labelName.getFont();
        labelName.setFont(new Font(labelFont.getName(), Font.BOLD, 25));
        nameValue.setFont(new Font(labelFont.getName(), Font.BOLD, 18));
        labelText.setFont(new Font(labelFont.getName(), Font.BOLD, 15));
        labelText2.setFont(new Font(labelFont.getName(), Font.BOLD, 15));
        labelText3.setFont(new Font(labelFont.getName(), Font.BOLD, 15));
        labelText4.setFont(new Font(labelFont.getName(), Font.BOLD, 15));
        label1.setFont(new Font(labelFont.getName(), Font.BOLD, 18));
        label2.setFont(new Font(labelFont.getName(), Font.BOLD, 18));
        label3.setFont(new Font(labelFont.getName(), Font.BOLD, 18));
        value1.setFont(new Font(labelFont.getName(), Font.BOLD, 18));
        value2.setFont(new Font(labelFont.getName(), Font.BOLD, 18));
        units1.setFont(new Font(labelFont.getName(), Font.BOLD, 18));
        units2.setFont(new Font(labelFont.getName(), Font.BOLD, 18));
        sign.setFont(new Font(labelFont.getName(), Font.BOLD, 21));
        delete.setFont(new Font(labelFont.getName(), Font.BOLD, 21));
        result.setFont(new Font(labelFont.getName(), Font.BOLD, 21));
        panelText.setBackground(c1);
        panelButton.setBackground(new Color(255,255,224));
        panelButton.setBorder(BorderFactory.createLineBorder(Color.black));
        //panelButton.setBackground(Color.white);
        panel1.setBackground(c1);
        panel2.setBackground(c1);
        panel1.setLayout(new GridLayout(1,1,2,2));
        panel1.add(labelName);
        panel2.setLayout(new GridLayout(4,1,-1,-1));
        panel2.add(labelText);
        panel2.add(labelText2);
        panel2.add(labelText3);
        panel2.add(labelText4);
        panelText.setLayout(new GridLayout(2,1,10,10));
        panelText.add(panel1);
        panelText.add(panel2);
        panelButton.setLayout(new GridLayout(4,3,10,10));
        panelButton.add(new JLabel(""));
        panelButton.add(nameValue);
        panelButton.add(label3);
        panelButton.add(label1);
        panelButton.add(value1);
        panelButton.add(units1);
        panelButton.add(label2);
        panelButton.add(value2);
        panelButton.add(units2);
        panelButton.add(new JLabel(""));
        panelButton.add(result);
        panelButton.add(delete);
        frame.setLayout(new BorderLayout(10,10));
        frame.add(panelText,BorderLayout.NORTH);
        frame.add(panelButton,BorderLayout.CENTER);
        addListener();
    }
    @Override
    protected void addListener() {
        result.addActionListener(listener);
        delete.addActionListener(listener);
        value1.setName("number-double");
        value2.setName("number-zero");
        value1.addKeyListener(keyListener);
        value2.addKeyListener(keyListener);
    }

    protected void removal(){
         value1.setText("");
         value2.setText("");
    }

    protected String getValue(){
        return value1.getText();
    }
    protected String getUnits1(){
        String a =(String) units1.getSelectedItem();
        return a;
    }
    protected String getUnits2(){
        String a =(String) units2.getSelectedItem();
        return a;
    }
    protected String getNameValue(){
        return nameValue.getText();
    }
    protected void setResult(double value){
        value2.setText(Double.toString(value));
    }
    protected boolean isField(){
        boolean flag = true;
        if(value1.getText().equals("")){
            flag = false;
        }
        return flag;
    }
}
