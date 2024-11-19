package controller;

import fileio.EmployeeFileText;
import fileio.IFileReadWrite;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import model.Employee;
import model.Developer;
import model.Tester;
import model.TeamLeader;

public class CompanyManagement {
    private List<Employee> empList;
    public static Scanner sc = new Scanner(System.in);
    
    private List<Employee> load() {
        try {
            IFileReadWrite file = new EmployeeFileText();
            return file.read();
        } catch (Exception e) {
            return null;
        }
    }
    
    public CompanyManagement() {
        empList = load();
        if(empList == null) {
            empList = new ArrayList<>();
        }
    }
    
    public boolean addEmployee(Employee emp) {
        if (emp == null) {
           return false;
        }
        
        return empList.add(emp);
}
    
    public Employee getEmployee(String code) {
        if (code.trim().isEmpty()) {
            return null;
        }
        
        for(Employee emp : empList) {
            if(emp.getEmpID().equalsIgnoreCase(code)) {
                return emp;
            }
        }
        
        return null;
    }
    
    public boolean isExistCode(String  code) {
        return getEmployee(code) != null;
    }
    
    public boolean updateEmployee(Employee updateEmp) {
        int index = empList.indexOf(updateEmp);
        empList.set(index, updateEmp);
        return true;
    }
        
    public List<Employee> getAllEmployees() {
        return empList;
    }
    
    public List<Employee> searchByName(String name) {
        List<Employee> result = new ArrayList<>();
        for (Employee emp : empList) {
            if(emp.getEmpName().toUpperCase().contains(name.trim().toUpperCase()))
            {
                result.add(emp);
            }
        }
        return result;
    }
    
    public boolean isExistTeamLeader(String name) {
        for (Employee emp : empList) {
            if (!(emp instanceof TeamLeader)) {
                continue;
            }
            TeamLeader tl = (TeamLeader) emp;
            if(tl.getEmpName().equals(name)) {
                return true;
            }
            
        }
        return false;
    }
        
    public Tester searchTesterHighestSalary() {
        Tester employeeHighestSalary = null;
        double MAX_INT = -1;
        for(Employee emp : empList) {
            if(!(emp instanceof Tester)) {
                continue;
            }
            Tester tester = (Tester) emp;
            if(tester.getSalary() > MAX_INT) {
                MAX_INT = tester.getSalary();
                employeeHighestSalary = tester;
            }
        }
        return employeeHighestSalary;
    }
    
    public List<Employee> searchByProgrammingLanguages(String programmingLanguage) {
        List<Employee> result = new ArrayList<>();
        if (programmingLanguage == null || programmingLanguage.trim().isEmpty()) {
            System.out.println("Programming language cannot be empty!");
            return result;
        }
        for(Employee emp : empList) {
            if(emp instanceof Developer) {
                Developer dev = (Developer) emp;
                List<String> languages = dev.getProgrammingLanguages();
                
                boolean check = false;
                for(String language : languages) {
                    if(language.equals(programmingLanguage)){
                        check = true;
                        break;
                    }
                }
                
                if(check) {
                    result.add(emp);
                }
            }
            
        }
        return result;
    }
    
    
    public List<Employee> sortBySalaryAndName() {
       List<Employee> list = new ArrayList<Employee>(this.empList);
       Comparator com = new Comparator<Employee>() {
           @Override
           public int compare(Employee o1, Employee o2) {
               int result = o1.getEmpName().compareTo(o2.getEmpName());
               if (result == 0) {
                   result = Double.compare(o1.getSalary(), o2.getSalary());
               }
               return result;
           }
           
       };
       Collections.sort(list, com);
       return list;
    }
    
    public boolean save() {
        try {
            IFileReadWrite file;
            file = new EmployeeFileText();
            return file.write(empList);
        } catch (Exception e) {
            return false;
        }
    }

    
    
    
}
