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
    
    public static int generateKoalaAge(){
        return (new Random().nextInt(18))+1;
    }
    
    public static boolean generate80percent(){
        return (new Random().nextInt(100))<80;
    }
    
    public static boolean generate20percent(){
        return (new Random().nextInt(100))<20;
    }
    
    public static boolean generate50percent(){
        return (new Random().nextInt(100))<50;
    }
}
