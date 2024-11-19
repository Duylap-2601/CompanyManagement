package model;

import java.util.List;


public class Developer extends Employee {
    
    private String teamName;
    private List<String> programmingLanguages;
    private double expYear;

    public Developer(String empID, String empName, double baseSal, String teamName, List<String> programmingLanguages, double expYear) {
        super(empID, empName, baseSal);
        this.teamName = teamName;
        this.programmingLanguages = programmingLanguages;
        this.expYear = expYear; 
    }

    public String getTeamName() {
        return teamName;
    }

    public List<String> getProgrammingLanguages() {
        return programmingLanguages;
    }

    public double getExpYear() {
        return expYear;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setProgrammingLanguages(List<String> programmingLanguages) {
        this.programmingLanguages = programmingLanguages;
    }

    public void setExpYear(double expYear) {
        this.expYear = expYear;
    }
    
    
    @Override
    public double getSalary() {
        if(expYear >= 5){
            return (int) ((int) this.getBaseSal() + (expYear * 2000000)); 
        }else if(expYear >= 3)
        {
            return (int) ((int) this.getBaseSal() + (expYear * 1000000));
        }else
        {
            return (int) this.getBaseSal();
        }
    }

    @Override
    public String toString() {
        return this.getEmpID() + "_" + this.getEmpName() + "_" + this.getBaseSal() + "_" + teamName + "_" + expYear;
    }

    @Override
    public String toFileString() {
        return String.format("%s_%s", toString(), programmingLanguages); 
    }
    
    
}
