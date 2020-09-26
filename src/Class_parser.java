import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Class_parser {

    static long classe_LOC = 0;
    static long classe_CLOC = 0;
    static long classe_DC = 0;
    static long emptyLines = 0;

    public Class_parser(File file){

    }


    //parse one class just for class statistics
    public static ArrayList<Long> get_Class_Stat(File file){


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


}
