import java.util.Scanner;

public class MAIN {
        public static void main(String[] args) {
            long start = System.currentTimeMillis();
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter size... pls...");
            int size = sc.nextInt();
            int General_Arr [] = new int[size];

            gen ob = new gen();
            General_Arr = ob.Arr(size);


            long time = start - System.currentTimeMillis();
            System.out.println("TIME: "+(-time));}
}
