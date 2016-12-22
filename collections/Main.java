package collections;

import java.util.ArrayList;

/**
 * Created by dim on 22.12.16.
 */
public class Main {
    public static void main(String[] args)
    {
        ArrayList list = new ArrayList();
        for (int i = 0; i<3; i++){
            list.add(new Apple());
        }
        //list.add(new Orange());

        for(int i=0; i< list.size(); i++){
            Apple obj = (Apple)list.get(i);
            System.out.println(obj.id());
        }
    }
}

class Apple
{
    private static long counter;
    private final long id = counter++;
    public long id(){
        return id;
    }
}

class Orange
{
    private static long counter;
    private final long id = counter++;
    public long id(){
        return id;
    }
}