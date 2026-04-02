import java.util.ArrayList;
import java.util.Random;

class Tests {
    private static Random random = new Random();

    private static int sizes[] = { 1000, 5000, 10000, 50000, 75000, 100000, 500000 };
    private static String types[] = { "RANDOM", "SORTED", "REVERSED" };
    private static int iterations = 10;

    public static void main(String args[]) {
        for (int size : sizes) {
            System.out.println("Sorting list of size: " + size + " by QUICKSORT");
            for (String type : types) {
                System.out.println("    List type: " + type);
                System.out.print("    Iterations time taken(ms): ");
                testSort(type, size);
            }
            System.out.println("");
        }
    }

    private static void testSort(String type, int size) {
        ArrayList<Integer> list = new ArrayList<Integer>();

        long totalTimeMS = 0;

        for (int i = 0; i < iterations; i++) {
            list.clear();
            generateList(list, type, size);

            long timeTakenMS = QuickSort.sortTimeMillis(list);

            totalTimeMS += timeTakenMS;

            System.out.print(timeTakenMS + ", ");
        }

        long averageTimeMS = totalTimeMS / iterations;

        System.out.println("");
        System.out.println("    Average time taken(ms): " + averageTimeMS);
        System.out.println("");
    }

    private static void generateList(ArrayList<Integer> list, String type, int size) {
        list.clear();

        for (int i = 0; i < size; i++) {
            if (type.equals("RANDOM")) {
                list.add(random.nextInt(499000) + 1000);
            } else if (type.equals("SORTED")) {
                list.add(i);
            } else if (type.equals("REVERSED")) {
                list.add(size - 1 - i);
            }
        }
    }
}

class QuickSort {
    public static long sortTimeMillis(ArrayList<Integer> list) {
        long startTime = System.currentTimeMillis();
        sort(list, 0, list.size() - 1);
        long endTime = System.currentTimeMillis();

        return endTime - startTime;
    }

    private static void sort(ArrayList<Integer> list, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivotIndex = partition(list, left, right);

        sort(list, left, pivotIndex - 1);
        sort(list, pivotIndex + 1, right);
    }

    private static int partition(ArrayList<Integer> list, int left, int right) {
        int pivot = movePivotToRight(list, left, right);
        int newPivotIndex = left;

        for (int i = left; i < right; i++) {
            if (list.get(i) <= pivot) {
                swap(list, i, newPivotIndex);
                newPivotIndex++;
            }
        }

        swap(list, right, newPivotIndex);

        return newPivotIndex;
    }

    private static int movePivotToRight(ArrayList<Integer> list, int left, int right) {
        int pivotIndex = (left + right) / 2;
        swap(list, pivotIndex, right);

        return list.get(right);
    }

    private static void swap(ArrayList<Integer> list, int i1, int i2) {
        int temp = list.get(i1);
        list.set(i1, list.get(i2));
        list.set(i2, temp);
    }
}
