package viewer;

import controller.CompanyManagement;
import java.util.ArrayList;
import java.util.List;
import model.Developer;
import model.Employee;
import model.TeamLeader;
import model.Tester;
import utilities.Inputter;

public class Main {

    CompanyManagement cm = new CompanyManagement();

    public static void main(String[] args) throws Exception {
        // Menu options
        String[] options = {"Show the Employee list",
            "Add Employee", "Update Employee ",
            "Search Employee", "Save",
            "Sort Employees", "Exit"};
        Main main = new Main();

        int choice = 0;
        System.out.println(
                "Note: \nAll employee's salary based on the actual salary after multiply with the bonus and casted into integer!!!");
        do {
            System.out.println("\nCompany Employee Management Program");
            choice = Menu.getChoice(options); // show Menu options
            switch (choice) {
                case 1:
                    main.printAllEmployee();
                    break;
                case 2:
                    main.addEmployee();
                    break;
                case 3:
                    main.updateEmployee();
                    break;
                case 4:
                    main.searchEmployee();
                    break;
                case 5:
                    main.save();
                    break;
                case 6:
                    main.sortEmployees();
                    break;
                default:
                    System.out.println("Good bye!");
            }
        } while (choice > 0 && choice < options.length);
    }

    private void printAllEmployee(List<Employee> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (Employee emp : list) {
            System.out.println(emp);
        }
    }

    private void printAllEmployee() {
        printAllEmployee(cm.getAllEmployees());
    }

    private void addEmployee() {
        String[] options = {"Add new Tester", "Add new Developer", "Add new TeamLeader", "Return to Main menu"};
        int choice = 0;
        do {
            choice = Menu.getChoice(options);
            switch (choice) {
                case 1:
                    boolean check = true;
                    String code = null;
                    do {
                        code = Inputter.inputNonBlankStr("Enter new Tester ID: ");
                        check = cm.isExistCode(code);
                        if (check) {
                            System.out.println("Employee is exist, re-enter the employee");
                        }
                    } while (check == true);
                    String name = Inputter.inputNonBlankStr("Enter new Tester name: ");
                    double baseSal = Inputter.inputDouble("Enter new Tester base salary: ");
                    double bonus = Inputter.inputDouble("Enter new Tester bonus: ");
                    String type = Inputter.inputNonBlankStr("Enter new Tester type: ");

                    Employee emp = new Tester(code, name, baseSal, bonus, type);
                    cm.addEmployee(emp);
                    break;
                case 2:
                    check = true;
                    code = null;
                    do {
                        code = Inputter.inputNonBlankStr("Enter new Developer ID: ");
                        check = cm.isExistCode(code);
                        if (check) {
                            System.out.println("Employee is exist, re-enter the employee");
                        }
                    } while (check == true);
                    name = Inputter.inputNonBlankStr("Enter new Developer name: ");
                    baseSal = Inputter.inputDouble("Enter new Developer base salary: ");
                    String teamName = Inputter.inputNonBlankStr("Enter new Developer team name: ");
                    List<String> programmingLanguages = new ArrayList<>();
                    boolean continueAdd = true;
                    while (continueAdd) {
                        String language = Inputter.inputNonBlankStr("Enter programming language: ");
                        programmingLanguages.add(language.toUpperCase());

                        String choose = Inputter.inputNonBlankStr("Do you want to add more languages? (Y/N): ");
                        if (choose.equalsIgnoreCase("N")) {
                            continueAdd = false;
                        }
                    }
                    double expYear = Inputter.inputDouble("Enter new Developer experience year: ");
                    Employee emp1 = new Developer(code, name, baseSal, teamName, programmingLanguages, expYear);
                    cm.addEmployee(emp1);
                    break;
                case 3:
                    check = true;
                    code = null;
                    do {
                        code = Inputter.inputNonBlankStr("Enter new TeamLeader ID: ");
                        check = cm.isExistCode(code);
                        if (check) {
                            System.out.println("Employee is exist, re-enter the employee");
                        }
                    } while (check == true);

                    boolean kt = true;
                    do {
                        name = Inputter.inputNonBlankStr("Enter new TeamLeader name: ");
                        kt = cm.isExistTeamLeader(name);
                        if (kt) {
                            System.out.println("TeamLeader is exist, please re-enter name: ");
                            return;
                        }
                    } while (kt == true);
                    baseSal = Inputter.inputDouble("Enter new TeamLeader base salary: ");
                    teamName = Inputter.inputNonBlankStr("Enter new TeamLeader team name: ");
                    programmingLanguages = new ArrayList<>();
                    continueAdd = true;
                    while (continueAdd) {
                        String language = Inputter.inputNonBlankStr("Enter programming language: ");
                        programmingLanguages.add(language);

                        String choose = Inputter.inputNonBlankStr("Do you want to add more languages? (Y/N): ");
                        if (choose.equalsIgnoreCase("N")) {
                            continueAdd = false;
                        }
                    }
                    expYear = Inputter.inputDouble("Enter new Developer experience year: ");
                    double bonus_rate = Inputter.inputDouble("Enter new TeamLeader bonus rate: ");
                    Employee emp2 = new TeamLeader(code, name, baseSal, teamName, programmingLanguages, expYear, bonus_rate);
                    cm.addEmployee(emp2);
                    break;
                default:
                    System.out.println("Invalid");
            }
        } while (choice >= 1 && choice <= 3);
    }

