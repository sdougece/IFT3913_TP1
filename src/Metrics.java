
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Metrics {
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

                if (line.matches("^[\\s&&[^\\n]]*$")) {
                    emptyLines++;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("I have " + emptyLines +" empty lines");
    }

    public static void main(String[] args) {
    String path = "test";
    System.out.println(path);
    File f = new File(path);
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
