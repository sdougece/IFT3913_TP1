public class Record_methods {
    static String chemin;
    static String classe;
    static String methode;
    static long methode_LOC;
    static long methode_CLOC;
    static long methode_DC;

    public Record_methods (String chemin, String classe,String methode, long methode_LOC, long methode_CLOC, long methode_DC){
        this.chemin = chemin;
        this.classe = classe;
        this.methode = methode;
        this.methode_LOC = methode_LOC;
        this.methode_CLOC = methode_CLOC;
        this.methode_DC = methode_DC;
    }


}
