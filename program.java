import java.util.Scanner;
public class program {
    public static void main(String [] args) throws Exception{
        Scanner kbd = new Scanner(System.in);
        System.out.print("¿Quieres realizar algun test?  ");
        String name = kbd.nextLine();
        name.toLowerCase();
        if(name.equals("si")){
            Tests test = new Tests();
            test.testLauncher();
        }else{
            System.out.print("Introduce nombre archivo sin extension:  ");        
            name = kbd.nextLine();
            Inputfile algorithm = new Inputfile();
            algorithm.input(name);
        }
    }   
}
