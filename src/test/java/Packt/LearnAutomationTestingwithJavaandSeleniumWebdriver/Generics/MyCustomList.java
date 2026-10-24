package Packt.LearnAutomationTestingwithJavaandSeleniumWebdriver.Generics;

import java.util.ArrayList;
import java.util.List;

public class MyCustomList<T> {

    List<T> list = new ArrayList<>();

    public void addElement(T element) {
        list.add(element);
    }

    public void removeElement(T element) {
        list.remove(element);
    }

    public String toString() {
        return list.toString();
    }

    public T get(int index) {
        return list.get(index);
    }
}
