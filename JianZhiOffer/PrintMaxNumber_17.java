public class PrintMaxNumber_17 {
    //n没有限定范围，大数问题，需要用字符串来表示
    public void print(int n) {
        if (n <= 0) {
            throw new RuntimeException("error input!");
        }
        char[] nums=new char[n];
        for (int i = 0; i < 10; i++) {
            //数字转字符，'0' + i 是 i 的ascii码
            nums[0]=(char)('0'+i);
            process(nums,0,n);
        }


    }

    public void process(char[] nums,int index,int len){
        if(index==len-1){
            print(nums);
            return;
        }
        for (int i = 0; i < 10; i++) {
            nums[index+1]=(char)('0'+i);
            process(nums,index+1,len);
        }

    }

    private void print(char[] nums) {
        //标记位，用来判断数字0之前是否有非零数字出现过
        int flag=0;
        String str="";
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]!='0'){
                flag=1;
                str+=nums[i];
            }
            if(nums[i]=='0'&&flag==1){
                str+=nums[i];
            }
        }
        System.out.print(str+" ");
    }

    public static void main(String[] args) {
        PrintMaxNumber_17 printMaxNumber_17=new PrintMaxNumber_17();
        printMaxNumber_17.print(3);

    }
}
