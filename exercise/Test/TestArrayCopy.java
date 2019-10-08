package Test;

import java.util.Arrays;

public class TestArrayCopy {
    public static void main(String[] args) {
//        int[] a = {1, 2, 3, 4};
////        int[] b=a.clone()
////        int[] b = new int[4];
////        System.arraycopy(a,0,b,0,4 );
//        int[] b = Arrays.copyOf(a,4);
//        b[0] = 10;
//        System.out.println(a[0] + " " + b[0]);
        int[][] a={{3,1,4,2,5},{4,2}};
        int[][] b=new int[a.length][];
        for(int i=0;i<a.length;i++){
            b[i]=a[i].clone();
        }
        b[0][0]=10;
        System.out.println(b[0][0]+"  "+a[0][0]);
        System.out.println(b[0]==a[0]);



    }
}
