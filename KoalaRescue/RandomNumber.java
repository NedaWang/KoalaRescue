import java.util.Random;
/**
 * Write a description of class RandomNumber here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RandomNumber
{ 
    // generate5percent
    public static boolean isDamaged(){
        int result = new Random().nextInt(20);
        return result == 1;
    }
    
    public static int generateNumberOfHealthyKoala(){
        return new Random().nextInt(10);
    }
    
    public static int generateNumberOfInjuredKoala(){
        return new Random().nextInt(3);
    }
    
    public static int generateNumberOfPredator(){
        return new Random().nextInt(5);
    }
}
