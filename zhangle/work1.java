package com.zl.use;

import java.util.Scanner;

public class work1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = 0, n = 0;
        //m = sc.nextInt();
        //n = sc.nextInt();
        m = 3;
        n = 3;
        int[][] array = new int[m][];
        for (int i = 0; i < array.length; i++) {
            array[i] = new int[n];
        }

        int count = 1;
        for (int i = 0; i < array.length; i++) {
            for (int o = 0; o < array[i].length; o++) {
                //array[i][o] = sc.nextInt();
                array[i][o] = count;
                count++;
            }
        }

        int count_h = 0;
        int count_l = 0;
        int c_h = 0;
        int c_l = 1;
        int temp = 0;
        int counter = 0;
        for (int i = 0; i < array.length; i++) {
            for (int o = 0; o < array[i].length; o++) {
                if (counter == n + m + n - 2) {
                    temp = 1;
                }
                counter++;

                //System.out.println("count_h=" +count_h);
                //System.out.println("count_l=" +count_l);
                System.out.println(array[count_h][count_l] + " ");

                count_h = count_h + c_h;
                count_l = count_l + c_l;
                if (count_h == m - 1 - temp && count_l == temp) {
                    c_l = 0;
                    c_h = -1;
                } else if (count_h == temp && count_l == n - 1 - temp) {
                    c_l = 0;
                    c_h = 1;
                } else if (count_h == m - 1 - temp && count_l == n - 1 - temp) {
                    c_l = -1;
                    c_h = 0;
                } else if (count_h == temp && count_l == temp) {
                    c_l = 1;
                    c_h = 0;
                }

            }
        }
    }

}
