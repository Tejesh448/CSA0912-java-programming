import java.util.*;
public class ListComparison {
    static final int N = 10000;
 
    static long timeAddEnd(List<Integer> list) {
        long start = System.nanoTime();
        for (int i = 0; i < N; i++) list.add(i);
        return System.nanoTime() - start;
    }
 
    static long timeFrontInsert(List<Integer> list) {
        long start = System.nanoTime();
        for (int i = 0; i < N; i++) list.add(0, i);
        return System.nanoTime() - start;
    }
 
    static long timeGet(List<Integer> list) {
        long start = System.nanoTime();
        long sum = 0;
        for (int i = 0; i < N; i++) sum += list.get(i);
        return System.nanoTime() - start;
    }
 
    static long timeIteration(List<Integer> list) {
        long start = System.nanoTime();
        long sum = 0;
        for (int value : list) sum += value;
        return System.nanoTime() - start;
    }
 
    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>();
        List<Integer> l = new LinkedList<>();
 
        System.out.println("ArrayList end add: " + timeAddEnd(a));
        System.out.println("LinkedList end add: " + timeAddEnd(l));
 
        a.clear(); l.clear();
        System.out.println("ArrayList front insert: " + timeFrontInsert(a));
        System.out.println("LinkedList front insert: " + timeFrontInsert(l));
 
        System.out.println("ArrayList get: " + timeGet(a));
        System.out.println("LinkedList get: " + timeGet(l));
        System.out.println("ArrayList iteration: " + timeIteration(a));
        System.out.println("LinkedList iteration: " + timeIteration(l));
    }
}
