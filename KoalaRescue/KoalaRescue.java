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
    Double budget; 
    private Reserve reserve;
    
    public KoalaRescue(){
        username = "";
        budget = 0.0;
        reserve = new Reserve();
        welcome();
    }
    
    public void welcome(){
        scanner = new Scanner(System.in);
        
        System.out.println("Welcome to Neda's tutorial~");
        boolean invalidName = true;
        while (invalidName){
            System.out.println("Input your name please:");
            username = scanner.nextLine();
            if (username.trim().length() > 0 && username.trim().length() <= 16){
                invalidName = false;
            }else{
                System.out.println("Please enter an valid username.");
            }
        }
        boolean invalidAmount = true;
        while (invalidAmount){
            System.out.println("Input budget please:");
            String budgetString = scanner.nextLine();
            if (budgetString.matches("\\d+\\.?\\d*")){
                budget = Double.valueOf(budgetString);
                if (budget >= 100 && budget <= 200){
                    invalidAmount = false;
                }else{
                    System.out.println("Budget should between 100 and 200");
                }
            }else{
                System.out.println("Input is an invalid number.");
            }
        }
        startMission();
    }
    
    public void print(){
        System.out.println("username: " + username);
        System.out.println("budget: " + budget);
    }
    
    public void startMission(){
        reserve.misstion(budget);
    }
  
    
}
