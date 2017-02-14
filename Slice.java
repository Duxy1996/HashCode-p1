import java.util.ArrayList;
public class Slice
{
   private int number_slice;
   private ArrayList<Rectangle> rectangles;
   
   public Slice(int slice){
       this.number_slice = slice;
       rectangles = new ArrayList();
    }
   public Rectangle get(int r){
       try{
           return rectangles.get(r);
        }catch(Exception e){return null;}
    }
   public void setSlice(int posx,int posy,char ingre){             
       Rectangle ingres = new Rectangle(posx,posy,ingre);      
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
