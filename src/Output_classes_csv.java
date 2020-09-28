import java.io.FileWriter;
import java.util.ArrayList;

public class Output_classes_csv {
    ArrayList<String[]> report = new ArrayList<String[]>();

    public Output_classes_csv(ArrayList<String[]> report){

        this.report = report;
    }

    //create class.csv with data from report arraylist

    public void Create_Class_Csv(){
        try {
            FileWriter csvWriter = new FileWriter("classes.csv");
            csvWriter.append("chemin");
            csvWriter.append(",");
            csvWriter.append("class");
            csvWriter.append(",");
            csvWriter.append("classe_LOC");
            csvWriter.append(",");
            csvWriter.append("classe_CLOC");
            csvWriter.append(",");
            csvWriter.append("classe_DC");
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
