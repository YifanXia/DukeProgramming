public class MultipleOccurencesFinder {

    public int howMany(String stringA, String stringB) {
        int count = 0;
        int index = stringB.indexOf(stringA);
        while (true) {
            if (index == -1) {
                break;
            }
            count ++;
            index = stringB.indexOf(stringA, index + stringA.length());
        }
        return count;
    }

    public void testHowMany() {
        String stringB = "ATGAACGAATTGAATC";
        String stringA = "GAA";
        int count = howMany(stringA, stringB);
        if (count != 3) throw new AssertionError("Wrong count");
    }

    public static void main(String[] args) {
        MultipleOccurencesFinder multipleOccurencesFinder = new MultipleOccurencesFinder();
        multipleOccurencesFinder.testHowMany();
    }
}
