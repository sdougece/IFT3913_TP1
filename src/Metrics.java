
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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

    public static int getMinClassBC(ArrayList<String[]> arr){

        int l = arr.size();
        int index = 0;
        float min = Float.parseFloat(arr.get(0)[6]);
        for(int i=1; i<l; i++){
            float t = Float.parseFloat(arr.get(i)[6]);
            if(t < min){
                min = t;
                index = i;
            }



        }
        return index;
    }

    public static int getMinMethodBC(ArrayList<String[]> arr){

        int l = arr.size();
        int index = 0;
        float min = Float.parseFloat(arr.get(0)[7]);
        for(int i=1; i<l; i++){
            float t = Float.parseFloat(arr.get(i)[7]);
            if(t < min){
                min = t;
                index = i;
            }
        }
        return index;
    }

    public static void output_3class_3methods(){


        ArrayList<String[]> worstBC_classReport = new ArrayList<String[]>();
        ArrayList<String[]> worstBC_methodsReport = new ArrayList<String[]>();

        for(int i=0;i<3;i++){
            int index = getMinClassBC(classReport);
            worstBC_classReport.add(classReport.get(index));
            classReport.remove(index);

        }



        for(int i=0;i<3;i++){
            int index = getMinMethodBC(methodsReport);
            worstBC_methodsReport.add(methodsReport.get(index));
            methodsReport.remove(index);

        }


        Output_classes_csv Occ = new Output_classes_csv(worstBC_classReport);

        Occ.Create_Class_Csv("3_BC_classes.csv");


        Output_methods_csv Omc = new Output_methods_csv(worstBC_methodsReport);


        Omc.Create_Method_Csv("3_BC_methods.csv");




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

        Occ.Create_Class_Csv("classes.csv");


        Output_methods_csv Omc = new Output_methods_csv(methodsReport);


        Omc.Create_Method_Csv("methodes.csv");

        output_3class_3methods();

    }

}
