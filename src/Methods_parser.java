import java.io.File;
import java.util.ArrayList;

public class Methods_parser {

    static ArrayList<String> methods_names = new ArrayList<String>();
    static long method_LOC = 0;
    static long method_CLOC = 0;
    static long method_DC = 0;


    public Methods_parser(File file){


    }


    //parse all the methods in one class for methods statistics
    public static ArrayList<Long> get_Methods_Stat(File file){



        //calculate stats for one method
        //Todo


        ArrayList<Long> stats = new ArrayList<Long>();
        stats.add(method_LOC);
        stats.add(method_CLOC);
        stats.add(method_DC);
        return stats;
    }




}
