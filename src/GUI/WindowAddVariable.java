package GUI;

import model.Formula;
import model.Variable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WindowAddVariable extends Window{
    private JPanel panelNorth = new JPanel();
    private JPanel panelCenter = new JPanel();
    private JPanel panelSouth = new JPanel();
    private JButton addButton = new JButton("Добавить переменные");
    private JLabel labelName = new JLabel("Добавление переменных к формуле:",SwingConstants.CENTER);
    private JLabel labelNameFormula;
    private JLabel labelFormula;
    private JLabel label1 = new JLabel("Название переменной",SwingConstants.CENTER);
    private JLabel label2 = new JLabel("Обозначение переменной",SwingConstants.CENTER);
    private Formula formula;

    public WindowAddVariable(Controller.MyListener listener, Controller.MyKeyListener keyListener, Controller.MyWindowListener windowListener, int countVariable, Formula _formula){
        super(listener,keyListener,windowListener);
        this.formula = _formula;
        panelCenter.setBackground(c1);
        panelNorth.setBackground(c1);
        panelSouth.setBackground(c1);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((screenSize.width - 800) / 2, (screenSize.height - 500) / 2, 800, 500);



        panelNorth.setLayout(new GridLayout(3,1,15,15));
        labelNameFormula = new JLabel(formula.getName(),SwingConstants.CENTER);
        labelFormula = new JLabel(formula.getFormula(),SwingConstants.CENTER);

        Font labelFont = labelName.getFont();
        labelName.setFont(new Font(labelFont.getName(), Font.BOLD, 25));
        label1.setFont(new Font(labelFont.getName(), Font.BOLD, 18));
        label2.setFont(new Font(labelFont.getName(), Font.BOLD, 18));
        labelNameFormula.setFont(new Font(labelFont.getName(), Font.BOLD, 23));
        labelFormula.setFont(new Font(labelFont.getName(), Font.BOLD, 23));
        addButton.setFont(new Font(labelFont.getName(), Font.BOLD, 18));

        panelNorth.add(labelName);
        panelNorth.add(labelNameFormula);
        panelNorth.add(labelFormula);

        panelSouth.setLayout(new GridLayout(1,1,15,15));
        panelSouth.add(addButton);

        panelCenter.setLayout(new GridLayout(countVariable+1, 3, 15,15));
        panelCenter.add(new Label(""));
        panelCenter.add(label1);
        panelCenter.add(label2);
        for (int i = 0; i < countVariable; i++){
            JLabel label = new JLabel("     Переменная " + String.valueOf(i+1));
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 18));
            JTextField nameTextField = new JTextField();
            JTextField desTextField = new JTextField();
            nameTextField.setFont(new Font(labelFont.getName(), Font.BOLD, 18));
            desTextField.setFont(new Font(labelFont.getName(), Font.BOLD, 18));
            panelCenter.add(label);
            panelCenter.add(nameTextField);
            panelCenter.add(desTextField);
        }

        frame.setLayout(new BorderLayout(15,15));
        frame.add(panelNorth, BorderLayout.NORTH);
        frame.add(panelSouth, BorderLayout.SOUTH);
        frame.add(panelCenter, BorderLayout.CENTER);
        frame.addWindowListener(windowListener);
        addListener();

    }
    @Override
    protected void addListener() {
        addButton.addActionListener(listener);
        Component[] components = panelCenter.getComponents();
        for(int i = 3; i < components.length; i=i+3){
           JTextField textField1 = (JTextField) components[i+1];
           JTextField textField2 = (JTextField) components[i+2];
           textField1.setName("nameVariable");
           textField2.setName("desVariable");
           textField1.addKeyListener(keyListener);
           textField2.addKeyListener(keyListener);
        }
        frame.addWindowListener(windowListener);
    }
    protected boolean isField(){
        boolean flag = true;
        Component[] components = panelCenter.getComponents();
        for(int i = 3; i < components.length; i=i+3){
            JTextField textField1 = (JTextField) components[i+1];
            JTextField textField2 = (JTextField) components[i+2];
            if(textField1.getText().equals("") || textField1.getText().equals("")){
                flag = false;
            }
        }
        return flag;
    }
    protected List<Variable> getVariable(int id){
       List<Variable> variableList = new ArrayList<>();
        Component[] components = panelCenter.getComponents();
        for(int i = 3; i < components.length; i=i+3){
            JTextField textField1 = (JTextField) components[i+1];
            JTextField textField2 = (JTextField) components[i+2];
            variableList.add(new Variable(id, textField1.getText(), textField2.getText(),formula));
            id++;
        }
       return variableList;
    }
}
