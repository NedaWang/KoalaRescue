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
    
    private String relocateHistory; // record relocate history.
    private int numberOfTreeLost;
    private int numberOfDeath;
    private Double originalBudget;
    
    public Reserve(){
        // initialise observation points.
        observationPoints = new ObservationPoint[10];
        safeHaven = new SafeHaven();
        relocateHistory = "";
        numberOfTreeLost = 0;
        numberOfDeath = 0;
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

    // healthy koala in safe heaven
    public void printSaftHavenStatus(){
        ArrayList<Koala> koalas = safeHaven.getKoalas();
        int numberOfHealthy = 0;
        for (Koala koala: koalas){
            if (koala.getCondition().equals("healthy")){
                numberOfHealthy++;
            }
        }
        if (numberOfHealthy == 0){
            System.out.println("No koala needs to be relocated.");
        }else{
            System.out.println("There are " + numberOfHealthy + " can be relocated." );
        }
        
    }
    
    // rescue mission
    public void misstion(double budget){
        // record original budget
        originalBudget = new Double(budget);
        for (int i = 0; i < observationPoints.length; i++){          
            System.out.println();
            System.out.println(i+1);
            // simulate the process of tree falls or damages.
            numberOfTreeLost += observationPoints[i].treesMonitor();
            
            boolean inProcess = true;
            while(inProcess){
                // print status
                
                observationPoints[i].printStatus();
                System.out.println("Budget: " + budget);
                printSaftHavenStatus(); // status of safe heaven
                printOptions();
                String option = new Scanner(System.in).nextLine().trim();
                switch (option){
                    case "a":
                        if (budget >= 20){
                            System.out.println("Moved an injured koala from point "+ i +" to the safe haven, spent $20");
                            // assume the system confirm there is an koala injured.
                            for (int index = 0; index < observationPoints[i].getKoalas().size(); index ++){
                                if (observationPoints[i].getKoalas().get(index).getCondition().equals("injured")){
                                    Koala injuredKoala = observationPoints[i].getKoalas().remove(index);
                                    safeHaven.getKoalas().add(injuredKoala);
                                    break;
                                }
                            }
                            budget -= 20;
                            break;
                        }else{
                            System.out.println("Short of budget. \n");
                            break;
                        }
           
                    case "b":
                        if (budget >= 10){
                            System.out.println("Moved an healthy koala from point "+ i +" to the safe haven, spent $10");
                            for (int index = 0; index < observationPoints[i].getKoalas().size(); index ++){
                                if (observationPoints[i].getKoalas().get(index).getCondition().equals("healthy")){
                                    Koala healthyKoala = observationPoints[i].getKoalas().remove(index);
                                    safeHaven.getKoalas().add(healthyKoala);
                                    break;
                                }
                            }
                            budget -= 10;
                            break;
                        }else{
                            System.out.println("Short of budget. \n");
                            break;
                        }
                        
                    case "c":
                        if (safeHaven.countHealthyKoala() > 0){
                            int relocateKoalaNumber = safeHaven.findOldestHealthyKoala();
                            System.out.println("Relocate a koala to point " + i);
                            Koala relocateKoala = safeHaven.getKoalas().remove(relocateKoalaNumber);
                            observationPoints[i].getKoalas().add(relocateKoala);
                            budget += 5;
                            relocateHistory += "        One koala has relocated to observer point " + (i+1);
                            relocateHistory += "\n";
                            break;
                        }else{
                            System.out.println("No koala can be relocated so far. \n");
                            break;
                        }
                    case "d":
                        System.out.println("Mission in observation point " + i + " is finished");
                        
                        inProcess = false;
                        break;
                }
            }
            numberOfDeath += observationPoints[i].numberOfKoalaUpdate();
            System.out.println("---------------------------------------------");
        }
        
        System.out.println("End of the mission");
        System.out.println("koala deaths: " + numberOfDeath);
        System.out.println("Tree lost: " + numberOfTreeLost);
        //System.out.println("originalBudget: " + originalBudget);
        System.out.println("Amount spent: " + (originalBudget - budget));
        System.out.println("Remaining in the budget: " + budget);
        if (relocateHistory.length() > 0){
            System.out.println("koalas relocated history: \n" + relocateHistory);
        }
        if (numberOfDeath == 0){
            System.out.println("Rescue was successful, with no koala deaths");
        }else{
            System.out.println("Rescue completed with " + numberOfDeath + " koalas deaths");
        }
    }
    
    public void printOptions(){
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("A. Move an injured koala to the safe haven");
        System.out.println("B. Move a healthy koala to the safe haven");
        System.out.println("C. Relocate a koala to this location");
        System.out.println("D. Take no further action");
    }
}
