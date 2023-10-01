package GUI;

import model.Formula;
import model.SI;

import javax.swing.*;
import java.awt.*;

public class WindowAddFormula extends Window{
    private JPanel panelNorth = new JPanel();
    private JPanel panelCenter = new JPanel();
    private JPanel panelSouth = new JPanel();
    private JButton addButton = new JButton("Добавить формулу");
    private JLabel labelName = new JLabel("Добавление новой формулы", SwingConstants.CENTER);
    private JLabel nameFormula = new JLabel("   Введите название формулы:");
    private JLabel formulationFormula = new JLabel("   Введите формулировку:");
    private JLabel formula = new JLabel("   Введите формулу:");
    private JLabel SIFormula = new JLabel("   Введите единицу измерения формулы:");
    private JTextField nameTextField = new JTextField();
    private JTextField formulationTextField = new JTextField();
    private JTextField formulaTextField = new JTextField();
    private JTextField SITextField =new JTextField();

    public WindowAddFormula(Controller.MyListener listener, Controller.MyKeyListener keyListener){
        super(listener, keyListener);
        panelCenter.setBackground(c1);
        panelNorth.setBackground(c1);
        panelSouth.setBackground(c1);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((screenSize.width - 800) / 2, (screenSize.height - 500) / 2, 800, 500);

        panelNorth.setLayout(new GridLayout(1,1,15,15));
        panelNorth.add(labelName);

        panelSouth.setLayout(new GridLayout(1,1,15,15));
        panelSouth.add(addButton);

        panelCenter.setLayout(new GridLayout(4,2,15,15));
        panelCenter.add(nameFormula);
        panelCenter.add(nameTextField);
        panelCenter.add(formulationFormula);
        panelCenter.add(formulationTextField);
        panelCenter.add(formula);
        panelCenter.add(formulaTextField);
        panelCenter.add(SIFormula);
        panelCenter.add(SITextField);

        frame.setLayout(new BorderLayout(15,15));
        frame.add(panelNorth, BorderLayout.NORTH);
        frame.add(panelSouth, BorderLayout.SOUTH);
        frame.add(panelCenter, BorderLayout.CENTER);

        Font labelFont = labelName.getFont();
        labelName.setFont(new Font(labelFont.getName(), Font.BOLD, 25));
        nameFormula.setFont(new Font(labelFont.getName(), Font.BOLD, 18));
        nameTextField.setFont(new Font(labelFont.getName(), Font.BOLD, 18));
        formula.setFont(new Font(labelFont.getName(), Font.BOLD, 18));
        formulaTextField.setFont(new Font(labelFont.getName(), Font.BOLD, 18));
        formulationFormula.setFont(new Font(labelFont.getName(), Font.BOLD, 18));
        formulationTextField.setFont(new Font(labelFont.getName(), Font.BOLD, 18));
        addButton.setFont(new Font(labelFont.getName(), Font.BOLD, 18));
        SIFormula.setFont(new Font(labelFont.getName(), Font.BOLD, 18));
        SITextField.setFont(new Font(labelFont.getName(), Font.BOLD, 18));

        addListener();
    }
    @Override
    protected void addListener() {
        addButton.addActionListener(listener);
        nameTextField.setName("nameFormula");
        nameTextField.addKeyListener(keyListener);
        formulationTextField.setName("formulationFormula");
        formulationTextField.addKeyListener(keyListener);
        formulaTextField.setName("formula");
        formulaTextField.addKeyListener(keyListener);
        SITextField.setName("SI");
        SITextField.addKeyListener(keyListener);
    }
    protected boolean isField(){
        boolean flag = true;
        if(nameTextField.getText().equals("") || formulationTextField.getText().equals("") ||
                formulaTextField.getText().equals("") || SITextField.getText().equals("")){
            flag = false;
        }
        return flag;
    }
    protected Formula getFormula(int id, SI si){
        Formula formula1 = new Formula(id,nameTextField.getText(), formulationTextField.getText(),
                formulaTextField.getText(), si);
        return formula1;
    }
    protected SI getSI(int id){
        SI si = new SI(id,SITextField.getText());
        return si;
    }
}
