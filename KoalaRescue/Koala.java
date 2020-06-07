
/**
 * Write a description of class Koala here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Koala
{
    private String condition;
    private int age;
    
    public Koala(String condition){
        this.condition = condition;
        this.age = RandomNumber.generateKoalaAge();
    }
    
    public String getCondition(){
        return this.condition;
    }
    
    public int getAge(){
        return this.age;
    }
}
