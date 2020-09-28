
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
    static ArrayList<String[]> methodReport = new ArrayList<String[]>();


    public static void main(String[] args) {
    String chemin = "test";
    System.out.println(chemin);


    // adding test data to classReport & methodReport
        classReport.add (new String[] {"chemin1","nomClasse1","LOC1","CLOC1","DC1"});
        classReport.add (new String[] {"chemin2","nomClasse2","LOC2","CLOC2","DC2"});

        methodReport.add (new String[] {"chemin1","nomClasse1","nomMethode1","LOC1","CLOC1","DC1"});
        methodReport.add (new String[] {"chemin2","nomClasse2","nomMethode1","LOC2","CLOC2","DC2"});

    File f = new File(chemin);
    File[] allFiles = f.listFiles();

        if (allFiles != null) {
            for(File file : allFiles) {
                if (file.getName().matches(".*\\.java$")) {
                    System.out.println("im a java file : " + file.getName());

                    //get statistics for this class
                    Class_parser c = new Class_parser(file);
                    c.get_Class_Stat();

                    //get statistics for each method in this class
                    Methods_parser m = new Methods_parser(file);
                    m.get_Methods_Stat();




                } else {
                    System.out.println("not a java file");
                }
            }
        }

        Output_classes_csv Occ = new Output_classes_csv(classReport);
        Occ.Create_Class_Csv();
        Output_methods_csv Omc = new Output_methods_csv(methodReport);
        Omc.Create_Method_Csv();

    }

}
