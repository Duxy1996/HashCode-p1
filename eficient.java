import java.util.*;
import java.io.*;
public class Eficient
{
    public static int getSlices(int champ,int tom,char[][] pizza,int max,int min, String s) throws Exception{
       ArrayList<Slice> resp = new ArrayList();
       int counter = 0;       
       Slice portion = new Slice(counter);      
       int score = 0;
       int minIn;
       int maxTrozos;
       int rectangles = 0;      
       int llevo_min = 0;
       int llevo_min2 = 0;   
       int max_puntuacion = 0;
       boolean terminaya = false;
       if(champ > tom){
           minIn = tom;
       }
       else{
           minIn = champ;
       }
       max_puntuacion = (minIn/min)*max;       
       maxTrozos = minIn/min;             
        for(int j = 0;j<pizza.length;j++){
            for(int i = 0;i<pizza[j].length;i++){
                if(pizza[j][i] == 'B'){
                    break;
                }                    
                portion.setSlice(j,i,pizza[j][i]);                 
                if(pizza[j][i] == 'M'){
                    terminaya = true;
                    llevo_min++;
                    minIn--;
                }else{
                    llevo_min2++;
                }                                        
                if(pizza[0].length == i+1 && (llevo_min>=min | llevo_min2>=min) ){                        
                    rectangles = max;
                    terminaya = true;                        
                }                                    
                rectangles++;
                pizza[j][i] = 'B';
                if(pizza[0].length == i+1 && (llevo_min<min | llevo_min2<min)){
                    portion = new Slice(counter);
                    terminaya = true;
                    rectangles = max;
                    llevo_min = llevo_min2 = min;
                }         
                if(rectangles >= max){                    
                    if(llevo_min<min | llevo_min2<min){
                        Rectangle aux = portion.get(0);
                        if(aux.ingredient == 'M'){
                            //champiñones
                            llevo_min--;
                        }else{
                            //tomates
                            llevo_min2--;
                        }
                        rectangles--;
                        portion.removeFirst();  
                        if(pizza[0].length == i+1){
                             portion = new Slice(counter);
                             terminaya = true;
                        }
                    }else{                        
                        if(terminaya){
                            rectangles = 0;
                            terminaya = false;
                            llevo_min = 0;
                            llevo_min2 = 0;
                            if(filter(portion,min)){
                                resp.add(portion);
                            }
                            portion = new Slice(counter++);
                            if(pizza[2].length-i < min*2){
                                break;
                            }
                        }                        
                    }                                        
                }                
            }             
       }    
       //System.out.println("Trozos"+slices.size());
       System.out.println();      
       int trozosvacios = 0;
       OutputFile print = new OutputFile(s);
       score = print.printPizza(resp);
       //pizza = evenMore(pizza,max,min);
       //print.printMatrix(pizza);
       return score;
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
    public static int[][] evenMore(int[][] pizza,int max,int min){        
        int[][] lessPizza = new int[pizza.length][max];
        boolean onlyOne = true;
        int k = 0;
        for(int i = 0;i < pizza.length; i++){
            k=0;
            for(int j = pizza[0].length - max;j < pizza[0].length; j++){                
                lessPizza[i][k] = pizza[i][j];     
                k++;
            }
        }
        return lessPizza;
    }
}
