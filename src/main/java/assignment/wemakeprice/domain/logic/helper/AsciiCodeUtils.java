package assignment.wemakeprice.domain.logic.helper;

public class AsciiCodeUtils {
    private static final byte NumberMin = 48;
    private static final byte NumberMax = 57;
    private static final byte LargeAlphabetMin = 65;
    private static final byte LargeAlphabetMax = 90;
    private static final byte SmallAlphabetMin = 97;
    private static final byte SmallAlphabetMax = 122;
    private static final byte LeftAngleBracket = 60;
    private static final byte RightAngleBracket = 62;

    public static boolean isNumber(int asciiCode) {
        return asciiCode >= NumberMin && asciiCode <= NumberMax;
    }

    public static boolean isAlphabet(int asciiCode) {
        return isLargeAlphabet(asciiCode) || isSmallAlphabet(asciiCode);
    }

    public static boolean isLargeAlphabet(int asciiCode) {
        return asciiCode >= LargeAlphabetMin && asciiCode <= LargeAlphabetMax;
    }

    public static boolean isSmallAlphabet(int asciiCode) {
        return asciiCode >= SmallAlphabetMin && asciiCode <= SmallAlphabetMax;
    }

    public static boolean isLeftAngleBracket(int asciiCode) {
        return asciiCode == LeftAngleBracket;
    }

    public static boolean isRightAngleBracket(int asciiCode) {
        return asciiCode == RightAngleBracket;
    }

    public static Integer mapToAlphabeticalOrder(int asciiCode) {
        if (isLargeAlphabet(asciiCode)) {
            return (asciiCode - LargeAlphabetMin) * 2;
        } else if (isSmallAlphabet(asciiCode)){
            return ((asciiCode - SmallAlphabetMin) * 2) + 1;
        } else {
            return -1;
        }
    }
}
