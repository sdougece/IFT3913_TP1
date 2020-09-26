
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Metrics {
    static String classes = "";
    static long classe_LOC = 0;
    static long methode_LOC = 0;
    static long classe_CLOC = 0;
    static long methode_CLOC = 0;
    static long classe_DC = 0;
    static long methode_DC = 0;


    private static void parse(File file) {

        int emptyLines = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            System.out.println("I can be opened");
            String line = "";
            while ((line = reader.readLine()) != null) {
                line = line.trim(); //remove space before and after
//                reference https://www.geek-share.com/detail/2782998175.html for regex "^[\\s&&[^\\n]]*$"
                if (line.matches("^[\\s&&[^\\n]]*$")) {
                    emptyLines++;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("I have " + emptyLines +" empty lines");
    }

    //parse one class just for class statistics
    public static ArrayList<Long> get_Class_Stat(File file){
        long classe_LOC = 0;
        long classe_CLOC = 0;
        long classe_DC = 0;

        long emptyLines = 0;

        //calculate stats for one class
        //Todo


        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            System.out.println("I can be opened");
            String line = "";
            while ((line = reader.readLine()) != null) {
                line = line.trim(); //remove space before and after
//                reference https://www.geek-share.com/detail/2782998175.html for regex "^[\\s&&[^\\n]]*$"
                if (line.matches("^[\\s&&[^\\n]]*$")) {
                    emptyLines++;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("I have " + emptyLines +" empty lines");




        ArrayList<Long> stats = new ArrayList<Long>();
        stats.add(classe_LOC);
        stats.add(classe_CLOC);
        stats.add(classe_DC);

        return stats;
    }

    //parse all the methods in one class for methods statistics
    public static ArrayList<Long> get_Methods_Stat(File file){
        String class_name = "";
        ArrayList<String> methods_names = new ArrayList<String>();
        long method_LOC = 0;
        long method_CLOC = 0;
        long method_DC = 0;

        //calculate stats for one method
        //Todo


        ArrayList<Long> stats = new ArrayList<Long>();
        stats.add(method_LOC);
        stats.add(method_CLOC);
        stats.add(method_DC);
        return stats;
    }

    public static void main(String[] args) {
    String chemin = "test";
    System.out.println(chemin);
    File f = new File(chemin);
    File[] allFiles = f.listFiles();

    for(File file : allFiles) {
        if (file.getName().matches(".*\\.java$")) {
            System.out.println("im a java file : " + file.getName());
            parse(file);
        } else {
            System.out.println("not a java file");
        }
    }
    }

}
