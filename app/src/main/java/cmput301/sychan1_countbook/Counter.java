package cmput301.sychan1_countbook;

import java.util.Date;

/**
 * Created by Steven C on 10/1/2017.
 */

public class Counter {
    private String name;
    private String comment;

    private int initialValue;
    private int currentValue;

    private Date date;

    public Counter(String name, int initialValue, String comment) {
        this.name = name;
        this.date = new Date();
        this.initialValue = initialValue;
        this.currentValue = initialValue;
        this.comment = comment;
    }

    public String getName(){
        return this.name;
    }

    public String getComment(){
        return this.comment;
    }

    public int getInitialValue(){
        return this.initialValue;
    }

    public int getCurrentValue(){
        return this.currentValue;
    }

    public Date getDate(){
        return this.date;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    public void setInitialValue(int initialValue){
        this.initialValue = initialValue;
    }

    public void setCurrentValue(int currentValue){
        this.currentValue = currentValue;
    }

    public void setDate(){
        this.date = new Date();
    }

    // For display purposes
    @Override
    public String toString(){
        return date.toString() +
                "\nName: " + name +
                "\nCurrent Value: " + currentValue;
    }
}
