import java.util.*;
public class Eficient4
{
    public static int getSlices(int t, int m,char[][] pizza,int max,int min, String name) throws Exception{
       System.out.println("###########################################");
       System.out.println("#################Algoritmo-4###############");
       System.out.println("###########################################");
       char[][] clone = clone(pizza);
       int posib[][] = getPosib(max,min);
       ArrayList<Slice> resp = new ArrayList(); 
       int score = 0;
       OutputFile print = new OutputFile(name);
       //print.printMatrix(pizza);
       int max_now = 0;
       int max_score = pizza.length * pizza[0].length;
       while(score != max_score){
           for(int j = 0;j<pizza.length;j++){
               for(int i = 0;i<pizza[j].length;i++){
                   if(pizza[j][i] != 'B'){
                       Slice aux = getSlice(posib,pizza,j,i,min,max);
                       if(filter(aux,min,max)){
                           resp.add(aux);
                        }
                    }
                }
            }
           score = print.getScore(resp);
           if (score > max_now){
               score = print.printPizza(resp);
               System.out.println("CreateFile");
            }
           if (score > max_now){
               max_now = score;
            }
           System.out.println("Score: "+score+" Max_now: "+max_now);
           if(score > 49000){
               System.out.print("UEEE");
            }
           pizza = clone(clone);
           resp = null;
           resp = new ArrayList();                       
        }
       //score = print.printPizza(resp);
       //print.printMatrix(pizza);
       return score;
    }
    public static Slice getSlice(int[][] posib,char[][] pizza,int row,int column,int min,int max){
        Slice portion = new Slice(0);
        boolean error = false;
        for(int k = 0;k<posib.length*posib.length;k++){
            int selector = (int)(Math.random()*posib.length);
            for(int i = row;i<row+posib[selector][0];i++){                
                for(int j = column; j < column + posib[selector][1];j++){                    
                    if(i < pizza.length && j < pizza[0].length ){
                        if(pizza[i][j] == 'B'){
                            error = true;
                            break;
                        }
                        portion.setSlice(i,j,pizza[i][j]);
                        pizza[i][j] = 'B';
                    }
                }
                if(error){
                    error = false;
                    break;
                }
            }
            if(filter(portion,min,max)){                
                break;
            }else{
                for(int kk = 0;kk<portion.size();kk++){
                        Rectangle aux = portion.get(kk);
                        pizza[aux.posx][aux.posy] = aux.ingredient;
                    }
                portion = new Slice(0);
            }
        }
        return portion;
    }
    public static int[][] getPosib(int max,int min) {
        int[][] posib = new int[2*max+2*min][2];
        int x,y;
        for(int i = 1;i<max;i++) {
            if((max/i * i) >= 2*min){
                //System.out.println(""+max/i+" x "+i);
                posib[i][0] = max/i;
                posib[i][1] = i;
            }
        } 
        boolean found= false;
        int last = 0;
        for(int i = 1;i<posib.length;i++){
            int aux = posib[i][0];
            int auy = posib[i][1];
            found = false;
            for(int j = 1;j<posib.length;j++){ 
                if(posib[j][1] == aux && posib[j][0] == auy){
                    found = true;
                }
                if(posib[j][1]== 0){
                    last = j;
                    break;                    
                }
            }
            if(!found){
                posib[last][0]=auy;
                posib[last][1]=aux;
            }
        }
        int counter = 0;
        for(int i = 1;i<posib.length;i++){
            if(posib[i][1] != 0){
                counter++;
            }
        }
        int[][] posibRet = new int[counter][2];
        counter = 0;
        for(int i = 1;i<posib.length;i++){
            if(posib[i][1] != 0){
                posibRet[counter][0] = posib[i][0];
                posibRet[counter][1] = posib[i][1];
                counter++;
            }
        }
        for(int i = 0;i<posibRet.length;i++){            
            //System.out.println(""+posibRet[i][0]+" x "+posibRet[i][1]);            
        }       
        return posibRet;
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
    public static char[][] clone(char[][] pizza){
        char[][] ret = new char[pizza.length][pizza[0].length];
        for(int i = 0; i < pizza.length;i++){
            for(int j = 0; j < pizza[i].length;j++){
               ret[i][j] = pizza[i][j]; 
            }            
        }
        return ret;
    }
}
