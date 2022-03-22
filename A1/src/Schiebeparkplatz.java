import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Schiebeparkplatz {

    String firstCar;
    String lastCar;
    int n;
    HashMap<String, Integer> horizontalCars;
    String[] parkRow;

    public Schiebeparkplatz(String firstCar, String lastCar, int n, HashMap<String, Integer> horizontalCars, String[] parkRow) {
        this.firstCar = firstCar;
        this.lastCar = lastCar;
        this.n = n;
        this.horizontalCars = horizontalCars;
        this.parkRow = parkRow;
    }

    public HashMap<Character, String> solution() {

        HashMap<Character, String> result = new HashMap<>();

        for (int i = 0; i < parkRow.length; i++) {
            if (parkRow[i] == null) {
                // no blocking car

                result.put((char) ('A'+i), "");
            } else {
                // blocking car

                LinkedList<String> shiftsLeft = new LinkedList<>();
                shiftsLeft = shiftCarsToLeft(shiftsLeft, parkRow[i], i);
                LinkedList<String> shiftsRight = new LinkedList<>();
                shiftsRight = shiftCarsToRight(shiftsRight, parkRow[i], i);

                // compare the solutions shiftsLeft and shiftsRight
                String concatenated;
                if (shiftsRight == null || (shiftsLeft != null && shiftsLeft.size() <= shiftsRight.size())) {
                    concatenated = shiftsLeft.stream()
                            .collect(Collectors.joining(", "));
                } else {
                    concatenated = shiftsRight.stream()
                            .collect(Collectors.joining(", "));
                }
                result.put((char) ('A'+i), concatenated);
            }
        }

        printSolution(result);

        return result;
    }

    public void printSolution(HashMap<Character, String> result) {
        for (int i = 0; i < parkRow.length; i++) {
            System.out.println(((char) ('A'+i)) + ": " + result.get((char) ('A'+i)));
        }
    }

    public LinkedList<String> shiftCarsToLeft(LinkedList<String> shiftsLeft, String prevCar, int prevKeyPosition) {

        // compute numShifts and keyPosition
        int numShifts = 1;
        if (horizontalCars.get(prevCar) == prevKeyPosition) {
            numShifts++;
        }
        int keyPosition = prevKeyPosition - 2;

        // base case 1 - where the keyPosition is out of border
        if (keyPosition < 0) {
            shiftsLeft = null;
            return shiftsLeft;
        }
        // base case 2 - where no horizontal car exists on the key position
        if (parkRow[keyPosition] == null) {
            shiftsLeft.addFirst(prevCar + " " + numShifts + " links");
            return shiftsLeft;
        }

        // recursion
        shiftsLeft.addFirst(prevCar + " " + numShifts + " links");
        LinkedList<String> shiftsLeftCopy = (LinkedList<String>) shiftsLeft.clone();
        shiftsLeft = shiftCarsToLeft(shiftsLeftCopy, parkRow[keyPosition], keyPosition);
        return shiftsLeft;
    }

    public LinkedList<String> shiftCarsToRight(LinkedList<String> shiftsRight, String prevCar, int prevKeyPosition) {

        // compute numShifts and keyPosition
        int numShifts = 1;
        if (horizontalCars.get(prevCar) != prevKeyPosition) {
            numShifts++;
        }
        int keyPosition = prevKeyPosition + 2;

        // base case 1 - where the keyPosition is out of border
        if (keyPosition >= parkRow.length) {
            shiftsRight = null;
            return shiftsRight;
        }
        // base case 2 - where no horizontal car exists on the key position
        if (parkRow[keyPosition] == null) {
            shiftsRight.addFirst(prevCar + " " + numShifts + " rechts");
            return shiftsRight;
        }

        // recursion
        shiftsRight.addFirst(prevCar + " " + numShifts + " rechts");
        LinkedList<String> shiftsRightCopy = (LinkedList<String>) shiftsRight.clone();
        shiftsRight = shiftCarsToRight(shiftsRightCopy, parkRow[keyPosition], keyPosition);
        return shiftsRight;
    }
}
