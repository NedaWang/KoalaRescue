import java.util.ArrayList;
/**
 * Write a description of class ObservationPoint here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ObservationPoint
{
    //private ArrayList<Tree> trees;
    private ArrayList<ArrayList<Tree>> trees; 
    //Manna Gum, 
    //Swamp Gum, 
    //Blue Gum, 
    //River Red Gum, 
    //Wattle
    private int numberOfPredator;
    private ArrayList<Koala> koalas;
    
    public ObservationPoint(){
        numberOfPredator = RandomNumber.generateNumberOfPredator();
        koalas = new ArrayList();
        trees = new ArrayList<ArrayList<Tree>>();
        int i = 5;
        while (i>0){
            trees.add(new ArrayList<Tree>());
            i--;
        }
        initializeKoalas();
    }
    
    private void initializeKoalas(){
        int i = RandomNumber.generateNumberOfHealthyKoala();
        while (i>0){
            koalas.add(new Koala("healthy"));
            i--;
        }
        int j = RandomNumber.generateNumberOfInjuredKoala();
        while (j>0){
            koalas.add(new Koala("injured"));
            j--;
        }
    }
    
    public ArrayList<ArrayList<Tree>> getTrees(){
        return trees;
    }
    
    public ArrayList<Koala> getKoalas(){
        return koalas;
    }
    
    public double calculateEdibleLeaveWeight(){
        double result = 0;
        for (ArrayList<Tree> sameTrees: trees){
            for (Tree t: sameTrees){
                if (t instanceof GumTree){
                    GumTree g = (GumTree)t;
                    result += g.getLeavesWeight();               
                }
            }
        }
        return result;
    }
    
    // monitor all trees in this point, if any tree has been burned or fallen.
    // return how many trees have been lost.
    public int treesMonitor(){
        int numberOfLost = 0;
        for (ArrayList<Tree> type: trees){
            if (treeDamageProcess(type)){
                numberOfLost++;
            }
        }
        return numberOfLost;
    }
    
    // if 5% happened, the number of this kind of tree minuse one.
    public boolean treeDamageProcess(ArrayList<Tree> type){
        if (type.size()>0){
            if (RandomNumber.isDamaged()){
                System.out.println("");
                System.out.println("Tree lost warnnig!");
                System.out.println("One" + type.get(0).getClass().getName() + "has lost.");
                type.remove(0);
                return true;
            }
        }
        return false;
    }
    
    // print status of observation point
    public void printStatus(){
        System.out.println("Observation point current status:");
        int numberOfHealthy = 0;
        int numberOfInjured = 0;
        for (Koala koala: koalas){
            if (koala.getCondition().equals("healthy")){
                numberOfHealthy++;
            }else{
                numberOfInjured++;
            }
        }
        System.out.println("Injured koalas: " + numberOfInjured);
        System.out.println("Healthy koalas: " + numberOfHealthy);
        System.out.println("Weight of available food: " + calculateEdibleLeaveWeight());
        System.out.println("Shelter trees: " + trees.get(4).size());
        System.out.println("Predators: " + numberOfPredator);
    }
    
    public int numberOfKoalaUpdate(){
        int diedNumber = 0;
        
        // healthy condition 
        ArrayList<Koala> healthyKoalas = new ArrayList<Koala>();
        for (Koala koala: koalas){
            if (koala.getCondition().equals("injured")){
                diedNumber ++;
            }else{
                healthyKoalas.add(koala);
            }
        }
        koalas = healthyKoalas;
        if (diedNumber>0){
            System.out.println(":( " + diedNumber + " injured koala died.");
        }
        
        // short of food
        int numberOfKoala = koalas.size();
        int foodWeight = (int)calculateEdibleLeaveWeight();
        int deathNumber = 0;
        if (numberOfKoala > foodWeight){
            for (int i = 0; i < (numberOfKoala-foodWeight); i++){
                if (RandomNumber.generate80percent()){
                    deathNumber++; // show the number of koala died in this point for shortage of food.
                    diedNumber++; // total number of death.
                    System.out.println(":( 1 koala died because of shortage of food.");
                }
            }
        }
        while (deathNumber > 0){
            koalas.remove(0);
            deathNumber--;
        }
        
        // lack of shelter
        numberOfKoala = koalas.size();
        int numberOfShelter = trees.get(4).size();
        if (numberOfKoala > numberOfShelter){
            for (int i = 0; i < (numberOfKoala-numberOfShelter); i++){
                if (RandomNumber.generate20percent()){
                    deathNumber++;
                    diedNumber++; // total number of death.
                    System.out.println(":( 1 koala died because of lack of shelter.");
                }
            }
        }
        while (deathNumber > 0){
            koalas.remove(0);
            deathNumber--;
        }
        
        // predators
        if (numberOfPredator > 3){
            if (RandomNumber.generate20percent()){
                diedNumber++;
                koalas.remove(0);
                    System.out.println(":( 1 koala died because of predators");
            }
        }
        
        return diedNumber;
    }
    
    
}
