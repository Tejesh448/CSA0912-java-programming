import java.util.concurrent.TimeUnit;
 
public class TicketBenchmark {
    static final int FIELDS = 50;
    static final int TICKETS = 10000;
    static volatile int blackHole;
 
    static String[] makeFields() {
        String[] fields = new String[FIELDS];
        for (int i = 0; i < FIELDS; i++) {
            fields[i] = "FIELD" + i + "=VALUE" + i + ";";
        }
        return fields;
    }
 
    static String usingPlus(String[] fields) {
        String ticket = "";
        for (String field : fields) {
            ticket = ticket + field;
        }
        return ticket;
    }
 
    static String usingBuilder(String[] fields) {
        StringBuilder ticket = new StringBuilder();
        for (String field : fields) {
            ticket.append(field);
        }
        return ticket.toString();
    }
 
    static String usingBuffer(String[] fields) {
        StringBuffer ticket = new StringBuffer();
        for (String field : fields) {
            ticket.append(field);
        }
        return ticket.toString();
    }
 
    static long measure(int method, String[] fields) {
        long start = System.nanoTime();
        int totalLength = 0;
        for (int i = 0; i < TICKETS; i++) {
            String result;
            if (method == 1) result = usingPlus(fields);
            else if (method == 2) result = usingBuilder(fields);
            else result = usingBuffer(fields);
            totalLength += result.length();
        }
        long end = System.nanoTime();
        blackHole = totalLength;
        return end - start;
    }
 
    public static void main(String[] args) {
        String[] fields = makeFields();
 
        // Warm-up: allow the JVM to optimise frequently used code.
        for (int i = 0; i < 3; i++) {
            measure(1, fields);
            measure(2, fields);
            measure(3, fields);
        }
 
        String[] names = {"String +", "StringBuilder", "StringBuffer"};
        for (int method = 1; method <= 3; method++) {
            long total = 0;
            int runs = 5;
            for (int r = 0; r < runs; r++) {
                total += measure(method, fields);
            }
            double averageMs = total / (double) runs / 1_000_000.0;
            System.out.printf("%s average: %.3f ms%n",
                              names[method - 1], averageMs);
        }
    }
}
