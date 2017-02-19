import java.util.*;
import java.io.*;
public class Eficient
{
    public static int getSlices(int champ,int tom,char[][] pizza,int max,int min, String name) throws Exception{
       System.out.println("###########################################");
       System.out.println("#################Algoritmo-1###############");
       System.out.println("###########################################");
       int counter = 0;
       int score   = 0;
       int rectangles = 0;      
       int llevo_min  = 0;
       int llevo_min2 = 0;  
       OutputFile print = new OutputFile(name);
       Slice portion = new Slice(counter);
       ArrayList<Slice> resp = new ArrayList();
        for(int j = 0;j<pizza.length;j++){
            for(int i = 0;i<pizza[j].length;i++){                                   
                portion.setSlice(j,i,pizza[j][i]);                 
                if(pizza[j][i] == 'M'){
                    llevo_min++;
                }else{
                    llevo_min2++;
                }                                        
                if(pizza[j].length == i+1 && (llevo_min>=min & llevo_min2>=min) ){                        
                    rectangles = max;                        
                }                
                rectangles++;
                pizza[j][i] = 'B';
                if(pizza[0].length == i+1 && (llevo_min<min | llevo_min2<min)){
                    for(int k = 0;k<portion.size();k++){
                        Rectangle aux = portion.get(k);
                        pizza[aux.posx][aux.posy] = aux.ingredient;
                    }
                    portion = new Slice(counter);
                    rectangles = max;
                    llevo_min = llevo_min2 = min;
                }                 
                if(rectangles >= max){                    
                    if(llevo_min<min | llevo_min2<min){
                        Rectangle aux = portion.get(0);
                        if(aux.ingredient == 'M'){
                            //champiñones
                            pizza[aux.posx][aux.posy] = 'M';
                            llevo_min--;
                        }else{
                            //tomates
                            pizza[aux.posx][aux.posy] = 'T'; 
                            llevo_min2--;
                        }
                        rectangles--;
                        portion.removeFirst();                         
                    }else{
                        rectangles = 0;
                        llevo_min  = 0;
                        llevo_min2 = 0;
                        if(filter(portion,min,max)){
                            resp.add(portion);
                        }
                        portion = new Slice(counter++);
                        if(pizza[j].length-i < min*2){
                            break;
                        }                       
                    }                                        
                }                
            }             
       }                  
       //print.printMatrix(pizza);            
       ArrayList<Slice> resp2 = evenMore(pizza,max,min,true);
       for(Slice aux : resp2){
           if(filter(aux,min,max)){
                    resp.add(aux);
                }
        }
       //print.printMatrix(pizza);
       score = print.printPizza(resp);
       return score;
    }    
    public static boolean filter(Slice aux,int min,int max){
        int min1 = 0;
        int min2 = 0;
        if(aux.getScore() == 0 | aux.getScore() > max) {
            return false;
        }
        for(int i = 0; i < aux.getScore();i++) {
            if(aux.get(i).ingredient == 'T') {
                min1++;
            }else{
                min2++;
            }
        }
        if(min1 < min || min2 < min) {
            return false;
        }
        return true;
    }
    public static ArrayList<Slice> evenMore(char[][] pizza,int max,int min){
        int counter = 0;
        int topscore =0;
        int rectangle = 0;
        Slice portion = new Slice(counter);
        ArrayList<Slice> resp = new ArrayList();
        String s = "";
        int stop = max;
        int iterator = 0;
        boolean exit = true;
        System.out.println();
        System.out.println("Resultado: ");
        for(int k = 0;k < pizza.length;k++){
            iterator = 0;
            if(pizza[k][pizza[k].length-1]=='B' || rectangle >= max){
                stop = max;
                exit = false;
                rectangle = 0;
                if(filter(portion,min,max)){
                    resp.add(portion);
                    portion = new Slice(++counter);
                }
            }
            for(int i = pizza[k].length-1;(i >= pizza[k].length-max) & iterator < stop & exit;i--){
                if(pizza[k][i]=='B' && iterator != 0) {
                    stop = iterator;
                    break;
                }  
                iterator++;                
                portion.setSlice(k,i,pizza[k][i]);
                rectangle++;                
                s = pizza[k][i] + s;                    
                topscore++;                
            }
            exit = true;
            //System.out.println(s);
            s = "";
        }        
        int score = 0;
        for(Slice aux : resp){
            score = aux.getScore()+score;
        }
        //System.out.println("Top score:"+topscore);
        //System.out.println("Get more score improved 2:"+score);
        return resp;
    }
    public static ArrayList<Slice> evenMore(char[][] pizza,int max,int min,boolean elector){
        int counter = 0;
        int rectangles = 0;
        int in1 = 0;
        int in2 = 0;
        Slice portion = new Slice(counter);
        ArrayList<Slice> resp = new ArrayList();
        for(int i = pizza[0].length-1;(i >= pizza[0].length-max) && i > 0;i--){
                for(int j = 0;j<pizza.length;j++) {
                    if(pizza[j][i]=='B'){
                        if(in1 >= min && in2 >= min){
                            if(filter(portion,min,max)){
                                resp.add(portion);
                            }
                        }
                        rectangles = 0;
                        in1 = 0;
                        in2 = 0;
                        portion = new Slice(++counter);                        
                    }else{
                        rectangles++;
                        if(pizza[j][i]=='M'){
                            in1++;
                        }else{
                            in2++;
                        }                    
                        portion.setSlice(j,i,pizza[j][i]);
                        pizza[j][i] = 'B';
                        if(rectangles >= max) {
                            if(in1 >= min && in2 >= in2){
                                if(filter(portion,min,max)){
                                    resp.add(portion);
                                }
                                rectangles = 0;
                                in1 = 0;
                                in2 = 0;
                                portion = new Slice(++counter);
                                if(pizza.length-j-1 < min*2){
                                    break;
                                }
                            }else{
                                Rectangle aux = portion.get(0);
                                if(aux.ingredient == 'M'){
                                    //champiñones
                                    pizza[aux.posx][aux.posy] = 'M';
                                    in1--;
                                }else{
                                    //tomates
                                    pizza[aux.posx][aux.posy] = 'T'; 
                                    in2--;
                                }
                                rectangles--;
                                portion.removeFirst();                            
                            }
                        }
                        if(pizza.length-j-1 == 0 && (in1< min | in2 < min)){     
                            rectangles = 0;
                            in1 = 0;
                            in2 = 0;
                            portion = new Slice(++counter);
                            break;
                        }
                    }                    
                }                
            }
        int score = 0;
        for(Slice aux : resp){
            score = aux.getScore()+score;
        }
        System.out.println("Score improved: "+score);
        return resp;
    }
}