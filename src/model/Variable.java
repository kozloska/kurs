package model;

public class Variable {
    private int ID;
    private String name;
    private String designation;
    private Formula formula;

    public Variable(int _ID, String _name, String _designation, Formula _formula){
       this.ID = _ID;
       this.name = _name;
       this.designation = _designation;
       this.formula = _formula;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public Formula getFormula() {
        return formula;
    }

    public String getDesignation() {
        return designation;
    }
}
