import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File inputFile = new File("input/hotels1.txt");
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int n = fileScanner.nextInt();
        int T = fileScanner.nextInt();
        int[] t = new int[n];
        double[] b = new double[n];
        for (int i = 0; i < n; i++) {
            t[i] = fileScanner.nextInt();
            b[i] = fileScanner.nextDouble();
        }

        Hotels h = new Hotels(n, T, t, b);

        h.solution();
    }
}
