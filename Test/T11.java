import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class T11 {
    public static int solve(String str, String pattern) {
        char[] chars1 = str.toCharArray();
        int len1 = chars1.length;
        char[] chars2 = pattern.toCharArray();
        int len2 = chars2.length;
        boolean flag;
        for (int i = 0; i < len1 - len2; i++) {
            flag = true;
            if (chars1[i] == chars2[0]) {
                for (int j = 0; j < len2; j++) {
                    if (chars1[i + j] != chars2[j]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int findNum(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                right = mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (right == 0) {
            if (arr[right] == target) {
                return right;
            } else {
                return -1;
            }
        }
        return right;
    }

    class ListNode {
        ListNode pre;
        ListNode next;
        int val;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode sortList(ListNode head) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Set<ListNode> set = new HashSet<>();
        ListNode tmp = head;
        while (tmp != null) {
            if (!set.contains(tmp)) {
                set.add(tmp);
                arrayList.add(tmp.val);
                tmp = tmp.next;
            } else {
                break;
            }

        }
        ListNode newHead = new ListNode(-1);
        ListNode tmp1 = newHead;
        ListNode preNode = null;
        ListNode startNode = new ListNode(arrayList.get(0));
        ListNode endNode = new ListNode(arrayList.get(arrayList.size() - 1));
        Collections.sort(arrayList);
        for (int i = 0; i < arrayList.size(); i++) {
            ListNode listNode = new ListNode(arrayList.get(i));
            if (i == 0) {
                preNode = listNode;
                tmp1.next = listNode;
                tmp1.pre = endNode;
                tmp1 = tmp1.next;
            } else if (i == arrayList.size() - 1) {
                tmp1.next = startNode;
                tmp1.pre = preNode;
            } else {
                tmp1.next = listNode;
                tmp1.pre = preNode;
                preNode = tmp1;
            }
        }
        return newHead.next;
    }

    public static void main(String[] args) {
        int[] arr = {3};
        System.out.println(findNum(arr, 4));
    }
}
