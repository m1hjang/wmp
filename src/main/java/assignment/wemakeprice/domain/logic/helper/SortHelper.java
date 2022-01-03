package assignment.wemakeprice.domain.logic.helper;

import java.util.List;
import java.util.function.Function;

public class SortHelper {

    public static void sortAscending(List<Integer> list) {
        if (list.isEmpty()) {
            return;
        }
        quickSort(list, 0, list.size() - 1, Function.identity());
    }

    public static void sortAscending(List<Integer> list, Function<Integer, Integer> orderMapper) {
        if (list.isEmpty()) {
            return;
        }
        quickSort(list, 0, list.size() - 1, orderMapper);
    }

    private static void quickSort(List<Integer> list,
                                  int startIndex,
                                  int endIndex,
                                  Function<Integer, Integer> orderMapper) {
        int pivotIndex = searchAndSwap(list, startIndex, endIndex, orderMapper);

        if (startIndex < pivotIndex - 1) {
            quickSort(list, startIndex, pivotIndex - 1, orderMapper);
        }

        if (endIndex > pivotIndex) {
            quickSort(list, pivotIndex, endIndex, orderMapper);
        }
    }

    private static int searchAndSwap(List<Integer> list,
                                     int startIndex,
                                     int endIndex,
                                     Function<Integer, Integer> orderMapper) {
        int pivot = orderMapper.apply(list.get((startIndex + endIndex) / 2));

        int startPoint = startIndex;
        int endPoint = endIndex;

        while (startPoint <= endPoint) {
            while (orderMapper.apply(list.get(startPoint)) < pivot) startPoint++;
            while (orderMapper.apply(list.get(endPoint)) > pivot) endPoint--;

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
