
/**
 * Write a description of class CutPizza here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CutPizza
{
    static int[][] pizzaOne = {{1,1,1,1},{1,2,1,1},{1,2,1,2},{1,2,1,1},{1,1,1,1},{2,2,2,1}};
    static int[][] pizzaTwo = {{1,1,1,1},{1,2,1,1},{1,2,1,2},{1,2,1,1},{1,1,1,1},{2,2,2,1}};
    static int[][] pizzaThree = {{1,1,1,1},{1,2,2,1},{1,2,2,1},{1,1,1,1}};
    static int[][] pizzaFour = {{1,1,1,1},{1,2,1,1},{1,2,1,2},{1,2,1,1},{1,1,1,1},{2,2,2,1}};
    public static void main(String [] args){        
        int numChamp;
        int numTom;
        numChamp = getIngredient(pizzaThree,2);
        numTom = getIngredient(pizzaThree,1);
        System.out.println("Champiñones: "+numChamp);
        System.out.println("Tomatitos  : "+numTom);
        System.out.println("---------------------");
        getSlices(4,numChamp,numTom,pizzaThree);
    }
    public static int getIngredient(int[][] pizza,int ingredient){
        int x = 0;
        for(int j = 0;j<pizza.length;j++){
            for(int i = 0;i<pizza[j].length;i++){
              if(ingredient == pizza[j][i] ){
                  x++;
                }                
            }
        }
        return x;
    }
    public static void getSlices(int numeroSlices,int champ,int tom,int[][] pizza){
        int[][] trozo = new int[pizza.length][pizza[2].length];
        int[][] pizzaRec;
        int minIn;
        boolean terminaya = false;
        if(champ > tom){
            minIn = tom;
        }
        else{
            minIn = champ;
        }
        // poner en horizontal
        for(int m = 0;m<numeroSlices;m++){
        System.out.println("Trozo"+(m+1));
        for(int j = 0;j<pizza.length;j++){
           for(int i = 0;i<pizza[j].length;i++){
                if(pizza[j][i] == -1){break;}
                trozo[j][i] = pizza[j][i];                 
                if(pizza[j][i] == 2){
                    terminaya = true;
                    minIn--;
                }
                if(minIn == 0){
                    terminaya = false;
                }
                if(m==numeroSlices-1){
                    terminaya = false;
                }
                pizza[j][i] = -1;
            }  
           if(terminaya){
                break;
           }
        }      
        terminaya = false;
        for(int j = 0;j<trozo.length;j++){
           for(int i = 0;i<trozo[j].length;i++){
               if(trozo[j][i] != 0){
                   System.out.print(trozo[j][i]);
                }               
            }  
           if(trozo[j][0] != 0){
                System.out.println();
           }
        }
        System.out.println();
        trozo = new int[pizza.length][pizza[2].length];
       }
    }
}
