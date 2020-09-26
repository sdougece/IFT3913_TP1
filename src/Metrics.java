
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





    public static void main(String[] args) {
    String chemin = "test";
    System.out.println(chemin);
    File f = new File(chemin);
    File[] allFiles = f.listFiles();

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

}
