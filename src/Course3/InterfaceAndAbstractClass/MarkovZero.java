package Course3.InterfaceAndAbstractClass;

public class MarkovZero extends AbstractMarkovModel {
    @Override
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for(int k=0; k < numChars; k++){
            int index = myRandom.nextInt(myText.length());
            sb.append(myText.charAt(index));
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return "Markov model of order 0.";
    }
}
