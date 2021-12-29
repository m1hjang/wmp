package assignment.wemakeprice.domain.logic;

import java.util.List;

public class SortLogic {

    public static void sortAscending(List<Integer> list) {
        quickSort(list, 0, list.size() - 1);
    }

    private static void quickSort(List<Integer> list, int startIndex, int endIndex) {
        int pivot = searchAndSwap(list, startIndex, endIndex);

        if (startIndex < pivot - 1) {
            quickSort(list, startIndex, pivot - 1);
        }

        if (endIndex > pivot) {
            quickSort(list, pivot, endIndex);
        }
    }

    private static int searchAndSwap(List<Integer> list, int startIndex, int endIndex) {
        int pivot = list.get((startIndex + endIndex) / 2);

        int startPoint = startIndex;
        int endPoint = endIndex;

        while (startPoint <= endPoint) {
            while (list.get(startPoint) < pivot) startPoint++;
            while (list.get(endPoint) > pivot) endPoint--;

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
