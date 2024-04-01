package functionalInterfaces;

import java.util.List;
import java.util.function.Consumer;

public class Scratch {

    public static void main(String[] args) {
        MyFunctionalInterface mfi = (x, y) -> x > y;

    }

    public static <T> void forEach(List<T> list, Consumer<T> consumer) {
        for (var item : list) {
            consumer.accept(item);
        }
    }
}
