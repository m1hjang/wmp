package assignment.wemakeprice.domain.logic.helper;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class SortHelperTest {

    @Test
    void sortAscending() {
        List<Integer> integers = Arrays.asList(3, 5, 7, 1);
        SortHelper.sortAscending(integers);

        int[] expected = {1, 3, 5, 7};
        int[] actualResult = integers.stream().mapToInt(i -> i).toArray();
        assertArrayEquals(expected, actualResult);
    }

    @Test
    void testSortAscending() {
        List<Integer> asciiCodes = "abABcCdD".chars().boxed().collect(Collectors.toList());
        SortHelper.sortAscending(asciiCodes, (asciiCode) -> {
            if (AsciiCodeUtils.isLargeAlphabet(asciiCode)) {
                return (asciiCode - 65) * 2;
            } else if (AsciiCodeUtils.isSmallAlphabet(asciiCode)){
                return ((asciiCode - 97) * 2) + 1;
            } else {
                return -1;
            }
        });

        int[] expected = {65, 97, 66, 98, 67, 99, 68, 100};
        int[] actualResult = asciiCodes.stream().mapToInt(i -> i).toArray();
        assertArrayEquals(expected, actualResult);
    }
}
