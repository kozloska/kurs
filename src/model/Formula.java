package model;

public class Formula {
    private int ID;
    private String name;
    private String formulation;
    private String formula;
    private SI si;

    public Formula(int id, String _name, String _formulation, String _formula, SI _si){
        this.ID = id;
        this.name = _name;
        this.formula = _formula;
        this.formulation = _formulation;
        this.si = _si;
    }
    public Formula(){

    }

    public String getFormulation() {
        return formulation;
    }

    public String getName() {
        return name;
    }
    public String getFormula(){
        return formula;
    }

    public int getID() {
        return ID;
    }

    public SI getSi() {
        return si;
    }
}
