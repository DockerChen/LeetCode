import java.util.ArrayList;

public class Str_replace {
    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer("we are happy");
        System.out.println(replaceSpace(stringBuffer));

    }

    public static String replaceSpace(StringBuffer str) {
//        return str.toString().replace(" ","%20");
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == ' ') {
                stringBuffer.append("%20");
            } else {
                stringBuffer.append(c);
            }
        }
        return stringBuffer.toString();


    }

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }


    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList arrayList = new ArrayList();
        while (listNode!=null){
            arrayList.add(0,listNode.val);
            listNode=listNode.next;
        }
        return arrayList;


    }
}
