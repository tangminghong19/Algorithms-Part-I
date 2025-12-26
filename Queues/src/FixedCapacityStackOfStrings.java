public class FixedCapacityStackOfStrings {

    private String[] s;
    private int N = 0;

    public FixedCapacityStackOfStrings(int capacity) {
        s = new String[capacity];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(String item) {
        s[N++] = item;
    }

    public String pop() {
        // return s[--N];
        // In a robust implementation, 
        // developers often set the popped array cell to null to help with garbage collection 
        // and prevent "loitering" (holding onto memory unnecessarily).
        // Hence...
        String item = s[--N];
        s[N] = null;
        return item;
        // This version avoids "loitering".
    }
}