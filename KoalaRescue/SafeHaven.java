import java.util.ArrayList;
/**
 * Write a description of class SafeHaven here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SafeHaven
{
    private ArrayList<Koala> koalas;
    
    public SafeHaven(){
        koalas = new ArrayList<Koala>();
    }
    
    public ArrayList<Koala> getKoalas(){
        return koalas;
    }
    
    // find the oldest healthy loala
    public int findOldestHealthyKoala(){
        int oldestAge = 0;
        int index = 100;
        for (int i = 0; i < koalas.size(); i ++){
            // only consider healty ones
            if (koalas.get(i).getCondition().equals("healthy")){
                if (koalas.get(i).getAge() > oldestAge){
                    index = i;
                    oldestAge = koalas.get(i).getAge();
                }
            }
        }
        return index;
    }
    
    public int countHealthyKoala(){
        int numberOfHealthy = 0;
        for (Koala koala: koalas){
            if (koala.getCondition().equals("healthy")){
                numberOfHealthy++;
            }
        }
        return numberOfHealthy;
    }
}
