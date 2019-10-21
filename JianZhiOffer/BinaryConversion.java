public class BinaryConversion {
    public int binaryConversion(String str){
        int len=str.length();
        int res=0;
        for (int i = 0; i < len; i++) {
            char c=str.charAt(i);
            res+=(c-'A'+1)*(int)Math.pow(26,len-i-1);
        }

        return res;
    }

    public static void main(String[] args) {
        String str="AAB";
        BinaryConversion binaryConversion=new BinaryConversion();
        System.out.println(binaryConversion.binaryConversion(str));
    }

}
