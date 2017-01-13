import java.util.ArrayList;
public class Slice
{
   private int number_slice;
   private ArrayList<int[][]> rectangles;
   public Slice(int slice){
       this.number_slice = slice;
       rectangles = new ArrayList();
    }
   public void setSlice(int[][] slice){
       rectangles.add(slice);
    }
   public int[][] getFirst(int pos){
       return rectangles.get(pos);
    }
}
