import java.io.*;
import java.util.ArrayList;

public class Methods_parser {
    File file;
    String class_name;
    ArrayList<String> methods_names = new ArrayList<String>();
    String methods_path;
    ArrayList<Long> methods_LOC= new ArrayList<Long>();
    ArrayList<Long> methods_CLOC= new ArrayList<Long>();
    ArrayList<Float> methods_DC= new ArrayList<Float>();


    public Methods_parser(File file){
        this.file = file;
        this.methods_path = file.getPath();
        this.class_name = file.getName();

    }


    public int get_brace_balance(String line){

        int balance = 0;
        char[] chars = line.toCharArray();

        for(int i=0; i<line.length(); i++){
            if(chars[i] == '{'){
                balance ++;
            }else if(chars[i] == '}'){
                balance --;
            }
        }

        return balance;
    }

    public String get_method_signature(String line){
        String name;
        String[] sigs;
        //get method name

        String[] strings = line.split("\\)");
        String[] m = strings[0].split(" ");

        int index = 0;

        for(int i=0; i < m.length; i++){

            if(m[i].contains("(")){
                index = i;
            }
        }

        String[] m1 = m[index].split("\\(");
        name = m1[0];

        String params = line.substring(line.indexOf("(") +1, line.indexOf(")"));


        String[] temp = params.split(",");
        String[] temp2;
        String p = "";

        for(int i=0; i<temp.length;i++){

            temp2=temp[i].split(" ");
            for(int j=0; j<temp2.length-1; j++){
                if(j==0){
                    p += temp2[j];
                }else {
                    p += "_" + temp2[j];
                }
            }

        }

        return name+"_"+p;
    }


    //parse all the methods in one class for methods statistics
    public void get_Methods_Stat(){


        try (BufferedReader reader = new BufferedReader(new FileReader(this.file))) {

            System.out.println(this.class_name);

            String line = "";
            long comment_before = 0;
            long method_LOC = 0;
            long method_CLOC = 0;
            float method_DC = 0;
            boolean in_method = false;
            boolean isComment = false;
            int total_brace_balance = 0;
            long emptyLines = 0;
            long totalLines = 0;


            //not just get out of a method but already out
            boolean still_out = true;
            boolean lineComment = false;
            boolean in_class = false;
            //calculate stats for one method
            while ((line = reader.readLine()) != null) {
                //remove space before and after
                line = line.trim();



                //before a method
                //if line before a method is comment, add into method_CLOC
                //stop accumulation when there is a line not comment show up.(ex. a global variable)
                if (line.isEmpty()) {
                    emptyLines++;

                }else if(line.startsWith("//") && !in_method){
                    comment_before++;
                    lineComment=true;
                }
                else if(!in_method && (line.startsWith("/*") && line.endsWith("*/") )|| (line.startsWith("/**") && line.endsWith("*/"))) {
                    comment_before++;
                    lineComment=true;
                }
                else if(!in_method && (line.startsWith("/*") && !line.endsWith("*/") )|| (line.startsWith("/**") && !line.endsWith("*/"))){
                    comment_before++;
                    isComment = true;
                    lineComment=true;
                }
                else if(!in_method && isComment){
                    comment_before++;
                    lineComment=true;
                }
                if(!in_method && line.endsWith("*/")){
                    isComment = false;

                    lineComment=true;
                }
                if(!in_method && !line.isEmpty() &&  !lineComment){
                    comment_before =0;
                    lineComment=false;
                }

                int brace_balance = get_brace_balance(line);
                total_brace_balance += brace_balance;

                if(total_brace_balance ==1 && !in_class){
                    comment_before = 0;
                    in_class = true;
                }

                if(total_brace_balance == 2 && !in_method) {
                    //start a method
                    System.out.println();
                    System.out.println("Method  : "+ get_method_signature(line));
                    in_method = true;
                    still_out = false;
                }

                if(total_brace_balance == 2){

                    if (line.isEmpty()) {
                        emptyLines++;
                    }
                    else if(line.startsWith("//")){
                        method_CLOC++;
                    }
                    else if((line.startsWith("/*") && line.endsWith("*/")) || (line.startsWith("/**") && line.endsWith("*/"))) {
                        method_CLOC++;
                    }
                    else if((line.startsWith("/*") && !line.endsWith("*/") )|| (line.startsWith("/**") && !line.endsWith("*/"))){
                        method_CLOC++;
                        isComment = true;
                    }
                    else if(isComment){
                        method_CLOC++;
                    }
                    if(line.endsWith("*/")){
                        isComment = false;
                    }
                }
                else if(total_brace_balance >2){
                    if (line.isEmpty()) {
                        emptyLines++;
                    }
                    else if(line.startsWith("//")){
                        method_CLOC++;
                    }
                    else if((line.startsWith("/*") && line.endsWith("*/")) || (line.startsWith("/**") && line.endsWith("*/"))) {
                        method_CLOC++;
                    }
                    else if((line.startsWith("/*") && !line.endsWith("*/") )|| (line.startsWith("/**") && !line.endsWith("*/"))){
                        method_CLOC++;
                        isComment = true;
                    }
                    else if(isComment){
                        method_CLOC++;
                    }
                    if(line.endsWith("*/")){
                        isComment = false;
                    }
                }
                else if(total_brace_balance == 1 && in_method){
                    //end of a method
                    in_method = false;
                    totalLines++;
                }

                if(in_method){
                    totalLines++;
                }
                if(!in_method && !still_out){
                    still_out = true;
                    method_LOC = totalLines - emptyLines + method_CLOC;
                    System.out.println("method_LOC =  "+ method_LOC );
                    System.out.println("method_CLOC before = " +comment_before);
                    System.out.println("method_CLOC inside = " +method_CLOC);
                    method_CLOC += comment_before;
                    System.out.println("method_CLOC total = "+ method_CLOC);

                    method_DC = (float)(1.0*method_CLOC/method_LOC);


                    System.out.println("method_DC = " + method_DC);

                    method_CLOC = 0;
                    comment_before = 0;
                }




            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
