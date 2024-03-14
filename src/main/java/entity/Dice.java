package entity;
import java.util.Random;
public class Dice {
    private final Random random;

    public Dice() {
        this.random = new Random();
    }
    public int roll() {
        // Rolling a 6-sided dice
        return random.nextInt(6) + 1;
    }
}
