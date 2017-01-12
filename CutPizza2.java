import java.util.*;
public class CutPizza2
{
   static int[][] pizzaOne = {{1,1,1,1},{1,2,1,1},{1,2,1,2},{1,2,1,1},{1,1,1,1},{2,2,2,1}};
   static ArrayList<int[]> position = new ArrayList();
   static ArrayList<int[][]> slice = new ArrayList();
   public static void main(String [] args){   
       int rows = 5;
       int columns = 5;
       int min = 1;
       int max = 6;       
       int[] miningrediente = {-1,-1};
       int ingredientOne = getNumIngredient(pizzaOne,1);       
       int ingredientTwo = getNumIngredient(pizzaOne,2);
       if(ingredientTwo < ingredientOne){
           miningrediente[0] = 2;//tipo
           miningrediente[1] = ingredientTwo;//cantidad
        }else{
            miningrediente[0] = 1;
            miningrediente[1] = ingredientOne;            
        }
       getSlices(rows,columns,min,max,pizzaOne);
       
   }
   public static void getSlices(int rows,int columns,int min,int max,int pizza[][]){
       if(min == 1){
           boolean change = false;
           int parts;
           int slice1[][] = new int[columns][rows];
           int slice2[][] = new int[columns][rows];
           for(int[] actual : position){             
              for(int i = actual[0];i<columns;i++){
                  if(pizza[i][actual[0]] == -1 || pizza[i][actual[0]] == 2){
                      break;
                    }
                  
                }
              
            } 
        }
   }
   public static int getNumIngredient(int[][] pizza,int ingredient){
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
   public static void getPositionof(int[] miningrediente,int[][] pizza,int columns ,int rows){
       int tipo = miningrediente[1];
       for(int i = 0; i < columns;i++){
           for(int j = 0; j < rows;j++){
               if(pizza[i][j]== tipo){
                   int aux[] = {i,j};
                   position.add(aux);
                }               
            }           
        }
    }
}
