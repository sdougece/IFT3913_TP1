
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Metrics {

    //sorties:

    static ArrayList<String> classes_names = new ArrayList<String>();
    static long classe_LOC = 0;
    static long classe_CLOC = 0;
    static long classe_DC = 0;

    static ArrayList<String> methods_names = new ArrayList<String>();
    static long methode_LOC = 0;
    static long methode_CLOC = 0;
    static long methode_DC = 0;


    static ArrayList<String[]> classReport = new ArrayList<String[]>();
    static ArrayList<String[]> methodsReport = new ArrayList<String[]>();
    static ArrayList<String> allPaths = new ArrayList<String>();

    public static void getAllFiles(File file){

        File[] allFiles = file.listFiles();
        if (allFiles != null) {
            for(File f : allFiles) {

                if(f.isDirectory()){
                    getAllFiles(f);
                }else{
                    allPaths.add(f.getAbsolutePath());
                }
                }
        }
    }



    public static void main(String[] args) {
    String mainPath = "test";


    File file1 = new File(mainPath);
    getAllFiles(file1);

        for (String path : allPaths) {
            System.out.println(path);
            File eachFile = new File(path);

            if (eachFile.getName().matches(".*\\.java$")) {
                System.out.println("im a java file : " + eachFile.getName());

                //get statistics for each method in this class
                Methods_parser m = new Methods_parser(eachFile);
                m.get_Methods_Stat();

                ArrayList<String[]> methodsReportLine = m.methods_output();
                methodsReport.addAll(methodsReportLine);

                //get statistics for this class
                Class_parser c = new Class_parser(eachFile);
                c.setClass_WMC(m.getClass_WMC());
                c.get_Class_Stat();

                classReport.add(c.class_output());

            } else {
                System.out.println("not a java file");
            }
        }

        Output_classes_csv Occ = new Output_classes_csv(classReport);
        Occ.Create_Class_Csv();
        Output_methods_csv Omc = new Output_methods_csv(methodsReport);
        Omc.Create_Method_Csv();

    }

}
