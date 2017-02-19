import java.util.*;
import java.io.*;
public class Eficient2 {
    //ejemplo big 1000 1000 6 14
    public static int getSlices(int champ,int tom,char[][] pizza,int max,int min, String s) throws Exception{
       System.out.println("###########################################");
       System.out.println("#################Algoritmo-2###############");
       System.out.println("###########################################");
       int anchura = (int)(Math.sqrt(max)) + 1;
       int altura = anchura;
       boolean decision = true;
       Slice portion = new Slice(0);
       ArrayList<Slice> resp = new ArrayList();
       while(altura*anchura > max){
            if(decision){
                anchura--;
            }else{
                altura--;
            }
            decision = !decision;
       }
       int random = pizza.length-altura;
        for(int k = 0; k < pizza.length-altura+1;k=k+altura){
            for(int l = 0;l<pizza[k].length-anchura+1;l=l+anchura){
                for(int j = k;j<k+altura;j++){
                   for(int i = l;i<l+anchura;i++){   
                        portion.setSlice(j,i,pizza[j][i]);
                        pizza[j][i]='B';
                  }                    
               }  
               if(filter(portion,min)){
                   resp.add(portion);
               }else {
                   for(int ss = 0;ss<portion.size();ss++){
                       Rectangle aux = portion.get(ss);
                       pizza[aux.posx][aux.posy] = aux.ingredient;
                   }                    
               }
               portion =  new Slice(0);
           }            
       }     
       //System.out.println(altura);
       //System.out.println(anchura);        
       OutputFile print = new OutputFile(s);
       //print.printMatrix(pizza);
       ArrayList<Slice> resp2 = evenMore(pizza,max,min,true);
       for(Slice aux : resp2){
           if(filter(aux,min)){
                    resp.add(aux);
                }
        }
       return print.printPizza(resp);
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
                            if(filter(portion,min)){
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
                                if(filter(portion,min)){
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
        System.out.println("Score improved2: "+score);
        return resp;
    }
}
