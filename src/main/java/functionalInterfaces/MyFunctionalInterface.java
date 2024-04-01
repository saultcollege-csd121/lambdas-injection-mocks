package functionalInterfaces;

@FunctionalInterface
public interface MyFunctionalInterface {
    public boolean test(Integer x, Integer y);
    public default boolean test2(Integer x) { return true; }
}
