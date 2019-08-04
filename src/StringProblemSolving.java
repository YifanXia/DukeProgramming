public class StringProblemSolving {

    public static boolean twoOccurences(String stringA, String stringB) {
        int count = 0;
        int lengthA = stringA.length();
        int index = 0;
        while (index != -1) {
            index = stringB.indexOf(stringA, index);
            if (index != -1) {
                count ++;
                index = index + lengthA;
            }
        }

        if (count >= 2) {
            return true;
        } else {
            return false;
        }
    }

    public static String lastPart(String stringA, String stringB) {
        String result = "";
        int lengthA = stringA.length();
        int index = 0;
        int previousIndex = 0;
        while (index != -1) {
            index = stringB.indexOf(stringA, previousIndex);
            if (index == -1) {
                result = stringB.substring(previousIndex);
            }
            else {
                previousIndex = index + lengthA;
            }
        }

        return result;
    }

    public void testing() {

        String stringA = "abc";
        String stringB = "abcccabcaaabc";
        boolean test = twoOccurences(stringA, stringB);
        String lastPartString = lastPart(stringA, stringB);
        System.out.println(test + " " + lastPartString);

        stringA = "abc";
        stringB = "abcccabaaab";
        test = twoOccurences(stringA, stringB);
        lastPartString = lastPart(stringA, stringB);
        System.out.println(test + " " + lastPartString);

        stringA = "abc";
        stringB = "aabcccabaaab";
        test = twoOccurences(stringA, stringB);
        lastPartString = lastPart(stringA, stringB);
        System.out.println(test + " " + lastPartString);

        stringA = "abc";
        stringB = "aa";
        test = twoOccurences(stringA, stringB);
        lastPartString = lastPart(stringA, stringB);
        System.out.println(test + " " + lastPartString);

        stringA = "a";
        stringB = "banan";
        test = twoOccurences(stringA, stringB);
        lastPartString = lastPart(stringA, stringB);
        System.out.println(test + " " + lastPartString);

    }

    public static void main(String[] args) {
        StringProblemSolving solving = new StringProblemSolving();
        solving.testing();
    }

}
