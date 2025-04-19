import java.util.Scanner;
import java.util.InputMismatchException;


public class Ulam {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter any positive integer: ");
        int n = scanner.nextInt();
        int result = ulam(n);
        System.out.println(result);
    }
    private static int ulam(int n){

        while(n < 1000000){
            if(n == 1) {
                System.out.println("Done");
                return 1;
            }else if (n % 2 == 0){
                n = n/2;
                System.out.println("Even:" + n);
            }else if (n % 2 != 0){
                n = n * 3 + 1;
                System.out.println("Odd:" + n);
            }
        }


        return n;
    }

}
