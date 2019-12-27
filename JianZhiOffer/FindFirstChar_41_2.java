import java.util.LinkedList;
import java.util.Queue;

public class FindFirstChar_41_2 {
    private Queue<Character> queue = new LinkedList<>();
    private int[] chars = new int[256];

    //Insert one char from stringstream
    public void Insert(char ch) {
        chars[ch]++;
        queue.add(ch);


    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        while (!queue.isEmpty()) {
           if(chars[queue.peek()]==1){
               return queue.peek();
           }else {
               queue.poll();
           }
        }
        return '#';

    }

}
