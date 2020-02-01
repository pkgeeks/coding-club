
import java.util.Comparator;
import java.util.PriorityQueue;

public class RearrangeCharsOfString {

    // creating a data structure to hold key and its frequency
    class Key {
        char ch;
        int count;
        Key(char ch, int c) {
            this.ch = ch;
            this.count = c;
        }
    }

    public  String rearrangeCharacters(String str) {
        if(str == null) return "";

        // creating count array
        int[] countArr = new int[26];
        for(int i=0; i<str.length(); i++)
            countArr[str.charAt(i) - 'a']++;

        // creating PriorityQueue object, it is Max Heap
        PriorityQueue<Key> maxHeap = new PriorityQueue<>(new Comparator<Key>() {
            @Override
            public int compare(Key o1, Key o2) { // defining comparator for decreasing order
                if(o1.count > o2.count) return -1;
                else if(o1.count < o2.count) return 1;
                else return 0;
            }
        });

        // adding each character and its count (Key object) in max heap
        for(int i=0; i<26; i++) {
            if(countArr[i] > 0) {
                Key obj = new Key((char)('a' + i), countArr[i]);
                maxHeap.add(obj);
            }
        }

        // now rearranging character
        Key prev = new Key('#', 0);
        String result = "";
        while(!maxHeap.isEmpty()) {

            Key top = maxHeap.poll();
            result += top.ch;

            // If frequency of previous character is greater than 1 then add again in heap
            if(prev.count > 0)
                maxHeap.add(prev);

            // make current character as the previous 'char'
            // decrease frequency by 'one'
            top.count = top.count - 1;
            prev = top;
        }

        if(result.length() == str.length()) return result;
        return "";
    }

    public static void main(String[] args) {
        RearrangeCharsOfString obj = new RearrangeCharsOfString();
        System.out.println(obj.rearrangeCharacters("aab"));
        System.out.println(obj.rearrangeCharacters("aaab"));
    }
}
