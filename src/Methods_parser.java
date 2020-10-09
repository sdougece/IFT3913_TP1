// Dougece Prophete  matricule :
//Yifu Zhou          matricule : 20092235

import java.io.*;
import java.util.ArrayList;

public class Methods_parser {
    File file;
    String class_name;
    String methods_path;
    Float class_WMC;
    ArrayList<String> methods_names = new ArrayList<String>();
    ArrayList<Long> methods_LOC= new ArrayList<Long>();
    ArrayList<Long> methods_CLOC= new ArrayList<Long>();
    ArrayList<Float> methods_DC= new ArrayList<Float>();
    ArrayList<Integer> methods_CC= new ArrayList<Integer>();
    ArrayList<Float> methods_BC= new ArrayList<Float>();


    public Methods_parser(File file){
        this.file = file;
        this.methods_path = file.getPath();
        this.class_name = file.getName();
        this.class_WMC = (float)0.0;
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
        if(m1.length==0) return "";
        name = m1[0];

        String p = "";
        if(line.indexOf(")") > line.indexOf("(") +1 ){

            String params = line.substring(line.indexOf("(") +1, line.indexOf(")"));


            String[] temp = params.split(",");
            String[] temp2;


            for (String s : temp) {

                temp2 = s.split(" ");
                for (int j = 0; j < temp2.length - 1; j++) {
                    if (j == 0) {
                        p += temp2[j];
                    } else {
                        p += "_" + temp2[j];
                    }
                }

            }
        }
        else{
            p = "";
        }



        return name+"_"+p;
    }


    //parse all the methods in one class for methods statistics
    public void get_Methods_Stat(){


        try (BufferedReader reader = new BufferedReader(new FileReader(this.file))) {

            System.out.println(this.class_name);
            String method_name = "";
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
            int method_cc = 0;



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
                    method_name = get_method_signature(line);
                    System.out.println("Method  : "+ method_name);
                    in_method = true;
                    still_out = false;
                    method_cc++;
                }

                if(total_brace_balance == 2){

                    if (line.isEmpty()) {
//                        emptyLines++;
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
                    else if(line.endsWith("*/")){
                        isComment = false;
                    }
                    if(line.startsWith("if") || line.startsWith("else if") || line.startsWith("for") || line.startsWith("while") || line.startsWith("case")  || line.contains(" catch ") || line.contains("}catch ")){
                        method_cc++;
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
                    else if(line.endsWith("*/")){
                        isComment = false;
                    }
                    if(line.startsWith("if") || line.startsWith("else if") || line.startsWith("for") || line.startsWith("while") || line.startsWith("case")  || line.contains(" catch ") || line.contains("}catch ")){
                        method_cc++;
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
                    method_LOC = totalLines + method_CLOC;
//                    System.out.println("totalLines =  "+ totalLines );
//                    System.out.println("emptyLines =  "+ emptyLines );

                    System.out.println("method_LOC =  "+ method_LOC );
                    System.out.println("method_CLOC before = " +comment_before);
                    System.out.println("method_CLOC inside = " +method_CLOC);
                    method_CLOC += comment_before;
                    System.out.println("method_CLOC total = "+ method_CLOC);

                    method_DC = (float)(1.0*method_CLOC/method_LOC);
                    class_WMC += method_cc;


                    System.out.println("method_DC = " + method_DC);
                    System.out.println("method_CC = " + method_cc);


                    this.methods_names.add(method_name);
                    this.methods_LOC.add(method_LOC);
                    this.methods_CLOC.add(method_CLOC);
                    this.methods_DC.add(method_DC);
                    this.methods_CC.add(method_cc);
                    this.methods_BC.add(method_DC/method_cc);

                    System.out.println("methods_BC = " + methods_BC);

                    method_CLOC = 0;
                    comment_before = 0;
                    method_cc = 0;
                    emptyLines =0;
                    totalLines=0;
                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<String[]> methods_output(){
        int num_methods = this.methods_names.size();

        ArrayList<String[]> report = new ArrayList<String[]>();

        for(int i=0; i<num_methods;i++){

            report.add(new String[]{String.valueOf(this.methods_path), String.valueOf(this.class_name), this.methods_names.get(i),
                    String.valueOf(this.methods_LOC.get(i)),String.valueOf(this.methods_CLOC.get(i)),String.valueOf(this.methods_DC.get(i)),String.valueOf(this.methods_CC.get(i)),String.valueOf(this.methods_BC.get(i))});


        }



        return report;
    }

    public float getClass_WMC(){
        return this.class_WMC;
    }




}