    private void sortEmployees() {
        cm.sortBySalaryAndName();
    }

    private void updateEmployee() {
        String[] options = {"Update new Tester", "Update new Developer", "Update new TeamLeader", "Return to Main menu"};
        int choice = 0;
        do {
            choice = Menu.getChoice(options);
            switch (choice) {
                case 1:
                    boolean check = false;
                    String code;
                    Employee e = null;
                    do {
                        code = Inputter.inputNonBlankStr("Enter Tester ID or cancel to return menu: ");
                        if (code.equalsIgnoreCase("cancel")) {
                            System.out.println("Update canceled. Returning to main menu.");
                            return;
                        }
                        check = cm.isExistCode(code);
                        if (!check) {
                            System.out.println("Employee does not exist!");
                        } else {
                            e = cm.getEmployee(code);
                            if (!(e instanceof Tester)) {
                                System.out.println("Not Tester, please re-enter: ");
                                check = false;
                            }
                        }
                    } while (!check);
                    Tester tester = (Tester) e;
                    String newName = Inputter.inputNonBlankStr("Enter new Tester name: ");

                    tester.setEmpName(newName);

                    double newBaseSalary = Inputter.inputDouble("Enter new Tester base salary: ");

                    tester.setBaseSal(newBaseSalary);

                    double newBonusRate = Inputter.inputDouble("Enter new Tester bonus rate: ");

                    tester.setBonusRate(newBonusRate);

                    String newType = Inputter.inputNonBlankStr("Enter new Tester type: ");
                    tester.setType(newType);

                    cm.updateEmployee(tester);
                    break;
                case 2:
                    check = false;
                    code = null;
                    Employee e2 = null;
                    do {
                        code = Inputter.inputNonBlankStr("Enter Teamleader ID or cancel to return menu: ");
                        if (code.equalsIgnoreCase("cancel")) {
                            System.out.println("Update canceled. Returning to main menu.");
                            return;
                        }
                        check = cm.isExistCode(code);
                        if (!check) {
                            System.out.println("Employee does not exist");
                        } else {
                            e2 = cm.getEmployee(code);
                            if ((e2 instanceof TeamLeader)) {
                                System.out.println("Not Teamleader, please re-enter: ");
                                check = false;
                            }
                        }
                    } while (!check);
                    TeamLeader tl = (TeamLeader) e2;
                    newName = Inputter.inputNonBlankStr("Enter new TeamLeader name: ");
                    tl.setEmpName(newName);
                    newBaseSalary = Inputter.inputDouble("Enter new TeamLeader base salary: ");
                    tl.setBaseSal(newBaseSalary);
                    String newTeamName = Inputter.inputNonBlankStr("Enter new TeamLeader team name: ");
                    tl.setTeamName(newTeamName);
                    List<String> newProgrammingLanguages = new ArrayList<>();
                    boolean continueAdd = true;
                    while (continueAdd) {
                        String language = Inputter.inputNonBlankStr("Enter programming language: ");
                        newProgrammingLanguages.add(language.toUpperCase());

                        String choose = Inputter.inputNonBlankStr("Do you want to add more languages? (Y/N): ");
                        if (choose.equalsIgnoreCase("N")) {
                            continueAdd = false;
                        }
                    }
                    tl.setProgrammingLanguages(newProgrammingLanguages);
                    double newExpYear = Inputter.inputDouble("Enter new Developer experience year: ");
                    tl.setExpYear(newExpYear);
                    double bonusRate = Inputter.inputDouble("Enter new Teamleader bonus rate: ");
                    tl.setBonus_rate(bonusRate);
                    cm.updateEmployee(tl);
                    break;
                case 3:
                    check = false;
                    code = null;
                    Employee e1 = null;
                    do {
                        code = Inputter.inputNonBlankStr("Enter Developer ID or cancel to return menu: ");
                        if (code.equalsIgnoreCase("cancel")) {
                            System.out.println("Update canceled. Returning to main menu.");
                            return;
                        }
                        check = cm.isExistCode(code);
                        if (!check) {
                            System.out.println("Employee does not exist");
                        } else {
                            e1 = cm.getEmployee(code);
                            if (!(e1 instanceof Developer)) {
                                System.out.println("Not Developer, please re-enter: ");
                                check = false;
                            }
                        }
                    } while (!check);
                    Developer developer = (Developer) e1;
                    newName = Inputter.inputNonBlankStr("Enter new Developer name: ");

                    developer.setEmpName(newName);

                    newBaseSalary = Inputter.inputDouble("Enter new Tester base salary: ");

                    developer.setBaseSal(newBaseSalary);

                    newTeamName = Inputter.inputNonBlankStr("Enter new Developer team name: ");
                    developer.setTeamName(newTeamName);
                    newProgrammingLanguages = new ArrayList<>();
                    continueAdd = true;
                    while (continueAdd) {
                        String language = Inputter.inputNonBlankStr("Enter programming language: ");
                        newProgrammingLanguages.add(language.toUpperCase());

                        String choose = Inputter.inputNonBlankStr("Do you want to add more languages? (Y/N): ");
                        if (choose.equalsIgnoreCase("N")) {
                            continueAdd = false;
                        }
                    }
                    developer.setProgrammingLanguages(newProgrammingLanguages);
                    newExpYear = Inputter.inputDouble("Enter new Developer experience year: ");
                    developer.setExpYear(newExpYear);
                    cm.updateEmployee(developer);

                    break;

                default:
                    break;

            }
        } while (choice >= 1 && choice <= 3);

    }

