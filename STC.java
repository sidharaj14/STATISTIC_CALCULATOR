import java.util.*;

public class STC {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("=== Mean, Median, Mode Calculator ===");
            System.out.println("Choose data type to enter:");
            System.out.println("1. Individual data");
            System.out.println("2. Discrete data");
            System.out.println("3. Continuous data");
            System.out.println("4. Exit");
            System.out.print("Choice: ");
            int t = getInt(sc, 1, 4);
            if (t == 4) break;

            if (t == 1) workflowIndividual(sc);
            else if (t == 2) workflowDiscrete(sc);
            else workflowContinuous(sc);

            System.out.print("\nDo you want to start again with new data type? (1.Yes / 2.No): ");
            if (getInt(sc, 1, 2) == 2) break;
            System.out.println();
        }
        System.out.println("Thank you for using the calculator.");
    }

    static int getInt(Scanner sc, int min, int max) {
        while (true) {
            if (sc.hasNextInt()) {
                int v = sc.nextInt();
                if (v >= min && v <= max) return v;
            } else sc.next(); // skip invalid
            System.out.print("Enter " + min + "–" + max + ": ");
        }
    }

    // ================= INDIVIDUAL WORKFLOW =================

    static void workflowIndividual(Scanner sc) {
        System.out.print("\nHow many values? ");
        int n = getInt(sc, 1, Integer.MAX_VALUE);
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("x" + (i + 1) + " = ");
            a[i] = sc.nextInt();
        }

        while (true) {
            System.out.println("\nIndividual data: what do you want to find?");
            System.out.println("1. Mean");
            System.out.println("2. Median");
            System.out.println("3. Mode");
            System.out.println("4. Back to main menu");
            System.out.print("Choice: ");
            int op = getInt(sc, 1, 4);
            if (op == 4) break;

            if (op == 1) meanIndividual(a);
            else if (op == 2) medianIndividual(a);
            else modeIndividual(a);
        }
    }

    static void meanIndividual(int[] a) {
        int sum = 0;
        System.out.print("Mean = (");
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
            System.out.print(a[i]);
            if (i < a.length - 1) System.out.print(" + ");
        }
        double mean = (double) sum / a.length;
        System.out.println(")/" + a.length + " = " + mean);
    }

    static void medianIndividual(int[] a) {
        int[] b = a.clone();
        Arrays.sort(b);
        System.out.print("Sorted: ");
        for (int i = 0; i < b.length; i++)
            System.out.print(b[i] + (i < b.length - 1 ? ", " : "\n"));
        double med;
        if (b.length % 2 == 1) med = b[b.length / 2];
        else med = (b[b.length / 2 - 1] + b[b.length / 2]) / 2.0;
        System.out.println("Median = " + med);
    }

    static void modeIndividual(int[] a) {
        Map<Integer,Integer> f = new HashMap<>();
        for (int x : a) f.put(x, f.getOrDefault(x, 0) + 1);
        int max = 0;
        for (int v : f.values()) if (v > max) max = v;
        if (max == 1) {
            System.out.println("No mode (all values frequency 1).");
            return;
        }
        List<Integer> modes = new ArrayList<>();
        for (int x : f.keySet()) if (f.get(x) == max) modes.add(x);
        if (modes.size() == 1)
            System.out.println("Mode = " + modes.get(0) + " (freq " + max + ")");
        else
            System.out.println("Modes = " + modes + " (freq " + max + ")");
    }

    // ================= DISCRETE WORKFLOW =================

    static void workflowDiscrete(Scanner sc) {
        System.out.print("\nHow many (xi, fi) pairs? ");
        int n = getInt(sc, 1, Integer.MAX_VALUE);
        int[] x = new int[n], f = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("xi fi (" + (i + 1) + "): ");
            x[i] = sc.nextInt();
            f[i] = sc.nextInt();
            if (f[i] < 0) { System.out.println("fi must be >= 0"); i--; }
        }
        // sort by xi
        for (int i = 0; i < n - 1; i++)
            for (int j = i + 1; j < n; j++)
                if (x[i] > x[j]) {
                    int tx = x[i], tf = f[i];
                    x[i] = x[j]; f[i] = f[j];
                    x[j] = tx;  f[j] = tf;
                }

        while (true) {
            System.out.println("\nDiscrete data: what do you want to find?");
            System.out.println("1. Mean");
            System.out.println("2. Median");
            System.out.println("3. Mode");
            System.out.println("4. Back to main menu");
            System.out.print("Choice: ");
            int op = getInt(sc, 1, 4);
            if (op == 4) break;

            if (op == 1) meanDiscrete(x, f);
            else if (op == 2) medianDiscrete(x, f);
            else modeDiscrete(x, f);
        }
    }

    static void meanDiscrete(int[] x, int[] f) {
        int sumFi = 0, sumFixi = 0;
        System.out.print("Σfi*xi = ");
        for (int i = 0; i < x.length; i++) {
            sumFi += f[i];
            sumFixi += f[i] * x[i];
            System.out.print(f[i] + "*" + x[i]);
            if (i < x.length - 1) System.out.print(" + ");
        }
        System.out.println(" = " + sumFixi);
        System.out.println("Σfi = " + sumFi);
        System.out.println("Mean = " + (double) sumFixi / sumFi);
    }

    static void medianDiscrete(int[] x, int[] f) {
        int N = 0;
        for (int v : f) N += v;
        if (N == 0) {
            System.out.println("All frequencies 0, median undefined.");
            return;
        }
        int pos1, pos2;
        if (N % 2 == 1) { pos1 = pos2 = N / 2 + 1; }
        else { pos1 = N / 2; pos2 = N / 2 + 1; }

        int count = 0, v1 = -1, v2 = -1;
        for (int i = 0; i < x.length; i++) {
            count += f[i];
            if (v1 == -1 && count >= pos1) v1 = x[i];
            if (v2 == -1 && count >= pos2) v2 = x[i];
        }
        double med = (v1 + v2) / 2.0;
        System.out.println("Median = " + med);
    }

    static void modeDiscrete(int[] x, int[] f) {
        int max = 0;
        for (int v : f) if (v > max) max = v;
        boolean allOne = true;
        for (int v : f) if (v != 1) { allOne = false; break; }
        if (allOne) {
            System.out.println("No mode (all fi = 1).");
            return;
        }
        List<Integer> modes = new ArrayList<>();
        for (int i = 0; i < x.length; i++)
            if (f[i] == max) modes.add(x[i]);
        if (modes.size() == 1)
            System.out.println("Mode = " + modes.get(0) + " (fi = " + max + ")");
        else
            System.out.println("Modes = " + modes + " (fi = " + max + ")");
    }

    // ================= CONTINUOUS WORKFLOW =================

    static void workflowContinuous(Scanner sc) {
        System.out.print("\nHow many class intervals? ");
        int n = getInt(sc, 1, Integer.MAX_VALUE);
        double[] L = new double[n], U = new double[n];
        int[] f = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("lower upper fi (" + (i + 1) + "): ");
            L[i] = sc.nextDouble();
            U[i] = sc.nextDouble();
            f[i] = sc.nextInt();
            if (f[i] < 0 || U[i] <= L[i]) {
                System.out.println("Check: fi >= 0 and upper > lower.");
                i--;
            }
        }

        while (true) {
            System.out.println("\nContinuous data: what do you want to find?");
            System.out.println("1. Mean");
            System.out.println("2. Median");
            System.out.println("3. Mode");
            System.out.println("4. Back to main menu");
            System.out.print("Choice: ");
            int op = getInt(sc, 1, 4);
            if (op == 4) break;

            if (op == 1) meanContinuous(L, U, f);
            else if (op == 2) medianContinuous(L, U, f);
            else modeContinuous(L, U, f);
        }
    }

    static void meanContinuous(double[] L, double[] U, int[] f) {
        double sumFi = 0, sumFim = 0;
        System.out.print("Σfi*mid = ");
        for (int i = 0; i < L.length; i++) {
            double m = (L[i] + U[i]) / 2.0;
            sumFi += f[i];
            sumFim += f[i] * m;
            System.out.print(f[i] + "*" + m);
            if (i < L.length - 1) System.out.print(" + ");
        }
        System.out.println(" = " + sumFim);
        System.out.println("Σfi = " + sumFi);
        System.out.println("Mean = " + sumFim / sumFi);
    }

    static void medianContinuous(double[] L, double[] U, int[] f) {
        int n = f.length;
        int[] cf = new int[n];
        cf[0] = f[0];
        for (int i = 1; i < n; i++) cf[i] = cf[i - 1] + f[i];
        int N = cf[n - 1];
        if (N == 0) {
            System.out.println("All frequencies 0, median undefined.");
            return;
        }
        double N2 = N / 2.0;
        int idx = 0;
        while (idx < n && cf[idx] < N2) idx++;
        double l = L[idx];
        double h = U[idx] - L[idx];
        int F = idx == 0 ? 0 : cf[idx - 1];
        int fm = f[idx];
        double med = l + ((N2 - F) / fm) * h;
        System.out.println("Median class: " + L[idx] + "-" + U[idx]);
        System.out.println("Median = " + med);
    }

    static void modeContinuous(double[] L, double[] U, int[] f) {
        int n = f.length;
        int idx = 0;
        for (int i = 1; i < n; i++)
            if (f[i] > f[idx]) idx = i;
        if (f[idx] == 0) {
            System.out.println("All frequencies 0, mode undefined.");
            return;
        }
        double l = L[idx];
        double h = U[idx] - L[idx];
        int f1 = f[idx];
        int f0 = idx == 0 ? 0 : f[idx - 1];
        int f2 = idx == n - 1 ? 0 : f[idx + 1];
        int den = 2 * f1 - f0 - f2;
        if (den == 0) {
            System.out.println("Cannot compute mode (denominator 0).");
            return;
        }
        double mode = l + ((double)(f1 - f0) / den) * h;
        System.out.println("Modal class: " + L[idx] + "-" + U[idx]);
        System.out.println("Mode = " + mode);
    }
}
