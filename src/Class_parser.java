import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Class_parser {
    File file;
    long classe_LOC;
    long classe_CLOC;
    long classe_DC;


    public Class_parser(File file){
        this.file = file;
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

            System.out.println("I can be opened");
            String line = "";
            while ((line = reader.readLine()) != null) {
                totalLines++;
                line = line.trim(); //remove space before and after
//                reference https://www.geek-share.com/detail/2782998175.html for regex "^[\\s&&[^\\n]]*$"
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
                else if(line.endsWith("*/")){
                    isComment = false;
                }




            }
        this.classe_LOC = totalLines - emptyLines;


        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("I have " + totalLines +" total lines");
        System.out.println("I have " + emptyLines +" empty lines");
        System.out.println("classe_LOC = " + this.classe_LOC);
        System.out.println("classe_CLOC = " + this.classe_CLOC);


    }


}