    private void save() {
        if (cm.save()) {
            System.out.println("OK!");
        } else {

        }
    }

    private void searchEmployee() {
        String[] options = {"Search by Name", "Search salary higher value", "Search by Programming Languages", "Return to Main menu"};
        int choice = 0;
        do {
            choice = Menu.getChoice(options);
            switch (choice) {
                case 1:
                    String name = Inputter.inputNonBlankStr("Enter Employee name: ");
                    List<Employee> result = cm.searchByName(name);
                    if (result == null || result.isEmpty()) {
                        System.out.println("No Employee is matched");
                    } else {
                        for (Employee e : result) {
                            System.out.println(e);
                        }
                    }

                    break;
                case 2:
                    Tester tester = cm.searchTesterHighestSalary();
                    if (tester == null) {
                        System.out.println("No Employee is matched");
                    } else {
                        System.out.println(tester);
                    }
                    break;
                case 3:
                    String lang = Inputter.inputNonBlankStr("Enter programming language: ");
                    List<Employee> developer = cm.searchByProgrammingLanguages(lang);
                    if (developer == null) {
                        System.out.println("No Employee is matched");
                    } else {
                        for (Employee e : developer) {
                            System.out.println(e);
                        }
                    }
                    break;
                default:
                    break;
            }
        } while (choice >= 1 && choice <= 3);

    }
}