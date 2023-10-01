package GUI;

import jdk.nashorn.api.scripting.NashornScriptEngineFactory;
import model.*;
import SQLLite.DBWorker;
import model.Number;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    private MyListener listener = new MyListener();
    private MyKeyListener keyListener = new MyKeyListener();
    private MyMouseListener mouseListener = new MyMouseListener();
    private MyWindowListener windowListener = new MyWindowListener();
    private WindowMain windowMain;
    private WindowEdinici windowEdinici;
    private WindowConversion windowConversion;
    private WindowSearch windowSearch;
    private WindowFormula windowFormula;
    private WindowAddFormula windowAddFormula;
    private WindowAddVariable windowAddVariable;
    private WindowCalculate windowCalculate;
    private String length[] = {"Миллиметр (мм)", "Сантиметр (см)", "Дециметр (дм)", "Метр (м)", "Километр (км)"};
    private String pressure[] = {"Паскаль (Па)", "Гектопаскаль (гПа)", "Килопаскаль (кПа)", "Миллипаскаль (мПа)"};
    private String time[] = {"Секунда", "Минута", "Час", "День"};
    private String weight[] = {"Грамм (г)", "Килограмм (кг)", "Центер (ц)", "Тонна (т)"};
    private String speed[] = {"Метров/Секунду (м/сек)", "Метров/Минуту (м/мин)", "Метров/Час (м/ч)",
    "Километров/Час (км/ч)", "Километров/Минуту (км/мин)"};
    private String temperature[] = {"Цельсия", "Фаренгейт", "Кельвин"};
    private Number number = new Number();
    private Number newnumber =  new Number();
    private List<Formula> formulaList = new ArrayList<>();
    private List<SI> SIList = new ArrayList<>();
    private List<Variable> variableList = new ArrayList<>();
    private SI si;
    private Formula formula;
    private List<String> resVariable = new ArrayList<>();
    private int countVariable = 0;
    public Controller(String name){
        windowMain = new WindowMain(listener);
        DBWorker.initDB();
        try {
            formulaList = DBWorker.getAllFormula();
            SIList = DBWorker.getAllSI();
            variableList = DBWorker.getAllVariable();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

//    private void check(JTextField text){
//        String regex = "[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)";
//        String word = text.getText();
//        if (word.matches(regex)) {
//        }
//        else {
//            System.out.println(word);
//            JOptionPane.showMessageDialog(null, "Введите число!", "Ошибка",
//                    JOptionPane.ERROR_MESSAGE);
//            text.setText("");
//        }
//    }
    private String variableChoose(){
        String[] mas = {"2", "3", "4","5"};
        String res = mas[0];
        res = (String) JOptionPane.showInputDialog(null, "Выберите количество переменных:",
                "Выбор переменных", JOptionPane.QUESTION_MESSAGE, null, mas, mas[0]);
        return res;
    }
    private String[] checkText(JTextField text){
        String word = text.getText().toLowerCase();
        int count = 0, k = 0;
        for (int i = 0; i < formulaList.size(); i++){
            if(formulaList.get(i).getName().contains(word)){
                count ++;
            }
        }
        String[] mas = new String[count];
        for (int i = 0; i < formulaList.size(); i++){
            if(formulaList.get(i).getName().contains(word)){
                mas[k] = formulaList.get(i).getName();
                k++;
            }
        }
        return mas;
    }
    private String calc(String input) {
        NashornScriptEngineFactory factory = new NashornScriptEngineFactory();
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        try {
            String result =engine.eval(input).toString();
            return result;
        } catch (ScriptException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Variable> getVariableFormula(Formula formula){
        List<Variable> variables = new ArrayList<>();
        for(int i = 0; i < variableList.size(); i++){
            if(variableList.get(i).getFormula().getID() == formula.getID()){
                variables.add(variableList.get(i));
            }
        }
        return variables;
    }

    private String getStringOperation(List <String> resVariable, List<Variable> variables){
        String result = "", res = " ";
       // result.replaceAll()
        int index = 0;
        for(int i = 0; i < formula.getFormula().length(); i++){
            if(formula.getFormula().charAt(i) == '='){
                index = i;
            }
        }
        for(int i = index + 1 ; i < formula.getFormula().length(); i++){
            result += formula.getFormula().charAt(i);
        }

        for(int i = 0; i < variables.size();i++){
            result = result.replaceAll(variables.get(i).getDesignation(),resVariable.get(i));
        }
        System.out.println(result);
//        index = 0;
//        for (int i = 0; i < result.length(); i++){
//            for(int k = 0; k < variables.size(); k++) {
//                if (String.valueOf(result.charAt(i)).equals(variables.get(k).getDesignation())) {
//                    res += resVariable.get(k);
//                    index ++;
//                }
//            }
//            if(index != 1){
//                res += result.charAt(i);
//            }
//            index = 0;
//        }
        return result;
    }

    private MeasureType getMeasureType(String text){
        MeasureType measureType = MeasureType.Миллиметр;
        switch (text)
        {
            case "Миллиметр (мм)":
                measureType = MeasureType.Миллиметр;
                break;
            case "Метр (м)":
                measureType = MeasureType.Метр;
                break;
            case "Сантиметр (см)":
                measureType = MeasureType.Сантиметр;
                break;
            case "Дециметр (дм)":
                measureType = MeasureType.Дециметр;
                break;
            case "Километр (км)":
                measureType = MeasureType.Километр;
                break;
            case "Паскаль (Па)":
                measureType = MeasureType.Паскаль;
                break;
            case "Гектопаскаль (гПа)":
                measureType = MeasureType.Гектопаскаль;
                break;
            case "Килопаскаль (кПа)":
                measureType = MeasureType.Килопаскаль;
                break;
            case "Миллипаскаль (мПа)":
                measureType = MeasureType.Миллипаскаль;
                break;
            case "Секунда":
                measureType = MeasureType.Секунда;
                break;
            case "Минута":
                measureType = MeasureType.Минута;
                break;
            case "Час":
                measureType = MeasureType.Час;
                break;
            case "День":
                measureType = MeasureType.День;
                break;
            case "Цельсия":
                measureType = MeasureType.Цельсия;
                break;
            case "Фаренгейт":
                measureType = MeasureType.Фаренгейт;
                break;
            case "Кельвин":
                measureType = MeasureType.Кельвин;
                break;
            case "Грамм (г)":
                measureType = MeasureType.Грамм;
                break;
            case "Килограмм (кг)":
                measureType = MeasureType.Килограмм;
                break;
            case "Центер (ц)":
                measureType = MeasureType.Центер;
                break;
            case "Тонна (т)":
                measureType = MeasureType.Тонна;
                break;
            case "Метров/Секунду (м/сек)":
                measureType = MeasureType.Метров_в_Секунду;
                break;
            case "Метров/Минуту (м/мин)":
                measureType = MeasureType.Метров_в_Минуту;
                break;
            case "Метров/Час (м/ч)":
                measureType = MeasureType.Метров_в_Час;
                break;
            case "Километров/Час (км/ч)":
                measureType = MeasureType.Километров_в_Час;
                break;
            case "Километров/Минуту (км/мин)":
                measureType = MeasureType.Километров_в_Минуту;
                break;
        }
        return measureType;
    }

    private Number newNumber(Number number,MeasureType type2){
        Number newNumber = new Number();
        String name = windowConversion.getNameValue();
        switch (name) {
            case "Длина":
                newNumber = number.lengthTo(type2);
                break;

            case "Время":
                newNumber = number.timeTo(type2);
                break;

            case "Скорость":
                newNumber = number.speedTo(type2);
                break;

            case "Температура":
                newNumber = number.temperatureTo(type2);
                break;

            case "Масса":
                newNumber = number.weightTo(type2);
                break;

            case "Давление":
                newNumber = number.pressureTo(type2);
                break;
        }
        return newNumber;
    }

    private double conversion() {
        String text = windowConversion.getValue();
        MeasureType type1 = getMeasureType(windowConversion.getUnits1());
        MeasureType type2 = getMeasureType(windowConversion.getUnits2());
            double value = Double.parseDouble(text);
            number = new Number(value, type1);
            newnumber = newNumber(number,type2);
        return newnumber.getValue();
    }
    private boolean ifFormula(String formula){
        boolean flag = false;
        for(int i = 0; i < formulaList.size(); i++){
            if(formulaList.get(i).getName().equals(formula)){
                flag = true;
            }
        }
        return flag;
    }
    private Formula getFormula(String name){
        Formula formula = new Formula();
        for(int i = 0; i < formulaList.size(); i++){
            if(formulaList.get(i).getName().equals(name)){
                formula = formulaList.get(i);
            }
        }
        return formula;
    }
    private void isSearch(){
        JOptionPane.showMessageDialog(null, "Нет такой формулы!", "Ошибка",
                JOptionPane.ERROR_MESSAGE);
    }
    public class MyListener implements ActionListener {
        public MyListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Нжата кнопка " + e.getActionCommand());
            if (e.getActionCommand().equals("Поиск формул")) {
                windowSearch = new WindowSearch( listener, keyListener, mouseListener);
            }
            if (e.getActionCommand().equals("Конверт единиц измерений")) {
                windowEdinici = new WindowEdinici(listener);
            }
            if(e.getActionCommand().equals("Добавить новую формулу")){
                windowAddFormula = new WindowAddFormula(listener,keyListener);
            }
            if (e.getActionCommand().equals("Длина")) {
                windowEdinici.isVisiable();
                windowConversion = new WindowConversion(listener, length, keyListener, "Длина");
            }
            if (e.getActionCommand().equals("Скорость")) {
                windowEdinici.isVisiable();
                windowConversion = new WindowConversion(listener, speed, keyListener,"Скорость");
            }
            if (e.getActionCommand().equals("Время")) {
                windowEdinici.isVisiable();
                windowConversion = new WindowConversion(listener, time, keyListener, "Время");
            }
            if (e.getActionCommand().equals("Температура")) {
                windowEdinici.isVisiable();
                windowConversion = new WindowConversion(listener, temperature, keyListener, "Температура");
            }
            if (e.getActionCommand().equals("Давление")) {
                windowEdinici.isVisiable();
                windowConversion = new WindowConversion(listener, pressure, keyListener, "Давление");
            }
            if (e.getActionCommand().equals("Масса")) {
                windowEdinici.isVisiable();
                windowConversion = new WindowConversion(listener,weight, keyListener, "Масса");
            }
            if (e.getActionCommand().equals("Перевести")) {
                if(windowConversion.isField()) {
                    windowConversion.setResult(conversion());
                }
            }
            if (e.getActionCommand().equals("Очистить поля")) {
                windowConversion.removal();
            }
            if(e.getActionCommand().equals("Добавить формулу")){
                if(windowAddFormula.isField()){
                    String countVar = variableChoose();
                    if(countVar != null){
                        countVariable = Integer.parseInt(countVar);
                        windowAddFormula.isVisiable();
                        si = windowAddFormula.getSI(SIList.size()+1);
                        formula = windowAddFormula.getFormula(formulaList.size()+1,si);
                        try {
                            DBWorker.addSI(si);
                            DBWorker.addFormula(formula);
                            formulaList = DBWorker.getAllFormula();
                            SIList = DBWorker.getAllSI();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        windowAddVariable = new WindowAddVariable(listener,keyListener,windowListener,countVariable,formula);
                    }
                    else {
                        windowAddFormula.isVisiable();
                    }
                }
            }
            if(e.getActionCommand().equals("Добавить переменные")){
                if(windowAddVariable.isField()){
                    List<Variable> variables = windowAddVariable.getVariable(variableList.size()+1);
                    for (int i = 0; i < variables.size(); i++){
                        try {
                            DBWorker.addVariable(variables.get(i));
                            variableList = DBWorker.getAllVariable();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    windowAddVariable.isVisiable();
                }
            }

            if (e.getActionCommand().equals("Продолжить")){
                if(ifFormula(windowSearch.getFormula().toLowerCase())){
                    Formula formula = getFormula((windowSearch.getFormula().toLowerCase()));
                    windowSearch.isVisiable();
                    windowFormula = new WindowFormula(listener,formula);
                }
                else {
                    isSearch();
                    windowSearch.setSearch();
                }
            }
            if (e.getActionCommand().equals("Расчитать значение формулы")) {
               windowFormula.isVisiable();
               formula = windowFormula.getFormula();
               windowCalculate = new WindowCalculate(listener,keyListener,formula,getVariableFormula(formula));
            }
            if(e.getActionCommand().equals("Очистить поля переменных")){
                windowCalculate.removal();
            }
            if(e.getActionCommand().equals("Расчитать")){
                if(windowCalculate.isField()) {
                    resVariable = windowCalculate.resultVariable();
                    String result  = calc(getStringOperation(resVariable, getVariableFormula(formula)));
                    windowCalculate.setResult(result);
                }
            }
        }
    }

    public class MyKeyListener implements KeyListener {
        boolean flag = false;
        public MyKeyListener(){}

        public void keyTyped(KeyEvent e) {
            JTextField text =(JTextField) e.getSource();
            if(text.getName().equals("number-zero")){
                e.consume(); // Этот метод предотвращает ввод новых символов
            }
            if(text.getName().equals("number")){
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();  // ignore event
                }
            }
            if(text.getName().equals("searchText")){
                char c = e.getKeyChar();
                Pattern letter = Pattern.compile("[а-яА-Я]");
                Matcher hasLetter = letter.matcher(String.valueOf(c));
                if(!hasLetter.find()){
                    if(c != ' ') {
                        e.consume();
                    }
                }
            }
            if(text.getName().equals("nameFormula")){
                char c = e.getKeyChar();
                Pattern letter = Pattern.compile("[а-яА-Я]");
                Matcher hasLetter = letter.matcher(String.valueOf(c));
                if(!hasLetter.find()){
                    if(c != ' ') {
                        e.consume();
                    }
                }
            }
            if(text.getName().equals("nameVariable")){
                char c = e.getKeyChar();
                Pattern letter = Pattern.compile("[а-яА-Я]");
                Matcher hasLetter = letter.matcher(String.valueOf(c));
                if(!hasLetter.find()){
                    if(c != ' ') {
                        e.consume();
                    }
                }
            }
            if(text.getName().equals("desVariable")){
                char c = e.getKeyChar();
                Pattern letter = Pattern.compile("[a-zA-Z]");
                Matcher hasLetter = letter.matcher(String.valueOf(c));
                if(!hasLetter.find()){
                    if(c != ' ') {
                        e.consume();
                    }
                }
            }
            if(text.getName().equals("formulationFormula")){
                char c = e.getKeyChar();
                if ( !((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();  // ignore event
                }
            }
            if(text.getName().equals("formula")){
                char c = e.getKeyChar();
                Pattern letter = Pattern.compile("[а-яА-Я0-9]");
                Matcher hasLetter = letter.matcher(String.valueOf(c));
                if(hasLetter.find()){
                        e.consume();
                }
            }
            if(text.getName().equals("SI")){
                char c = e.getKeyChar();
                Pattern letter = Pattern.compile("[а-яА-Я]");
                Matcher hasLetter = letter.matcher(String.valueOf(c));
                if(!hasLetter.find()){
                    if(c != ' ' && c!= '/') {
                        e.consume();
                    }
                }
            }
            if(text.getName().equals("number-double")) {
                String word = text.getText();
                String regex = "[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)";
                word = word + e.getKeyChar();
                if (word.matches(regex) || (word.length() == 0)) {
                    System.out.println("+");
                } else {
                   e.consume();
                }
            }
        }
        @Override
        public void keyPressed(KeyEvent e) {
        }
        @Override
        public void keyReleased(KeyEvent e) {
            JTextField text =(JTextField) e.getSource();
            if(text.getName().equals("searchText")){
                windowSearch.formulaList(checkText(text));
            }

        }
    }
    public class MyMouseListener implements MouseListener{
        public MyMouseListener(){}


        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                JList list = (JList)e.getSource();
                int index = list.locationToIndex(e.getPoint());
                String name = (String)  list.getModel().getElementAt(index) ;
                Formula formula = getFormula(name);
                windowSearch.isVisiable();
                windowFormula = new WindowFormula(listener,formula);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
    public class MyWindowListener implements WindowListener{
        public MyWindowListener(){

        }

        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {
            try {
                windowAddVariable.isVisiable();
                DBWorker.deleteSI(si);
                DBWorker.deleteFormula(formula);
                formulaList = DBWorker.getAllFormula();
                SIList = DBWorker.getAllSI();
                variableList = DBWorker.getAllVariable();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
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
}
