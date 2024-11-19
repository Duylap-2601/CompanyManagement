package fileio;


import fileio.IFileReadWrite;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import model.Developer;
import model.Employee;
import model.TeamLeader;
import model.Tester;


public class EmployeeFileText implements IFileReadWrite<Employee> {
    
    private final String FILE_NAME = "src/fileio/Employee.txt";

    @Override
    public List<Employee> read() throws Exception {
        List<Employee> list = new ArrayList<>();
      
        
        File f;
        FileInputStream file;
        BufferedReader myInput;
        try {
            f = new File(FILE_NAME);
            String fullBath = f.getAbsolutePath();
            file = new FileInputStream(fullBath);
            myInput = new BufferedReader(new InputStreamReader(file));
            String line;
            while((line = myInput.readLine()) != null) {
                if(line.trim().isEmpty()) {
                    continue;
                }
                String split[] = line.split("_");
                switch(split.length) {
                    case 6:
                        String code = split[1].trim();
                        String name = split[2].trim();
                        double baseSal = Double.parseDouble(split[3].trim());
                        double bonus = Double.parseDouble(split[4].trim());
                        String type = split[5].trim();
                        list.add(new Tester(code, name, baseSal, bonus, type));
                    break;    
                    case 7:
                        code = split[1].trim();
                        name = split[2].trim();
                        baseSal = Double.parseDouble(split[3].trim());
                        String team = split[4].trim();
                        double expY = Double.parseDouble(split[5].trim());
                        String pl = split[6].trim().substring(1, split[6].trim().length() - 1);
                        List<String> listPl = new ArrayList<>();
                        for(String s : pl.split(",")) {
                            listPl.add(s.trim());
                        }
                        list.add(new Developer(code, name, baseSal, team, listPl, expY));
                    break;    
                    case 8:
                        code = split[1].trim();
                        name = split[2].trim();
                        baseSal = Double.parseDouble(split[3].trim());
                        team = split[4].trim();
                        expY = Double.parseDouble(split[5].trim());
                        pl = split[6].trim().substring(1, split[6].trim().length() - 1);
                        listPl = new ArrayList<>();
                        for(String s : pl.split(",")) {
                            listPl.add(s.trim());
                        }
                        bonus = Double.parseDouble(split[7].trim());
                        list.add(new TeamLeader(code, name, baseSal, team, listPl, expY, bonus));
                    break;
                    default:
                        break;
                }
            }
           
            myInput.close();
        } catch (Exception e) {
            throw e;
        }
        return list;
     
    }

    @Override
    public boolean write(List<Employee> list) throws Exception {
        if (list == null) {
            return false;
        }
        
        File f;
        FileOutputStream file;
        BufferedWriter myOutput;
        try {
            f = new File(FILE_NAME);
            String fullBath = f.getAbsolutePath();
            file = new FileOutputStream(fullBath);
            myOutput = new BufferedWriter(new OutputStreamWriter(file));
            
            int i = 0;
            for(Employee emp : list) {
                if (i > 0) {
                    myOutput.newLine();
                }
                myOutput.write((++i) + "_" + emp.toFileString());
            }
            myOutput.close();
        } catch (Exception e) {
            throw e;
        }
        return true;
    }
    
}
