import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class StoringAndSorting {
    private static Random random = new Random();

    public static void main(String args[]) {
        ArrayList<Integer> list = new ArrayList<Integer>();

        int sizes[] = { 1000, 5000, 10000, 50000, 75000, 100000, 500000 };

        for (int size : sizes) {
            generateList(list, size);

            System.out.println("Unsorted " + size);
            printFirst1000List(list);
            saveList(list, "unsorted" + size + ".csv");

            QuickSort.sort(list);

            System.out.println("Sorted " + size);
            printFirst1000List(list);
            saveList(list, "sorted" + size + ".csv");
            System.out.println("");
        }
    }

    private static void generateList(ArrayList<Integer> list, int size) {
        list.clear();

        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(499000) + 1000);
        }
    }

    private static void printFirst1000List(ArrayList<Integer> list) {
        System.out.print(list.get(0));

        for (int i = 1; i < 1000; i++) {
            System.out.print("," + list.get(i));
        }

        System.out.println("");
    }

    private static void saveList(ArrayList<Integer> list, String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.print(list.get(0));

            for (int i = 1; i < list.size(); i++) {
                printWriter.print("," + list.get(i));
            }

            printWriter.println("");

            printWriter.close();
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("Exception caught writing output: " + e);
        }
    }

}

abstract class QuickSort {
    public static void sort(ArrayList<Integer> list) {
        sort(list, 0, list.size() - 1);
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
