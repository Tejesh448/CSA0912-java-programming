import java.util.*;
class Medicine {
    String code;
    String name;
    int quantity;
 
    Medicine(String code, String name, int quantity) {
        this.code = code;
        this.name = name;
        this.quantity = quantity;
    }
 
    public String toString() {
        return code + " - " + name + " - Stock: " + quantity;
    }
}
 
public class PharmacyMaps {
    public static void main(String[] args) {
        Map<String, Medicine> hash = new HashMap<>();
        Map<String, Medicine> sorted = new TreeMap<>();
        Map<String, Medicine> insertion = new LinkedHashMap<>();
 
        Medicine[] data = {
            new Medicine("M103", "Paracetamol", 120),
            new Medicine("M101", "Amoxicillin", 60),
            new Medicine("M105", "Cetirizine", 90),
            new Medicine("M102", "Metformin", 75),
            new Medicine("M104", "ORS", 45)
        };
 
        for (Medicine m : data) {
            hash.put(m.code, m);
            sorted.put(m.code, m);
            insertion.put(m.code, m);
        }
 
        long start = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            hash.get("M103");
        }
        long lookupTime = System.nanoTime() - start;
 
        System.out.println("100,000 lookups (ns): " + lookupTime);
 
        System.out.println("\\nCode order:");
        for (Medicine m : sorted.values()) System.out.println(m);
 
        System.out.println("\\nInsertion order:");
        for (Medicine m : insertion.values()) System.out.println(m);
    }
}
