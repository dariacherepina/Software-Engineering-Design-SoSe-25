import java.util.Scanner;
import java.util.InputMismatchException;


public class Ulam {


    public static void main(String[] args) {
        /*
        scanner is here because:
        it provides automatic resource cleanup aka closes scanner
        and this way I can use scanner in getInput function without closing it there
        */
        try(Scanner scanner = new Scanner(System.in)){
            int n = getInput(scanner);
            int result = ulam(n);
            System.out.println(result);
        }

    }
    private static int ulam(int n){
        int iterations = 1000000;
        for (int i = 0; i < iterations; i++){
            if(n == 1) {
                System.out.println("Done at the iteration " + i);
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

    private static int getInput(Scanner scanner){

        System.out.print("Enter any positive integer: ");
        try{
            int n = scanner.nextInt();
            if (n < 0){
                throw new InputMismatchException();
            }else{
                return n;
            }
        }catch (InputMismatchException e) {
            System.out.println("Please enter a positive integer");
            // Clear invalid input
            scanner.next();
        }
        return 0;
    }
}
