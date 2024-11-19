package model;

import java.util.List;


public class TeamLeader extends Developer {
    
        private double bonus_rate;
    
        
    public TeamLeader(String empID, String empName, double baseSal, String teamName, List<String> programmingLanguages, double expYear, double bonus_rate) {
        super(empID, empName, baseSal, teamName, programmingLanguages, expYear);
        this.bonus_rate = bonus_rate;
    }

    public double getBonus_rate() {
        return bonus_rate;
    }

    public void setBonus_rate(double bonus_rate) {
        this.bonus_rate = bonus_rate;
    }
    
    
    
    @Override
    public double getSalary() {
        double baseSalary = super.getSalary();
        if (this instanceof Developer) {
            return baseSalary + (bonus_rate * baseSalary);
        } else {
            return baseSalary;
        }
    }    

    @Override
    public String toFileString() {
        return String.format("%s_%.2f", super.toFileString(), bonus_rate); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
