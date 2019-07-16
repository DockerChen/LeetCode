public class RemoveDuplicates_80 {
    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 2,3, 3, 3};


        int len = removeDuplicates(nums);
        for (int i = 0; i < len; i++) {
            System.out.print(nums[i] + " ");

        }


    }

    public static int removeDuplicates(int[] nums) {
        int j = 0;
        int count=0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == nums[j]) {
                count++;
                if(count<=2){
                    nums[j++]=nums[i];
                    System.out.println(j+":"+nums[j]);

                }


            }else{
                nums[j++]=nums[i];
                count=1;
                System.out.println(j+":"+nums[j]);


            }


        }

        return j;

    }
}
