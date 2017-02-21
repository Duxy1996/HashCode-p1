import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Inputfile {    
    static char[][] pizza;
    static int R, C, L, H, numM, numT;
    static char minIngredient;    
    public static void input(String name){           
        // STEP 1. Read the file and create the pizza 
        double globalTimeOne = System.nanoTime();
        File file = new File(name+".in");
        try {
            Scanner sc = new Scanner(file);            
            //read parameters
            R = sc.nextInt();
            C = sc.nextInt();            
            L = sc.nextInt();            
            H = sc.nextInt();            
            sc.nextLine();
            System.out.println("Pizza de "+R+"x"+C+" = "+(R*C));
            System.out.println("Minimo ingrediente  por trozo: "+L);
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
            }            
        }    
        System.out.println("Numero T: "+numT+" : "+numT*100/(numT+numM)+"%"+
        " Numero M: "+numM+" : "+numM*100/(numT+numM)+"%");
        if(numM < numT) {
            minIngredient = 'M';
        } else {
            minIngredient = 'T';
        }               
        //STEP 2. Try to get the best        
        Eficient5 algo2 = new Eficient5();
        int score = 0;
        double first = System.nanoTime();
        try{
            score = algo2.getSlices(numM,numT,pizza,H,L,name);
        }catch(Exception e){
            System.out.println("Error: "+e.toString());
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
        System.out.println("###########################################");
        System.out.println("Max score is: "+max_score);
        System.out.println("Score:"+score+" for "+name);
        System.out.println("X "+pizza.length);
        System.out.println("Y "+pizza[0].length);
        System.out.println("Puntuacion sobre real:"+ (((double)score)/(max_score))*100);
        System.out.println("Puntuacion sobre total:"+ (((double)score)/(pizza[0].length*pizza.length))*100);
        System.out.println("Tiempo Algoritmico: "+first/1000000000);
        System.out.println("Tiempo Global:"+ ((System.nanoTime()- globalTimeOne)/1000000000));
    }       
}
