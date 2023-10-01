package GUI;

import model.Formula;
import model.Variable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WindowCalculate extends Window{
    private Formula formula;
    private List<Variable> variables;
    private JPanel panelNorth = new JPanel();
    private JPanel panelNorth1 = new JPanel();
    private JPanel panelNorth2 = new JPanel();
    private JPanel panelCenter = new JPanel();
    private JPanel panel = new JPanel();
    private JPanel panelSouth = new JPanel();
    private JLabel formulaName;
    private JLabel formulaLabel = new JLabel("Формула:",SwingConstants.CENTER);
    private JLabel formul;
    private JLabel SILabel = new JLabel("Международная система единиц:",SwingConstants.CENTER);
    private JLabel SI;
    private JLabel resultLabel = new JLabel("   Результат");
    private JTextField result = new JTextField();
    private JButton calculate = new JButton("Расчитать");
    private JButton delete = new JButton("Очистить поля переменных");

    public WindowCalculate(Controller.MyListener listener, Controller.MyKeyListener keyListener, Formula _formula, List<Variable> _variables){
        super(listener,keyListener);
        this.formula = _formula;
        this.variables = _variables;
        panelNorth.setBackground(c1);
        panelCenter.setBackground(new Color(255,255,224));
        panelSouth.setBackground(new Color(255,255,224));
        panel.setBackground(new Color(255,255,224));
        panelCenter.setBorder(BorderFactory.createLineBorder(Color.black));
        panelNorth1.setBackground(c1);
        panelNorth2.setBackground(c1);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((screenSize.width - 800) / 2, (screenSize.height - 500) / 2, 800, 500);
        formulaName = new JLabel(formula.getName().toUpperCase(), SwingConstants.CENTER);
        formul = new JLabel(formula.getFormula(),SwingConstants.CENTER);
        SI = new JLabel(formula.getSi().getName(),SwingConstants.CENTER);
        Font labelFont = formulaName.getFont();

        panelNorth1.setLayout(new GridLayout(1,1,15,15));
        panelNorth1.add(formulaName);
        panelNorth2.setLayout(new GridLayout(2,2,15,15));
        panelNorth2.add(formulaLabel);
        panelNorth2.add(formul);
        panelNorth2.add(SILabel);
        panelNorth2.add(SI);
        panelNorth.setLayout(new GridLayout(2,1,15,15));
        panelNorth.add(panelNorth1);
        panelNorth.add(panelNorth2);

        panel.setLayout(new GridLayout(variables.size()+1,2,15,15));
        for(int i = 0; i < variables.size(); i++){
            JLabel label = new JLabel("   Введите значение переменной: "+ variables.get(i).getName() +
                    " " + variables.get(i).getDesignation());
            panel.add(label);
            label.setFont(new Font(labelFont.getName(), Font.BOLD, 16));
            JTextField textField = new JTextField();
            textField.setFont(new Font(labelFont.getName(), Font.BOLD, 20));
            panel.add(textField);
        }
        panel.add(resultLabel);
        panel.add(result);

        panelSouth.setLayout(new GridLayout(1,2,15,15));
        panelSouth.add(delete);
        panelSouth.add(calculate);

        panelCenter.setLayout(new BorderLayout(15,15));
        panelCenter.add(panel, BorderLayout.CENTER);
        panelCenter.add(panelSouth, BorderLayout.SOUTH);

        frame.setLayout(new BorderLayout(15,15));
        frame.add(panelNorth, BorderLayout.NORTH);
        frame.add(panelCenter, BorderLayout.CENTER);
       // frame.add(panelSouth, BorderLayout.SOUTH);

        formulaName.setFont(new Font(labelFont.getName(), Font.BOLD, 25));
        formulaLabel.setFont(new Font(labelFont.getName(), Font.BOLD,20));
        formul.setFont(new Font(labelFont.getName(), Font.BOLD,20 ));
        SILabel.setFont(new Font(labelFont.getName(), Font.BOLD, 20));
        SI.setFont(new Font(labelFont.getName(), Font.BOLD, 20));
        calculate.setFont(new Font(labelFont.getName(), Font.BOLD,18 ));
        result.setFont(new Font(labelFont.getName(), Font.BOLD,20));
        delete.setFont(new Font(labelFont.getName(), Font.BOLD,18));
        resultLabel.setFont(new Font(labelFont.getName(), Font.BOLD, 16));
        SI.setForeground(Color.BLUE);
        formul.setForeground(Color.BLUE);

        addListener();
    }
    @Override
    protected void addListener() {
        result.setName("number-zero");
        result.addKeyListener(keyListener);
        delete.addActionListener(listener);
        calculate.addActionListener(listener);
        Component[] components = panel.getComponents();
        for(int i = 1; i < components.length-2; i=i+2){
            JTextField textField1 = (JTextField) components[i];
            textField1.setName("number-double");
            textField1.addKeyListener(keyListener);
        }
    }
    protected void removal(){
        Component[] components = panel.getComponents();
        for(int i = 1; i < components.length-2; i=i+2){
            JTextField textField1 = (JTextField) components[i];
            textField1.setText("");
        }
        result.setText("");
    }
    protected List<String> resultVariable(){
        List <String> res = new ArrayList<>();
        Component[] components = panel.getComponents();
        for(int i = 1; i < components.length-2; i=i+2){
            JTextField textField1 = (JTextField) components[i];
            res.add(textField1.getText());
        }
        return res;
    }
    protected boolean isField(){
        boolean flag = true;
        Component[] components = panel.getComponents();
        for(int i = 1; i < components.length-2; i=i+2){
            JTextField textField1 = (JTextField) components[i];
            if(textField1.getText().equals("")){
                flag = false;
            }
        }
        return flag;
    }
    protected void setResult(String res){
        result.setText(res);
    }
}
