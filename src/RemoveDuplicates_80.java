public class RemoveDuplicates_80 {
    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3};


        int len = removeDuplicates(nums);
        for (int i = 0; i < len; i++) {
            System.out.print(nums[i] + " ");

        }



    }

    public static int removeDuplicates(int[] nums) {
        //双指针，index:替换的下标，i：当前位置的下标
        int index=0;
        int count=0;
        for (int i = 0; i < nums.length; i++) {
            if(i==0||nums[i]==nums[i-1]){
                count++;
            }else {
                count=1;
            }

            if(count<=2){
                nums[index++]=nums[i];
            }

        }
        return index;



    }
}


