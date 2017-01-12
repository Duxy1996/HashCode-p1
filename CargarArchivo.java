import java.util.*;
import java.io.*;
public class CargarArchivo {
    public static void main(String [] args){
        File file = new File("Doc1.txt");
        String[] lineas = new String[10];//son las columnas
        
        try {
            Scanner sc = new Scanner(file);
            int i = 0;
            while (sc.hasNextLine()) {
                lineas[i] = sc.nextLine();
                i++;
            }
            sc.close();
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }       
}
