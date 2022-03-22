import java.util.ArrayList;
import java.util.Arrays;

public class Hotels {

    final int maximum_time_difference = 360;

    int n;
    int T;
    int[] t;
    double[] b;
    double[] b_sorted;

    public Hotels(int n, int T, int[] t, double[] b) {
        this.n = n;
        this.T = T;
        this.t = t;
        this.b = b;
        this.b_sorted = Arrays.copyOf(b, b.length);
        Arrays.sort(b_sorted);
    }

    public void solution() {
        for (int i = b_sorted.length-1; i >= 0; i--) {
            double X = b_sorted[i];
            ArrayList<Integer> t_X = new ArrayList<>();
            for (int j = 0; j < b.length; j++) {
                if (b[j] >= X) {
                    t_X.add(t[j]);
                }
            }
            if (isReachable(t_X)) {
                System.out.println(X);
                return;
            }
        }
        System.out.println("No Route is possible");
    }

    public boolean isReachable(ArrayList<Integer> t_X) {
        if (t_X.get(0) > maximum_time_difference) {
            return false;
        }
        int lastPos = 0;
        int j = 0;
        for (int i = 0; i < 4; i++) {
            while (j < t_X.size()-1 && t_X.get(j+1) - lastPos <= maximum_time_difference) {
                j++;
            }
            lastPos = t_X.get(j);
            if (T - lastPos <= maximum_time_difference) {
                return true;
            }
        }
        return false;
    }
}
