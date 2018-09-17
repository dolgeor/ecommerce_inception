package util;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsolePrinter<T, V> {

    public void print(T o) {
        System.out.println(o);
    }

    public void printArray(List<V> list) {
        list.forEach(System.out::println);
    }

    public void out(T o, List<V> list2) {
        print(o);
        printArray(list2);
    }
}
