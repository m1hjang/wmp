package assignment.wemakeprice.domain.logic.helper;

import java.util.List;
import java.util.function.Function;

public class SortHelper {

    public static void sortAscending(List<Integer> list) {
        quickSort(list, 0, list.size() - 1, Function.identity());
    }

    public static void sortAscending(List<Integer> list, Function<Integer, Integer> order) {
        quickSort(list, 0, list.size() - 1, order);
    }

    private static void quickSort(List<Integer> list, int startIndex, int endIndex, Function<Integer, Integer> order) {
        int pivotIndex = searchAndSwap(list, startIndex, endIndex, order);

        if (startIndex < pivotIndex - 1) {
            quickSort(list, startIndex, pivotIndex - 1, order);
        }

        if (endIndex > pivotIndex) {
            quickSort(list, pivotIndex, endIndex, order);
        }
    }

    private static int searchAndSwap(List<Integer> list, int startIndex, int endIndex, Function<Integer, Integer> order) {
        int pivot = order.apply(list.get((startIndex + endIndex) / 2));

        int startPoint = startIndex;
        int endPoint = endIndex;

        while (startPoint <= endPoint) {
            while (order.apply(list.get(startPoint)) < pivot) startPoint++;
            while (order.apply(list.get(endPoint)) > pivot) endPoint--;

            if (startPoint <= endPoint) {
                swap(list, startPoint, endPoint);
                startPoint++;
                endPoint--;
            }
        }

        return startPoint;
    }

    private static void swap(List<Integer> list, int index1, int index2) {
        int temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }
}
