package backend;

public class Fecha{
    private int day;
    private int month;
    private int year;
    public Fecha(int d, int m, int y){
        this.day = d;
        this.month = m;
        this.year = y;
    }
    public int getDay(){
        return this.day;
    }
    public int getMonth(){
        return this.month;

    }
    public int getYear(){
        return this.year;
    }   
}
