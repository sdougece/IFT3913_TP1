
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
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));

        } catch (IOException ignored) {
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }

        }
    }

    public static void main(String[] args) {
        String path = "test.java";
        System.out.println(path);
        File f = new File(path);
        if(path.matches(".*\\.java$")){
            System.out.println("im a java file");
            parse(f);
        }else{
            System.out.println("not a java file");
        }

    }

}
