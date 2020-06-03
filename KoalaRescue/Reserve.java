import java.io.*;
import java.util.Scanner;  
import java.util.ArrayList;
/**
 * Write a description of class Reserve here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Reserve
{
    private ObservationPoint[] observationPoints;
    private SafeHaven safeHaven;
    private FileInputStream inputStream;
    private Scanner scanner;
    
    public Reserve(){
        // initialise observation points.
        observationPoints = new ObservationPoint[10];
        safeHaven = new SafeHaven();
        try{
            inputStream = new FileInputStream("trees.txt"); 
            scanner = new Scanner(inputStream);
            initialise();
            scanner.close();
            inputStream.close();
        }catch(IOException e){
            e.printStackTrace(); 
        }
    }
    
    // order: Manna Gum, Swamp Gum, Blue Gum, River Red Gum, Wattle
    public void initialise(){
        int row = 0;

        while(scanner.hasNextLine()){  
             //first row
            ObservationPoint observationPoint = new ObservationPoint(); // new observe point
            ArrayList<ArrayList<Tree>> trees = observationPoint.getTrees();
            
            String[] amountString = scanner.nextLine().split(",");
            
            int amountOfMannaGum = Integer.valueOf(amountString[0]);
            while (amountOfMannaGum > 0){
                trees.get(0).add(new MannaGum());
                //observationPoint.getTrees().add(new MannaGum());
                amountOfMannaGum--;
            }
            
            int amountOfSwampGum = Integer.valueOf(amountString[1]);
            while (amountOfSwampGum > 0){
                //observationPoint.getTrees().add(new SwampGum());
                trees.get(1).add(new SwampGum());
                amountOfSwampGum--;
            }
            
            int amountOfBlueGum = Integer.valueOf(amountString[2]);
            while (amountOfBlueGum > 0){
                trees.get(2).add(new BlueGum());
                amountOfBlueGum--;
            }
            
            int amountOfRiverRedGum = Integer.valueOf(amountString[3]);
            while (amountOfRiverRedGum > 0){
                trees.get(3).add(new RiverRedGum());
                amountOfRiverRedGum--;
            }
            
            int amountOfWattle = Integer.valueOf(amountString[4]);
            while (amountOfWattle > 0){
                trees.get(4).add(new Wattle());
                amountOfWattle--;
            }
            
            observationPoints[row] = observationPoint;
            row++;
        } 
    }
    
    // get obervation points
    public ObservationPoint[] getObservationPoints(){
        return observationPoints;
    }
    // get safe haven.
    public SafeHaven getSafeHaven(){
        return safeHaven;
    }
    
    
}
