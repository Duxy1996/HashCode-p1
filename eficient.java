import java.util.*;
import java.io.*;
public class Eficient
{
    public static int getSlices(int numeroSlices,int champ,int tom,int[][] pizza,int max,int min) throws Exception{
       int[][] trozo = new int[pizza.length][pizza[2].length];        
       ArrayList<Slice> resp = new ArrayList();
       int counter = 0;       
       Slice portion = new Slice(counter);
       boolean hasMin = false;
       int score = 0;
       int minIn;
       int maxTrozos;
       int rectangles = 0;      
       int llevo_min = 0;
       int llevo_min2 = 0;       
       boolean terminaya = false;
       if(champ > tom){
           minIn = tom;
       }
       else{
           minIn = champ;
       }
       maxTrozos = minIn/min*2;       
        for(int m = 0;m<maxTrozos;m++){        
            for(int j = 0;j<pizza.length;j++){
                for(int i = 0;i<pizza[j].length;i++){
                    if(pizza[j][i] == -1){
                        break;
                    }                    
                    portion.setSlice(j,i,pizza[j][i]);                 
                    if(pizza[j][i] == 2){
                        terminaya = true;
                        hasMin = true;
                        llevo_min++;
                        minIn--;
                    }else{
                        llevo_min2++;
                    }
                    if(minIn == 0){
                        terminaya = true;
                        if(pizza[0].length - i < max-llevo_min-llevo_min2){
                            rectangles = max-1;
                        }
                    }
                    if(pizza[0].length == i+1 && (llevo_min>=min | llevo_min2>=min) ){
                        int aux = max - rectangles;
                        int aux2 = pizza[0].length -1 -i;
                        aux = aux -aux2;
                        if(aux >= 0){
                            rectangles = rectangles + aux;
                        }else{
                            rectangles = rectangles;
                        }
                        terminaya = true;                        
                    }
                    if(m==numeroSlices-1){
                        terminaya = false;
                    }                
                    rectangles++;
                    pizza[j][i] = -1;
                    if(pizza[0].length == i+1 && (llevo_min<min | llevo_min2<min)){
                        portion = new Slice(counter);
                        terminaya = true;
                        rectangles = max;
                        llevo_min = llevo_min2 = min;
                    }         
                    if(rectangles >= max){                    
                        if(llevo_min<min | llevo_min2<min){
                            rectangles--;
                            portion.removeFirst();  
                            if(pizza[0].length == i+1){
                                 portion = new Slice(counter);
                                 terminaya = true;
                            }
                        }else{                        
                            if(terminaya){
                                rectangles = 0;
                                llevo_min = 0;
                                //System.out.println("Trozo");
                                hasMin = false;
                                terminaya = false;
                                rectangles = 0;
                                llevo_min = 0;
                                llevo_min2 = 0;
                                //slices.add(trozo);
                                if(filter(portion,min)){
                                    resp.add(portion);
                                }
                                portion = new Slice(counter++);
                                if(pizza.length-i < min*2){
                                    break;
                                }
                            }                        
                        }                                        
                    }                
                }             
            }          
       }       
       //System.out.println("Trozos"+slices.size());
       System.out.println();
      
       int trozosvacios = 0;
       OutputFile print = new OutputFile();
       //score = print.printPizza(resp);
       print.printMatrix(pizza);
       return score;
    }
    public static int[][] quitarElprimero(int[][] trozo){
        boolean x = false;
        for(int i = 0;i<trozo.length;i++){
            for(int j = 0;j<trozo[0].length;j++){
                if(trozo[i][j] != 0){
                    trozo[i][j] = 0;      
                    x = true;
                    break;                    
                }                
            }
            if(x){
                break;
            }
        }
        return trozo;
    }
    public static boolean filter(Slice aux,int min){
        int min1 = 0;
        int min2 = 0;
        if(aux.getScore() == 0){return false;}
        for(int i = 0; i < aux.getScore();i++){
            if(aux.get(i).ingredient == 'T'){
                min1++;
            }else{
                min2++;
            }
        }
        if(min1 < min || min2 < min){return false;}
        return true;
    }
}
