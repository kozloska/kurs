package GUI;

import model.Formula;

import javax.swing.*;
import java.awt.*;


public class WindowFormula extends Window{
    private Formula formula;
    private JPanel panelNorth = new JPanel();
    private JPanel panelCenter = new JPanel();
    private JPanel panelSouth = new JPanel();
    private JLabel formulaName;
    private JLabel formulation;
    private JLabel formulaLabel = new JLabel("Формула:",SwingConstants.CENTER);
    private JLabel formul;
    private JLabel SILabel = new JLabel("Международная система единиц:",SwingConstants.CENTER);
    private JLabel SI;
    private JButton calculate = new JButton("Расчитать значение формулы");

    public WindowFormula(Controller.MyListener _listener, Formula _formula) {
        super(_listener);
        this.formula = _formula;
        panelNorth.setBackground(c1);
        panelCenter.setBackground(c1);
        panelSouth.setBackground(c1);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((screenSize.width - 800) / 2, (screenSize.height - 500) / 2, 800, 500);
        formulaName = new JLabel(formula.getName().toUpperCase(), SwingConstants.CENTER);
        formulation = new JLabel("<html><div style='width: 100%; text-align: center;'>" + formula.getFormulation() + "<div></html>",SwingConstants.CENTER);
        formul = new JLabel("   "+ formula.getFormula(),SwingConstants.CENTER);
        SI = new JLabel("   "+formula.getSi().getName(),SwingConstants.CENTER);
        SI.setForeground(Color.BLUE);
        formul.setForeground(Color.BLUE);
        panelNorth.setLayout(new GridLayout(2,1,0,0));
        panelNorth.add(formulaName);
        panelNorth.add(formulation);

        panelCenter.setLayout(new GridLayout(2,2,0,0));
        panelCenter.add(formulaLabel);
        panelCenter.add(formul);
        panelCenter.add(SILabel);
        panelCenter.add(SI);

        panelSouth.setLayout(new GridLayout(1,1,15,15));
        panelSouth.add(calculate);

        frame.setLayout(new BorderLayout(15,15));
        frame.add(panelNorth, BorderLayout.NORTH);
        frame.add(panelCenter, BorderLayout.CENTER);
        frame.add(panelSouth, BorderLayout.SOUTH);

        Font labelFont = formulaName.getFont();
        formulaName.setFont(new Font(labelFont.getName(), Font.BOLD, 25));
        formulation.setFont(new Font(labelFont.getName(), Font.BOLD,22));
        formulaLabel.setFont(new Font(labelFont.getName(), Font.BOLD,20));
        formul.setFont(new Font(labelFont.getName(), Font.BOLD,20 ));
        SILabel.setFont(new Font(labelFont.getName(), Font.BOLD, 20));
        SI.setFont(new Font(labelFont.getName(), Font.BOLD, 20));
        calculate.setFont(new Font(labelFont.getName(), Font.BOLD, 20));
        addListener();
    }
    @Override
    protected void addListener() {
        calculate.addActionListener(listener);
    }

    public Formula getFormula() {
        return formula;
    }
}
