public class RemoveElements_27 {
    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 2, 3};
        int val = 2;


        int len = removeElement(nums, val);
        for (int i = 0; i < len; i++) {
            System.out.print(nums[i] + " ");

        }


    }

//快慢指针
    public static int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;

    }
}
