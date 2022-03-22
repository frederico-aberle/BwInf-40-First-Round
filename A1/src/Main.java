import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File inputFile = new File("input/parkplatz5.txt");
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String firstCar = fileScanner.next();
        String lastCar = fileScanner.next();
        int n = fileScanner.nextInt();

        HashMap<String, Integer> horizontalCars = new HashMap<>();
        String[] parkRow = new String[lastCar.charAt(0) - firstCar.charAt(0) + 1];

        for (int i = 0; i < n; i++) {
            char name = fileScanner.next().charAt(0);
            int p = fileScanner.nextInt();
            horizontalCars.put(name+"", p);
            parkRow[p] = name+"";
            parkRow[p+1] = name+"";
        }

        Schiebeparkplatz s = new Schiebeparkplatz(firstCar, lastCar, n, horizontalCars, parkRow);

        s.solution();
    }
}
