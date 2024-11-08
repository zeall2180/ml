import java.util.Scanner;

public class Main {
    
    public static int[] fibonacci(int n) {
        int steps = 0;
        int a = 0, b = 1;
        for (int i = 0; i < n; i++) {
            steps++;  // Counting the step
            int temp = a;
            a = b;
            b = temp + b;
        }
        return new int[]{a, steps};
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

       
        System.out.print("Enter the Fibonacci number position: ");
        int n = sc.nextInt();

        
        int[] resultItr = Main.fibonacci(n);
        System.out.println("Fibonacci of " + n + ": " + resultItr[0] + ", Steps: " + resultItr[1]);
        
        sc.close();  
    }
}
