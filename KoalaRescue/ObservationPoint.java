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
    public void treesMonitor(){
        for (ArrayList<Tree> type: trees){
            treeDamageProcess(type);
        }
    }
    
    // if 5% happened, the number of this kind of tree minuse one.
    public void treeDamageProcess(ArrayList<Tree> type){
        if (type.size()>0){
            if (RandomNumber.isDamaged()){
                type.remove(0);
            }
        }
    }
}
