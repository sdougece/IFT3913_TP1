
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
                line = line.trim();//去掉首尾空格
//统计空行的行数
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
        String path = "test\\IndexFiles.java";
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
