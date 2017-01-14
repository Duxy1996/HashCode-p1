import java.io.*;
import java.util.*;
public class OutputFile {
    public int printPizza( ArrayList<Slice> resp) throws Exception{
       double name = Math.random();
       int trozosvacios = 0;
       int score = 0;
       System.out.println();     
       PrintWriter writer = new PrintWriter("Respuesta_"+name+".txt");
       for(Slice aux : resp){
           try{
                 System.out.println(aux.get(0).posx+" "+aux.get(0).posy+" "+aux.get(aux.getScore()-1).posx+" "+aux.get(aux.getScore()-1).posy+" ");
                writer.println(aux.get(0).posx+" "+aux.get(0).posy+" "+aux.get(aux.getScore()-1).posx+" "+aux.get(aux.getScore()-1).posy+" ");
           }catch(Exception e){
                    trozosvacios++;
           }
           //System.out.println(aux.toString());           
            score = aux.getScore()+score;
        }
       writer.close();
       System.out.println(resp.size()-trozosvacios);    
       return score;
    }   
    public void printMatrix(int[][] matrix){
        for(int i = 0; i<matrix.length-1;i++){
            for(int j = 0;j<matrix[i].length-1;j++){
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }
}
