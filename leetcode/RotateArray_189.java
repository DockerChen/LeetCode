public class RotateArray_189 {
    public static void main(String[] args) {
        int nums[] = {1, 2, 3, 4, 5, 6, 7};
        rotate(nums, 3);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");

        }
    }
    //暴力的解法，会超出运行时间限制
//    public static void rotate(int[] nums, int k) {
//        int temp;
//        for (int i = 0; i < k; i++) {
//            for (int j = nums.length-1; j > 0; j--) {
//                temp = nums[j - 1];
//                nums[j - 1] = nums[j];
//                nums[j] = temp;
//            }
//
//        }
//    }
    public static void rotate(int[] nums, int k){
        int nums1[]= new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nums1[(i+k)%nums.length]=nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i]=nums1[i];

        }
    }



}
