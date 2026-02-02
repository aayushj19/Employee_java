package practiceExample;

import java.util.ArrayList;
import java.util.*;

public class IteratorPractice {
    public static void main(String[] args){
        ArrayList<String> cars = new ArrayList<>();
        cars.add("Volvo");
        cars.add("BMW");

        Collections.sort(cars,Collections.reverseOrder());
        java.util.Iterator<String> it = cars.iterator();

        while(it.hasNext()){
            System.out.println(it.next());

        }
    }
}