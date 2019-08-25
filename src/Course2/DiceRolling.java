package Course2;
import java.util.Random;

public class DiceRolling {

    public void simulate(int rolls) {
        Random rand = new Random();
        int[] counts = new int[13];
        int k;
        for (k = 0; k < rolls; k ++) {
            int d1 = rand.nextInt(6) + 1;
            int d2 = rand.nextInt(6) + 1;
            counts[d1 + d2] ++;
        }

        int i;
        for (i = 2; i < 13; i ++) {
            System.out.println("Sum " + i + " occurred " + counts[i] + " times, which is " + 100.0 * counts[i] / rolls + " percent.");
        }
    }

    public static void main(String[] args) {
        DiceRolling diceRolling = new DiceRolling();
        diceRolling.simulate(10000);
    }

}
