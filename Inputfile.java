import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Inputfile {    
    static char[][] pizza;
    static int R, C, L, H, numM, numT;
    static char minIngredient;    
    public static void main(String [] args){           
        // STEP 1. Read the file and create the pizza
        System.out.print("Introduce nombre archivo sin extension:  ");
        Scanner kbd = new Scanner(System.in);
        String s = kbd.nextLine();
        double globalTimeOne = System.nanoTime();
        File file = new File(s+".in");
        try {
            Scanner sc = new Scanner(file);            
            //read parameters
            R = sc.nextInt();
            C = sc.nextInt();            
            L = sc.nextInt();            
            H = sc.nextInt();            
            sc.nextLine();
            System.out.println("Pizza de "+R+"x"+C);
            System.out.println("Minimo ingrediente por trozo: "+L);
            System.out.println("Maximo ingredientes por trozo: "+H);
            //create pizza
            pizza = new char[R][C];            
            //fill the pizza!
            int line_n = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    pizza[line_n][i] = line.charAt(i);
                }
                line_n++;
            }
            sc.close();
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }       
        //Count ingredients
        for(int i = 0; i < pizza.length; i++) {
            for(int j = 0; j < pizza[0].length; j++) {
                if(pizza[i][j] == 'M') {
                    numM++;
                } else {
                    numT++;
                }
                //System.out.print(pizza[i][j]); //-representacion matriz
            }
            //System.out.println(); //-representacion matriz
        }        
        if(numM < numT) {
            minIngredient = 'M';
        } else {
            minIngredient = 'T';
        }               
        //STEP 2. Try to get the best
        Eficient algo2 = new Eficient();
        int xx = 0;
        double first = System.nanoTime();
        try{
            xx = algo2.getSlices(numM,numT,pizza,H,L,s);
        }catch(Exception e){
            
        }
        int max_score = 0;
        if(numM<numT){
            max_score = (numM/L)*H;
        }
        else{
            max_score = (numT/L)*H;
        }
        if(max_score>(pizza[0].length*pizza.length)){
            max_score = (pizza[0].length*pizza.length);
        }
        first = System.nanoTime() - first;
        System.out.println();
        System.out.println("Max score is: "+max_score);
        System.out.println("Score:"+xx+" for "+s);
        System.out.println("X "+pizza.length);
        System.out.println("Y "+pizza[0].length);
        System.out.println("Puntuacion:"+ (((double)xx)/(pizza[0].length*pizza.length))*100);
        System.out.println("Tiempo Algoritmico: "+first/1000000000);
        System.out.println("Tiempo Global:"+ ((System.nanoTime()- globalTimeOne)/1000000000));
    }       
}
