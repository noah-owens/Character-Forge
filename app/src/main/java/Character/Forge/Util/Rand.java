package Character.Forge.Util;

import java.util.concurrent.ThreadLocalRandom;


/**
 * Singleton Class Rand should hopefully help to keep pseudorandom numbers
 * generated across the program from being too close to each other.
 * <p>
 * @version 0.5.0
 * @author Noah Owens
 */
public class Rand {
    private static Rand randInstance = null;

    /**
     * Access singleton Rand object
     * <p>
     * @return Rand object
     */
    public static Rand getRandInstance() {
        if (randInstance == null) {
            randInstance = new Rand();
        }
        return randInstance;
    }

    /**
     * rollDie(int x) returns a number 'n' for which 1 <= n <= x
     * <p>
     * @param n the dice size being rolled
     * @return integer from [1,n]
     */
    public int rollDie(int n) {
        return ThreadLocalRandom.current().nextInt(1, n + 1);
    }

    /**
     * randInt(int x) returns a number 'n' for which 0 <= n <= x
     * @param n upper bound
     * @return integer from [0,n)
     */
    public int randInt(int n) {
        return ThreadLocalRandom.current().nextInt(0, n);
    }
}
