package assignment.wemakeprice.domain.logic;

public class CharFilter {
    private static final byte numberMin = 48;
    private static final byte numberMax = 57;
    private static final byte largeAlphabetMin = 65;
    private static final byte largeAlphabetMax = 90;
    private static final byte smallAlphabetMin = 97;
    private static final byte smallAlphabetMax = 122;
    private static final byte leftAngleBracket = 60;
    private static final byte rightAngleBracket = 62;

    public static boolean isNumber(int asciiCode) {
        return asciiCode >= numberMin && asciiCode <= numberMax;
    }

    public static boolean isAlphabet(int asciiCode) {
        return isLargeAlphabet(asciiCode) || isSmallAlphabet(asciiCode);
    }

    public static boolean isLargeAlphabet(int asciiCode) {
        return asciiCode >= largeAlphabetMin && asciiCode <= largeAlphabetMax;
    }

    public static boolean isSmallAlphabet(int asciiCode) {
        return asciiCode >= smallAlphabetMin && asciiCode <= smallAlphabetMax;
    }

    public static boolean isLeftAngleBracket(int asciiCode) {
        return asciiCode == leftAngleBracket;
    }

    public static boolean isRightAngleBracket(int asciiCode) {
        return asciiCode == rightAngleBracket;
    }

}
