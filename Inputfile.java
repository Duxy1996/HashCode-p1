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
        File file = new File(s+".in");
        try {
            Scanner sc = new Scanner(file);            
            //read parameters
            R = sc.nextInt();
            System.out.println(R);
            C = sc.nextInt();
            System.out.println(C);
            L = sc.nextInt();
            System.out.println(L);
            H = sc.nextInt();
            System.out.println(H);
            sc.nextLine();            
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
        int[][] number = new int[pizza.length][pizza[0].length];
        for(int k = 0;k<pizza.length;k++) {
            for(int m = 0;m<pizza[0].length;m++) {
                if(pizza[k][m]=='T'){
                    number[k][m]=1;
                }else{
                    number[k][m]=2;
                }
            }                  
        }
        for(int k = 0;k<pizza.length;k++) {
            for(int m = 0;m<pizza[0].length;m++) {
                //System.out.print(number[k][m]); //-representacion matriz
            }            
            //System.out.println(); //-representacion matriz
        }
        //count ingredients
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
        CutPizza algo1 = new CutPizza();
        Eficient algo2 = new Eficient();
        int xx = algo2.getSlices(1000000,numM,numT,number,H,L);
        System.out.println();
        System.out.println("Score:"+xx);
        System.out.println("X "+number.length);
        System.out.println("Y "+number[0].length);
        System.out.println("Total "+(number[0].length*number.length));
    }       
}
