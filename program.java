import java.util.Scanner;
public class program {
    public static void main(String [] args) throws Exception{
        Scanner kbd = new Scanner(System.in);
        System.out.print("¿Quieres realizar algun test?  ");
        String algo;
        String name = kbd.nextLine();        
        name.toLowerCase();
        if(name.equals("si")){
            Tests test = new Tests();
            test.testLauncher();
        }else{
            while(1==1){
                System.out.print("Introduce nombre archivo sin extension:  ");        
                name = kbd.nextLine();      
                if(name.equals("exit")){System.exit(0);}
                Inputfile algorithm = new Inputfile();
                algorithm.input(name);
            }
        }
    }   
}
