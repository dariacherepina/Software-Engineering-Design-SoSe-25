import java.util.Scanner;
import java.util.InputMismatchException;


public class Ulam {


    public static void main(String[] args) {
        for(int i = 1; i < 1000000; i++) {
            int counter = 0;
            if(ulam(i)){
                System.out.println("Number " + i);
            }
        }
    }

    // case a
    private static boolean ulam(int n){
        int counter = 0;
        while(true){
            if(n == 1) {
                System.out.println("Done at with the nr iterations: " + counter);
                return true;
            }else if (n % 2 == 0){
                n = n/2;
                counter++;
            }else if (n % 2 != 0){
                n = n * 3 + 1;
                counter++;
            }
        }
    }



    //case b
    private static boolean ulam02(int n, int counter){
        if(n == 1) {
            System.out.println("Done at with the nr iterations: " + counter);
            counter = 0;
            return true;
        }else if (n % 2 == 0){
            n = n/2;
            counter++;
            return ulam02(n,counter);
        }else if (n % 2 != 0){
            n = n * 3 + 1;
            counter++;
            return ulam02(n, counter);
        }
        return false;
    }


}
