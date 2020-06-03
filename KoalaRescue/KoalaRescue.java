import java.util.*;

/**
 * This is an entry for the whole project.
 *
 * @author (Neda)
 * @version (01/06/2020)
 */
public class KoalaRescue
{
    Scanner scanner;
    String username;
    float budget; 
    private Reserve reserve;
    
    public KoalaRescue(){
        username = "";
        budget = 0;
        reserve = new Reserve();
        welcome();
    }
    
    public void welcome(){
        scanner = new Scanner(System.in);
        
        System.out.println("welcome to Neda's tutorial~");
        System.out.println("input your name please:");
        username = scanner.nextLine();
        System.out.println("input budget please:");
        budget = Float.valueOf(scanner.nextLine());
    }
    
    public void print(){
        System.out.println("username: " + username);
        System.out.println("budget: " + budget);
    }
    
  
    
}
