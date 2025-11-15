import java.util.Scanner;
import java.util.ArrayList;

public class StatisticsCalculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("=== Mean, Median, Mode Calculator ===");
            System.out.println("Choose the data type to enter:");
            System.out.println("1. Individual Data");
            System.out.println("2. Discrete Data");
            System.out.println("3. Continuous Data");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1, 2, 3, or 4): ");

            int mainChoice = getValidatedInt(sc, 1, 4);

            if (mainChoice == 4) {
                System.out.println("Thank you for using the calculator.");
                break;
            }

            if (mainChoice == 1) {
                handleIndividualData(sc);
            } else if (mainChoice == 2) {
                handleDiscreteData(sc);
            } else {
                handleContinuousData(sc);
            }
            System.out.println("\nWould you like to perform another calculation? (1. Yes / 2. No)");
            int again = getValidatedInt(sc, 1, 2);
            if (again != 1) {
                System.out.println("Thank you for using the calculator.");
                break;
            }
            System.out.println();
        }
        sc.close();
    }

    // Helper method for validated integer input
    static int getValidatedInt(Scanner sc, int min, int max) {
        while (true) {
            if (sc.hasNextInt()) {
                int val = sc.nextInt();
                if (val >= min && val <= max) {
                    return val;
                }
            } else {
                sc.next(); // Skip invalid input
            }
            System.out.print("Please enter a valid option (" + min + "-" + max + "): ");
        }
    }

    static void handleIndividualData(Scanner sc) {
        System.out.println("\nIndividual Data Selected.");
        System.out.println("Choose calculation:");
        System.out.println("1. Mean");
        System.out.println("2. Median");
        System.out.println("3. Mode");
        System.out.print("Enter your choice (1-3): ");
        int choice = getValidatedInt(sc, 1, 3);

        if (choice == 1) {
            meanIndividualData(sc);
        } else if (choice == 2) {
            medianIndividualData(sc);
        } else {
            modeIndividualData(sc);
        }
    }

    static void meanIndividualData(Scanner sc) {
        System.out.print("How many values will you enter? ");
        int n = getValidatedInt(sc, 1, Integer.MAX_VALUE);
        int[] data = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            System.out.print("x" + (i + 1) + " = ");
            data[i] = sc.nextInt();
            sum += data[i];
        }
        double mean = (double) sum / n;
        System.out.print("Mean = (");
        for (int i = 0; i < n; i++) {
            System.out.print(data[i]);
            if (i < n - 1) System.out.print(" + ");
        }
        System.out.println(") / " + n);
        System.out.println("Mean = " + sum + " / " + n);
        System.out.println("Mean = " + mean);
    }

    static void medianIndividualData(Scanner sc) {
        System.out.print("How many values will you enter? ");
        int n = getValidatedInt(sc, 1, Integer.MAX_VALUE);
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("x" + (i + 1) + " = ");
            data[i] = sc.nextInt();
        }
        java.util.Arrays.sort(data);
        System.out.print("Sorted data: ");
        for (int i = 0; i < n; i++) {
            System.out.print(data[i] + (i < n - 1 ? ", " : "\n"));
        }
        double median;
        if (n % 2 == 1) {
            int medianIndex = n / 2;
            System.out.println("Median is value at position " + (medianIndex + 1) + " in sorted data.");
            median = data[medianIndex];
        } else {
            int left = (n / 2) - 1;
            int right = n / 2;
            System.out.println("Median is average of values at positions " + (left + 1) + " and " + (right + 1) + ".");
            median = (data[left] + data[right]) / 2.0;
        }
        System.out.println("Median = " + median);
    }

    static void modeIndividualData(Scanner sc) {
        System.out.print("How many values will you enter? ");
        int n = getValidatedInt(sc, 1, Integer.MAX_VALUE);
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("x" + (i + 1) + " = ");
            data[i] = sc.nextInt();
        }
        java.util.HashMap<Integer, Integer> freqMap = new java.util.HashMap<>();
        for (int value : data) {
            freqMap.put(value, freqMap.getOrDefault(value, 0) + 1);
        }
        int maxFreq = 0;
        for (int freq : freqMap.values()) {
            if (freq > maxFreq) maxFreq = freq;
        }
        java.util.ArrayList<Integer> modes = new java.util.ArrayList<>();
        for (int key : freqMap.keySet()) {
            if (freqMap.get(key) == maxFreq) {
                modes.add(key);
            }
        }
        System.out.println("Frequencies:");
        for (int key : freqMap.keySet()) {
            System.out.println(key + ": " + freqMap.get(key) + " times");
        }
        if (maxFreq == 1) {
            System.out.println("All values have the same frequency, so there is no mode (data is uniform).");
        } else if (modes.size() == 1) {
            System.out.println("Mode is " + modes.get(0) + " (appears " + maxFreq + " times).");
        } else {
            System.out.print("Modes are: ");
            for (int i = 0; i < modes.size(); i++) {
                System.out.print(modes.get(i));
                if (i < modes.size() - 1) System.out.print(", ");
            }
            System.out.println(" (each appears " + maxFreq + " times)");
        }
    }

    static void handleDiscreteData(Scanner sc) {
        System.out.println("\nDiscrete Data Selected.");
        System.out.println("Choose calculation:");
        System.out.println("1. Mean");
        System.out.println("2. Median");
        System.out.println("3. Mode");
        System.out.print("Enter your choice (1-3): ");
        int choice = getValidatedInt(sc, 1, 3);

        if (choice == 1) {
            meanDiscreteData(sc);
        } else if (choice == 2) {
            medianDiscreteData(sc);
        } else {
            modeDiscreteData(sc);
        }
    }

    static void meanDiscreteData(Scanner sc) {
        int n;
        int[] xi = null;
        int[] fi = null;

        while (true) {
            System.out.print("How many (xi, fi) pairs will you enter? ");
            n = getValidatedInt(sc, 1, Integer.MAX_VALUE);
            xi = new int[n];
            fi = new int[n];

            System.out.println("Enter your data in the format: xi fi");
            for (int i = 0; i < n; i++) {
                System.out.print("Pair " + (i + 1) + " (xi, fi): ");
                xi[i] = sc.nextInt();
                fi[i] = sc.nextInt();
                if (fi[i] < 0) {
                    System.out.println("Frequency cannot be negative! Re-enter this pair.");
                    i--;
                }
            }
            System.out.println("You entered:");
            System.out.println("----------------------");
            System.out.println("|   xi   |   fi      |");
            System.out.println("----------------------");
            for (int i = 0; i < n; i++) {
                System.out.printf("|  %4d  |  %4d     |\n", xi[i], fi[i]);
            }
            System.out.println("----------------------");
            System.out.println("Is this data correct? (1. Yes   2. No)");
            int correct = getValidatedInt(sc, 1, 2);
            if (correct == 1) break;
            System.out.println("Let's re-enter your data.");
        }
        int sumFixi = 0, sumFi = 0;
        System.out.print("Calculation steps:\nΣfi*xi = ");
        for (int i = 0; i < xi.length; i++) {
            int product = xi[i] * fi[i];
            sumFixi += product;
            System.out.print(fi[i] + "*" + xi[i]);
            if (i < xi.length - 1) System.out.print(" + ");
        }
        System.out.println(" = " + sumFixi);

        System.out.print("Σfi = ");
        for (int i = 0; i < fi.length; i++) {
            sumFi += fi[i];
            System.out.print(fi[i]);
            if (i < fi.length - 1) System.out.print(" + ");
        }
        System.out.println(" = " + sumFi);

        double mean = (double) sumFixi / sumFi;
        System.out.println("Mean = Σfi*xi / Σfi = " + sumFixi + " / " + sumFi + " = " + mean);
    }

    static void medianDiscreteData(Scanner sc) {
        int n;
        int[] xi = null;
        int[] fi = null;

        while (true) {
            System.out.print("How many (xi, fi) pairs will you enter? ");
            n = getValidatedInt(sc, 1, Integer.MAX_VALUE);
            xi = new int[n];
            fi = new int[n];

            System.out.println("Enter your data in the format: xi fi");
            for (int i = 0; i < n; i++) {
                System.out.print("Pair " + (i + 1) + " (xi, fi): ");
                xi[i] = sc.nextInt();
                fi[i] = sc.nextInt();
                if (fi[i] < 0) {
                    System.out.println("Frequency cannot be negative! Re-enter this pair.");
                    i--;
                }
            }
            System.out.println("You entered:");
            System.out.println("----------------------");
            System.out.println("|   xi   |   fi      |");
            System.out.println("----------------------");
            for (int i = 0; i < n; i++) {
                System.out.printf("|  %4d  |  %4d     |\n", xi[i], fi[i]);
            }
            System.out.println("----------------------");
            System.out.println("Is this data correct? (1. Yes   2. No)");
            int correct = getValidatedInt(sc, 1, 2);
            if (correct == 1) break;
            System.out.println("Let's re-enter your data.");
        }

        // Sort the data
        for (int i = 0; i < xi.length - 1; i++) {
            for (int j = i + 1; j < xi.length; j++) {
                if (xi[i] > xi[j]) {
                    int tempX = xi[i], tempF = fi[i];
                    xi[i] = xi[j]; fi[i] = fi[j];
                    xi[j] = tempX; fi[j] = tempF;
                }
            }
        }
        int totalValues = 0;
        for (int f : fi) totalValues += f;
        if (totalValues == 0) {
            System.out.println("All frequencies are zero. Median is undefined.");
            return;
        }
        System.out.print("Expanded ordered data (for explanation): ");
        for (int i = 0; i < xi.length; i++) {
            for (int f = 0; f < fi[i]; f++) {
                System.out.print(xi[i]);
                if (!(i == xi.length - 1 && f == fi[i] - 1)) System.out.print(", ");
            }
        }
        System.out.println();

        double median;
        if (totalValues % 2 == 1) {
            int medianPos = totalValues / 2 + 1;
            System.out.println("Total values = " + totalValues + " (odd). Median is at position " + medianPos);
            int count = 0, medianValue = -1;
            for (int i = 0; i < xi.length; i++) {
                count += fi[i];
                if (count >= medianPos) {
                    medianValue = xi[i];
                    break;
                }
            }
            median = medianValue;
        } else {
            int pos1 = totalValues / 2, pos2 = totalValues / 2 + 1;
            System.out.println("Total values = " + totalValues + " (even). Median is average of values at " + pos1 + " and " + pos2);
            int count = 0, value1 = -1, value2 = -1;
            for (int i = 0; i < xi.length; i++) {
                int prev = count + 1;
                count += fi[i];
                if (value1 == -1 && count >= pos1) value1 = xi[i];
                if (value2 == -1 && count >= pos2) value2 = xi[i];
            }
            median = (value1 + value2) / 2.0;
        }
        System.out.println("Median = " + median);
    }

    static void modeDiscreteData(Scanner sc) {
        int n;
        int[] xi = null;
        int[] fi = null;

        while (true) {
            System.out.print("How many (xi, fi) pairs will you enter? ");
            n = getValidatedInt(sc, 1, Integer.MAX_VALUE);
            xi = new int[n];
            fi = new int[n];

            System.out.println("Enter your data in the format: xi fi");
            for (int i = 0; i < n; i++) {
                System.out.print("Pair " + (i + 1) + " (xi, fi): ");
                xi[i] = sc.nextInt();
                fi[i] = sc.nextInt();
                if (fi[i] < 0) {
                    System.out.println("Frequency cannot be negative! Re-enter this pair.");
                    i--;
                }
            }
            System.out.println("You entered:");
            System.out.println("----------------------");
            System.out.println("|   xi   |   fi      |");
            System.out.println("----------------------");
            for (int i = 0; i < n; i++) {
                System.out.printf("|  %4d  |  %4d     |\n", xi[i], fi[i]);
            }
            System.out.println("----------------------");
            System.out.println("Is this data correct? (1. Yes   2. No)");
            int correct = getValidatedInt(sc, 1, 2);
            if (correct == 1) break;
            System.out.println("Let's re-enter your data.");
        }

        int fmax = fi[0];
        for (int i = 1; i < fi.length; i++) {
            if (fi[i] > fmax) fmax = fi[i];
        }
        ArrayList<Integer> modes = new ArrayList<>();
        for (int i = 0; i < xi.length; i++) {
            if (fi[i] == fmax) modes.add(xi[i]);
        }
        boolean allFreqOne = true;
        for (int f : fi) if (f != 1) allFreqOne = false;
        System.out.println("Maximum frequency (fmax) = " + fmax);
        if (allFreqOne) {
            System.out.println("All values occur only once (fi=1). No mode (uniform data).");
        } else if (modes.size() == 1) {
            System.out.println("Mode is " + modes.get(0) + " (fi = " + fmax + ").");
        } else {
            System.out.print("Modes are: ");
            for (int i = 0; i < modes.size(); i++) {
                System.out.print(modes.get(i));
                if (i < modes.size() - 1) System.out.print(", ");
            }
            System.out.println(" (each with fi = " + fmax + ")");
            System.out.println("Data is multimodal.");
        }
    }

    static void handleContinuousData(Scanner sc) {
        System.out.println("\nContinuous Data Selected.");
        System.out.println("Choose calculation:");
        System.out.println("1. Mean");
        System.out.println("2. Median");
        System.out.println("3. Mode");
        System.out.print("Enter your choice (1-3): ");
        int choice = getValidatedInt(sc, 1, 3);

        if (choice == 1) {
            meanContinuousData(sc);
        } else if (choice == 2) {
            medianContinuousData(sc);
        } else {
            modeContinuousData(sc);
        }
    }

    static void meanContinuousData(Scanner sc) {
        int n;
        double[] lower = null, upper = null, mid = null;
        int[] fi = null;

        while (true) {
            System.out.print("How many class intervals? ");
            n = getValidatedInt(sc, 1, Integer.MAX_VALUE);
            lower = new double[n];
            upper = new double[n];
            mid = new double[n];
            fi = new int[n];

            System.out.println("Enter intervals and frequency: lower upper fi (e.g. 10 20 5)");
            for (int i = 0; i < n; i++) {
                System.out.print("Interval " + (i + 1) + " (lower upper fi): ");
                lower[i] = sc.nextDouble();
                upper[i] = sc.nextDouble();
                fi[i] = sc.nextInt();
                if (fi[i] < 0) {
                    System.out.println("Frequency cannot be negative! Re-enter.");
                    i--;
                } else if (upper[i] <= lower[i]) {
                    System.out.println("Upper limit must be greater. Re-enter.");
                    i--;
                } else {
                    mid[i] = (lower[i] + upper[i]) / 2.0;
                }
            }
            System.out.println("You entered:");
            System.out.println("------------------------------------");
            System.out.println("| Interval  |  Mid   |   fi        |");
            System.out.println("------------------------------------");
            for (int i = 0; i < n; i++) {
                System.out.printf("| %5.1f-%5.1f | %6.2f | %4d       |\n", lower[i], upper[i], mid[i], fi[i]);
            }
            System.out.println("------------------------------------");
            System.out.println("Is this data correct? (1. Yes   2. No)");
            int correct = getValidatedInt(sc, 1, 2);
            if (correct == 1) break;
            System.out.println("Let's re-enter your data.");
        }

        int sumFi = 0;
        double sumFimidi = 0;
        System.out.print("Σfi*mid = ");
        for (int i = 0; i < n; i++) {
            sumFi += fi[i];
            sumFimidi += fi[i] * mid[i];
            System.out.print(fi[i] + "*" + mid[i]);
            if (i < n - 1) System.out.print(" + ");
        }
        System.out.println(" = " + sumFimidi);

        System.out.print("Σfi = ");
        for (int i = 0; i < n; i++) {
            System.out.print(fi[i]);
            if (i < n - 1) System.out.print(" + ");
        }
        System.out.println(" = " + sumFi);

        double mean = sumFimidi / sumFi;
        System.out.println("Mean = Σfi*mid / Σfi = " + sumFimidi + " / " + sumFi + " = " + mean);
    }

    static void medianContinuousData(Scanner sc) {
        int n;
        double[] lower = null, upper = null;
        int[] fi = null;

        while (true) {
            System.out.print("How many class intervals? ");
            n = getValidatedInt(sc, 1, Integer.MAX_VALUE);
            lower = new double[n];
            upper = new double[n];
            fi = new int[n];

            System.out.println("Enter intervals and frequency: lower upper fi (e.g. 10 20 5)");
            for (int i = 0; i < n; i++) {
                System.out.print("Interval " + (i + 1) + " (lower upper fi): ");
                lower[i] = sc.nextDouble();
                upper[i] = sc.nextDouble();
                fi[i] = sc.nextInt();
                if (fi[i] < 0) {
                    System.out.println("Frequency cannot be negative! Re-enter.");
                    i--;
                } else if (upper[i] <= lower[i]) {
                    System.out.println("Upper limit must be greater. Re-enter.");
                    i--;
                }
            }
            System.out.println("You entered:");
            System.out.println("------------------------------------");
            System.out.println("| Interval  |   fi        |");
            System.out.println("------------------------------------");
            for (int i = 0; i < n; i++) {
                System.out.printf("| %5.1f-%5.1f | %4d       |\n", lower[i], upper[i], fi[i]);
            }
            System.out.println("------------------------------------");
            System.out.println("Is this data correct? (1. Yes   2. No)");
            int correct = getValidatedInt(sc, 1, 2);
            if (correct == 1) break;
            System.out.println("Let's re-enter your data.");
        }

        int[] cf = new int[n];
        cf[0] = fi[0];
        for (int i = 1; i < n; i++) {
            cf[i] = cf[i - 1] + fi[i];
        }
        int N = cf[n - 1];
        if (N == 0) {
            System.out.println("Sum of frequencies is zero. Median is undefined.");
            return;
        }
        double medianPosition = N / 2.0;
        System.out.println("Cumulative frequencies: ");
        for (int i = 0; i < n; i++) {
            System.out.println("cf[" + i + "] = " + cf[i]);
        }

        int medianClass = -1;
        for (int i = 0; i < n; i++) {
            if (cf[i] >= medianPosition) {
                medianClass = i;
                break;
            }
        }
        if (medianClass == -1) {
            System.out.println("Cannot locate median class.");
            return;
        }
        double l = lower[medianClass];
        int f = fi[medianClass];
        int F = medianClass == 0 ? 0 : cf[medianClass - 1];
        double h = upper[medianClass] - lower[medianClass];

        System.out.println("Median class: " + lower[medianClass] + "–" + upper[medianClass]);
        System.out.println("Applying formula:");
        System.out.println("Median = l + [(N/2 - F) / f] * h");
        System.out.println("l = " + l + ", N = " + N + ", F = " + F + ", f = " + f + ", h = " + h);

        double median = l + ((medianPosition - F) / f) * h;
        System.out.println("Median = " + l + " + ((" + medianPosition + " - " + F + ") / " + f + ") * " + h + " = " + median);
    }

    static void modeContinuousData(Scanner sc) {
        int n;
        double[] lower = null, upper = null;
        int[] fi = null;

        while (true) {
            System.out.print("How many class intervals? ");
            n = getValidatedInt(sc, 1, Integer.MAX_VALUE);
            lower = new double[n];
            upper = new double[n];
            fi = new int[n];

            System.out.println("Enter intervals and frequency: lower upper fi (e.g. 10 20 5)");
            for (int i = 0; i < n; i++) {
                System.out.print("Interval " + (i + 1) + " (lower upper fi): ");
                lower[i] = sc.nextDouble();
                upper[i] = sc.nextDouble();
                fi[i] = sc.nextInt();
                if (fi[i] < 0) {
                    System.out.println("Frequency cannot be negative! Re-enter.");
                    i--;
                } else if (upper[i] <= lower[i]) {
                    System.out.println("Upper limit must be greater. Re-enter.");
                    i--;
                }
            }
            System.out.println("You entered:");
            System.out.println("------------------------------------");
            System.out.println("| Interval  |   fi        |");
            System.out.println("------------------------------------");
            for (int i = 0; i < n; i++) {
                System.out.printf("| %5.1f-%5.1f | %4d       |\n", lower[i], upper[i], fi[i]);
            }
            System.out.println("------------------------------------");
            System.out.println("Is this data correct? (1. Yes   2. No)");
            int correct = getValidatedInt(sc, 1, 2);
            if (correct == 1) break;
            System.out.println("Let's re-enter your data.");
        }

        int modalIdx = 0;
        for (int i = 1; i < n; i++) {
            if (fi[i] > fi[modalIdx]) {
                modalIdx = i;
            }
        }
        if (fi[modalIdx] == 0) {
            System.out.println("All frequencies are zero. Mode is undefined.");
            return;
        }
        double l = lower[modalIdx];
        int f1 = fi[modalIdx];
        int f0 = modalIdx == 0 ? 0 : fi[modalIdx - 1];
        int f2 = modalIdx == n - 1 ? 0 : fi[modalIdx + 1];
        double h = upper[modalIdx] - lower[modalIdx];

        System.out.println("Modal class: " + lower[modalIdx] + "–" + upper[modalIdx]);
        System.out.println("Applying formula:");
        System.out.println("Mode = l + [(f1 - f0) / (2f1 - f0 - f2)] * h");
        System.out.println("l = " + l + ", f1 = " + f1 + ", f0 = " + f0 + ", f2 = " + f2 + ", h = " + h);

        if ((2 * f1 - f0 - f2) == 0) {
            System.out.println("Cannot compute mode: denominator is zero in formula.");
            return;
        }
        double mode = l + ((double)(f1 - f0) / (2 * f1 - f0 - f2)) * h;
        System.out.println("Mode = " + l + " + ((" + f1 + " - " + f0 + ") / (2*" + f1 + " - " + f0 + " - " + f2 + ")) * " + h + " = " + mode);
    }
}
