import java.io.FileWriter;
import java.util.ArrayList;

public class Output_methods_csv {
    ArrayList<String[]> report = new ArrayList<String[]>();

    public Output_methods_csv(ArrayList<String[]> report){

        this.report = report;
    }
    //create class.csv with data from report arraylist

    public void Create_Method_Csv(String path){
        try {
            FileWriter csvWriter = new FileWriter(path);
            csvWriter.append("chemin");
            csvWriter.append(",");
            csvWriter.append("class");
            csvWriter.append(",");
            csvWriter.append("methode");
            csvWriter.append(",");
            csvWriter.append("methode_LOC");
            csvWriter.append(",");
            csvWriter.append("methode_CLOC");
            csvWriter.append(",");
            csvWriter.append("methode_DC");
            csvWriter.append(",");
            csvWriter.append("CC");
            csvWriter.append(",");
            csvWriter.append("methode_BC");
            csvWriter.append("\n");


            for (String[] strings : report) {
                csvWriter.append(String.join(",", strings));
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();
        }catch (Exception e){System.out.println(e);}


    }



}
