package com.zl.use;

import java.util.Scanner;

public class work3 {

	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int m = 0;
		int n = 0;
		m = sc.nextInt();
		n = sc.nextInt();
		int [][]array = new int[m][];
		for (int i = 0;i<array.length ;i++)
		{
			array[i]=new int[n];
		}
		
		for (int i=0;i<array.length;i++)
		{
			for (int o=0;o<array[i].length;o++)
			{
				array[i][o]=0;
			}
		}
		
		int k = 0;
		k = sc.nextInt();
		
		int x = 0 ;
		int y = 0;
		int c = 0;
		int []count = new int [k];
		for (int i =0;i<k;i++)
		{
			x=sc.nextInt();
			y=sc.nextInt();
			if(x>=n||x<0)
			{
				if(i!=0)count[i] = count[i-1];
				else count[i] = 0;
				continue;
			}
			
			if(y>=m||y<0)
			{
				if(i!=0)count[i] = count[i-1];
				else count[i] = 0;
				continue;
			}
			
			array[x][y] = 1;
			for(int u = 0;u<x;u++)
			{
				for (int v=0;v<y;v++)
				{
					if(array[u][v]==1) c++;
				}
			}
				
			count[i] = c;
			c = 0;
		}
		for (int i=0 ; i<k;i++)
		{
			System.out.println(count[i]+ " ");
		}
		
	}
}
