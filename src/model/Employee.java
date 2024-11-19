package model;

import java.util.Objects;


public abstract class Employee implements ITextFileOutput {
    private String empID;
    private String empName;
    private double baseSal;

    public Employee(String empID, String empName, double baseSal) {
        this.empID = empID;
        this.empName = empName;
        this.baseSal = baseSal;
    }

    public String getEmpID() {
        return empID;
    }

    public String getEmpName() {
        return empName;
    }

    public double getBaseSal() {
        return baseSal;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public void setBaseSal(double baseSal) {
        this.baseSal = baseSal;
    }
    
    
    abstract public double getSalary();

  
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        if (!Objects.equals(this.empID, other.empID)) {
            return false;
        }
        return Objects.equals(this.empName, other.empName);
    }
    
    

    @Override
    public String toString() {
        return empID + "_" + empName + "_" + baseSal;
    }

    @Override
    public String toFileString() {
        return this.toString();    
    }
    
    
}
