import java.util.*;
import java.io.*;
public class Tests {
    public static void testLauncher() throws Exception {
        for(int m = 10;m<500;m=m+10){
            int max = 6;//(int)(Math.random()*20)+11;
            int min = 2;//(int)(Math.random()*10)+1;
            test(m,m,max,min);
        }
    }   
    public static void test(int x,int y,int max,int min) throws Exception {        
        char[][] pizza = new char[x][y];
        char[][] pizzaT = new char[x][y];
        int contM = 0;
        int contT = 0;
        for(int j = 0;j<x;j++) {
            for(int i = 0;i<y;i++) {
                if(Math.random()<0.3){   
                    pizza[j][i]='M';
                    pizzaT[j][i]='M';
                    contM++;
                }else{
                    pizza[j][i]='T';
                    pizzaT[j][i]='T';
                    contT++;
                }
            }
        }
        Eficient2 testOne = new Eficient2();
        Eficient testTwo = new Eficient(); 
        double xx = (double)testOne.getSlices(contM,contT,pizza,max,min,"smallbig");
        double yy = (double)testTwo.getSlices(contM,contT,pizzaT,max,min,"smallbig");
        //System.out.println("Score for Max: "+max+"and Min: "+min+":"+(xx/(x*y)*100));
        System.out.println("Score alg bad: "+xx+" Max score "+x*y);
        System.out.println("Score alg god: "+yy+" Max score "+x*y);
    }
    public static void test() throws Exception{
        String name = "ejemplos/smallbig.in";
        int x = 1400;//max1400
        int y = 1400;//max1400
        int max = 20;
        int min = 8;
        PrintWriter writer = new PrintWriter(name);
        writer.println(""+x+" "+y+" "+min+" "+max);
        for(int j = 0;j<x;j++) {
            for(int i = 0;i<y;i++) {
                if(Math.random()<0.8){   
                    writer.print("M");
                }else{
                    writer.print("T");
                }
            }
            writer.println();
        }
        writer.close();
    }
}
