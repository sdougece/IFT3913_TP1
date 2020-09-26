import java.io.File;
import java.util.ArrayList;

public class Methods_parser {
    File file;
    static ArrayList<String> methods_names = new ArrayList<String>();
    static long method_LOC = 0;
    static long method_CLOC = 0;
    static long method_DC = 0;


    public Methods_parser(File file){
        this.file = file;

    }


    //parse all the methods in one class for methods statistics
    public void get_Methods_Stat(){



        //calculate stats for one method
        //Todo



    }




}
