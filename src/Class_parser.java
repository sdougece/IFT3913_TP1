import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Class_parser {
    File file;
    long classe_LOC;
    long classe_CLOC;
    float classe_DC;
    String class_name;
    String class_path;

    public Class_parser(File file){
        this.file = file;
        this.class_path = file.getPath();
        this.class_name = file.getName();
        this.classe_LOC = 0;
        this.classe_CLOC = 0;
        this.classe_DC = 0;

    }


    //parse one class just for class statistics
    public void get_Class_Stat(){


        //calculate stats for one class
        //Todo
        long emptyLines = 0;
        long totalLines = 0;
        boolean isComment = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(this.file))) {

            System.out.println(this.class_name + "  can be opened");
            String line = "";
            while ((line = reader.readLine()) != null) {
                totalLines++;
                //remove space before and after
                line = line.trim();

                /* we dont count "totaly empty lines" even if it is in javadoc /** or bloc comment /*
                 for example like this bloc is only counted as three lines

                 */

                //reference https://www.geek-share.com/detail/2782998175.html for regex "^[\\s&&[^\\n]]*$"
                if (line.matches("^[\\s&&[^\\n]]*$")) {
                    emptyLines++;
                }
                else if(line.startsWith("//")){
                    this.classe_CLOC++;
                }
                else if((line.startsWith("/*") && line.endsWith("*/") )|| (line.startsWith("/**") && line.endsWith("*/"))) {
                    this.classe_CLOC++;
                }
                else if((line.startsWith("/*") && !line.endsWith("*/") )|| (line.startsWith("/**") && !line.endsWith("*/"))){
                    this.classe_CLOC++;
                    isComment = true;
                }
                else if(isComment){
                    this.classe_CLOC++;
                }
                if(line.endsWith("*/")){
                    isComment = false;
                }
            }
            this.classe_LOC = totalLines - emptyLines;
            this.classe_DC = (float) (1.0*this.classe_CLOC/this.classe_LOC);

        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println("Class path is  " + this.class_path);
//        System.out.println("Class name is  " + this.class_name);
//
//        System.out.println("I have " + totalLines +" total lines");
//        System.out.println("I have " + emptyLines +" empty lines");
//        System.out.println("classe_LOC = " + this.classe_LOC);
//        System.out.println("classe_CLOC = " + this.classe_CLOC);
//        System.out.println("classe_DC = " + this.classe_DC);

    }

    public String[] class_output(){

        return (new String[] {this.class_path, this.class_name,String.valueOf(this.classe_LOC)
        ,String.valueOf(this.classe_CLOC),String.valueOf(this.classe_DC)});
    }


}
