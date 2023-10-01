package GUI;

import GUI.Controller;

import javax.swing.*;
import java.awt.*;

public abstract class Window extends JFrame{
    protected JFrame frame;
    protected Controller.MyListener listener;
    protected Controller.MyKeyListener keyListener;
    protected Controller.MyMouseListener mouseListener;
    protected Controller.MyWindowListener windowListener;
    protected Color c1 = new Color(255,255,240);

    public Window(Controller.MyListener _listener){
        frame = new JFrame("Интерактивный справочник формул по физике");
        frame.setVisible(true);
        this.listener = _listener;
        frame.getContentPane().setBackground(c1);
    }
    public Window(Controller.MyListener _listener, Controller.MyKeyListener _keyListener){
        frame = new JFrame("Интерактивный справочник формул по физике");
        frame.setVisible(true);
        this.listener = _listener;
        this.keyListener = _keyListener;
        frame.getContentPane().setBackground(c1);
    }
    public Window(Controller.MyListener _listener, Controller.MyKeyListener _keyListener, Controller.MyWindowListener _windowListener){
        frame = new JFrame("Интерактивный справочник формул по физике");
        frame.setVisible(true);
        this.listener = _listener;
        this.keyListener = _keyListener;
        this.windowListener = _windowListener;
        frame.getContentPane().setBackground(c1);
    }
    public Window(Controller.MyListener _listener, Controller.MyKeyListener _keyListener, Controller.MyMouseListener _mouseListener){
        frame = new JFrame("Интерактивный справочник формул по физике");
        frame.setVisible(true);
        this.listener = _listener;
        this.keyListener = _keyListener;
        this.mouseListener = _mouseListener;
        frame.getContentPane().setBackground(c1);
    }
    protected abstract void addListener();
    protected void isVisiable(){
        frame.setVisible(false);
    }
}
