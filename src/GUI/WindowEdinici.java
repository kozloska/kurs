package GUI;

import javax.swing.*;
import java.awt.*;

public class WindowEdinici extends Window {
    private JPanel panelImage = new JPanel();
    private JPanel panelName = new JPanel();
    private JLabel nameLabel = new JLabel("Конверт единиц измерений",SwingConstants.CENTER);
    private JLabel text = new JLabel(" Что перевести?");
    private JButton pressure = new JButton();
    private JButton weight = new JButton();
    private JButton length = new JButton();
    private JButton temperature = new JButton();
    private JButton time = new JButton();
    private JButton speed = new JButton();
    public WindowEdinici(Controller.MyListener _listener) {
        super(_listener);
        panelImage.setBackground(c1);
        panelName.setBackground(c1);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((screenSize.width - 800) / 2, (screenSize.height - 500) / 2, 800, 500);
        panelImage.setLayout(new GridLayout(2,3,10,10));
        setImage();
        panelImage.add(speed);
        panelImage.add(temperature);
        panelImage.add(time);
        panelImage.add(pressure);
        panelImage.add(length);
        panelImage.add(weight);
        panelName.setLayout(new GridLayout(2,1,10,10));
        panelName.add(nameLabel);
        panelName.add(text);
        Font labelFont = nameLabel.getFont();
        nameLabel.setFont(new Font(labelFont.getName(), Font.BOLD, 25));
        text.setFont(new Font(labelFont.getName(), Font.BOLD, 18));
        pressure.setFont(new Font(labelFont.getName(), Font.BOLD, 18));
        speed.setFont(new Font(labelFont.getName(), Font.BOLD, 18));
        time.setFont(new Font(labelFont.getName(), Font.BOLD, 18));
        temperature.setFont(new Font(labelFont.getName(), Font.BOLD, 18));
        length.setFont(new Font(labelFont.getName(), Font.BOLD, 18));
        weight.setFont(new Font(labelFont.getName(), Font.BOLD, 18));
        frame.setLayout(new BorderLayout(10,10));
        frame.add(panelImage,BorderLayout.CENTER);
        frame.add(panelName,BorderLayout.NORTH);
        addListener();
    }
    private void setImage(){
        try {
            pressure = new JButton("Давление",new ImageIcon(((new ImageIcon(
                    "C:\\Users\\79086\\Desktop\\kurs\\src\\resources\\pressure.png").getImage()
                    .getScaledInstance(100, 100,
                            java.awt.Image.SCALE_SMOOTH)))));

            time = new JButton("Время",new ImageIcon(((new ImageIcon(
                    "C:\\Users\\79086\\Desktop\\kurs\\src\\resources\\time.jpg").getImage()
                    .getScaledInstance(180, 105,
                            java.awt.Image.SCALE_SMOOTH)))));

            temperature = new JButton("Температура",new ImageIcon(((new ImageIcon(
                    "C:\\Users\\79086\\Desktop\\kurs\\src\\resources\\temperature.jpg").getImage()
                    .getScaledInstance(120, 100,
                            java.awt.Image.SCALE_SMOOTH)))));

            weight = new JButton("Масса",new ImageIcon(((new ImageIcon(
                    "C:\\Users\\79086\\Desktop\\kurs\\src\\resources\\weight.jpg").getImage()
                    .getScaledInstance(115, 115,
                            java.awt.Image.SCALE_SMOOTH)))));
            length = new JButton("Длина",new ImageIcon(((new ImageIcon(
                    "C:\\Users\\79086\\Desktop\\kurs\\src\\resources\\length.png").getImage()
                    .getScaledInstance(100, 100,
                            java.awt.Image.SCALE_SMOOTH)))));

            speed = new JButton("Скорость",new ImageIcon(((new ImageIcon(
                    "C:\\Users\\79086\\Desktop\\kurs\\src\\resources\\speed.png").getImage()
                    .getScaledInstance(100, 100,
                            java.awt.Image.SCALE_SMOOTH)))));

            speed.setBackground(new Color(255,255,255));
            speed.setVerticalTextPosition(AbstractButton.BOTTOM);
            speed.setHorizontalTextPosition(AbstractButton.CENTER);
            pressure.setBackground(new Color(255,255,255));
            time.setBackground(new Color(255,255,255));
            time.setVerticalTextPosition(AbstractButton.BOTTOM);
            time.setHorizontalTextPosition(AbstractButton.CENTER);
            length.setBackground(new Color(255,255,255));
            length.setVerticalTextPosition(AbstractButton.BOTTOM);
            length.setHorizontalTextPosition(AbstractButton.CENTER);
            weight.setBackground(new Color(255,255,255));
            weight.setVerticalTextPosition(AbstractButton.BOTTOM);
            weight.setHorizontalTextPosition(AbstractButton.CENTER);
            pressure.setVerticalTextPosition(AbstractButton.BOTTOM);
            pressure.setHorizontalTextPosition(AbstractButton.CENTER);
            temperature.setBackground(new Color(255,255,255));
            temperature.setVerticalTextPosition(AbstractButton.BOTTOM);
            temperature.setHorizontalTextPosition(AbstractButton.CENTER);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    protected void addListener() {
        speed.addActionListener(listener);
        time.addActionListener(listener);
        temperature.addActionListener(listener);
        length.addActionListener(listener);
        pressure.addActionListener(listener);
        weight.addActionListener(listener);
    }
}
