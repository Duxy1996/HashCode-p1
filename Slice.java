import java.util.ArrayList;
public class Slice
{
   private int number_slice;
   private ArrayList<Rectangle> rectangles;
   
   public Slice(int slice){
       this.number_slice = slice;
       rectangles = new ArrayList();
    }
   public Rectangle getfirst(int r){
       return rectangles.get(r);
    }
   public void setSlice(int posx,int posy,int ingre){
       char i;
       if(ingre == 1){
           i = 'T';
        }else{
           i = 'M';
        }      
       Rectangle ingres = new Rectangle(posx,posy,i);      
       rectangles.add(ingres);
    }
   public void removeFirst(){
       try{
           rectangles.remove(0);
        }catch(Exception e){}
    }
   public int getScore(){
       return rectangles.size();
    }
   public String toString(){
       String s = "";
       for(Rectangle r : rectangles){
           s = s+ r.ingredient;
        } 
        return s;
    }
}
