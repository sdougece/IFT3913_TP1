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
        String[] strings = line.split("\\)");
        return strings[0]+")";

    }


    //parse all the methods in one class for methods statistics
    public void get_Methods_Stat(){


        try (BufferedReader reader = new BufferedReader(new FileReader(this.file))) {

            System.out.println(this.class_name + "  can be opened");

            String line = "";
            long method_LOC = 0;
            long method_CLOC = 0;
            float method_DC = 0;
            boolean in_method = false;
            int total_brace_balance = 0;
            //calculate stats for one method
            while ((line = reader.readLine()) != null) {
                int brace_balance = get_brace_balance(line);
                total_brace_balance += brace_balance;

                if(total_brace_balance == 2 && in_method==false){
                    //start a method
                    in_method = true;
                    System.out.println("Method found : "+ get_method_signature(line));

                }
                else if(total_brace_balance >2){
                    //in a method
                }
                else if(total_brace_balance == 1 && in_method==true){
                    //end a method
                    in_method = false;
                    System.out.println("method ends");
                }







            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
