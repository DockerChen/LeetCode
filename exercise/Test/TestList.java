package Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestList {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            System.out.println(object);
        }
        for (Object c : list) {
            System.out.println(c);

        }


        System.out.println(Integer.MAX_VALUE);
        System.out.println(list.toString());
    }

}
