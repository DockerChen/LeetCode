public class PrintStringSequence {
    //打印所有的子序列
    public static void printStringSequence(char[] chs,int index,String res) {
        if(index==chs.length){
            System.out.println(res);
            return;
        }
        printStringSequence(chs,index+1,res);
        printStringSequence(chs,index+1,res+chs[index]);

    }

    public static void main(String[] args) {
        printStringSequence("abc".toCharArray(),0,"");
    }

}
