#include<stdio.h>
#define max 10
int main(){
     int num[max][max];
     int m,n,i,j,temp;
     scanf("%d %d",&m,&n);
     for(i=0;i<m;i++){
     	for(j=0;j<n;j++){
     		scanf("%d",&temp);
     		num[i][j]=temp;
		 }
	 }
	 for(temp=0;temp<(n/2+1);temp++){
	 	i=j=temp;

	 	while(j<n-temp){
	 		printf("%d ",num[i][j]);
			 j++;	
		}
		j--;i++;
		while(i<m-temp){
			 printf("%d ",num[i][j]);
			 i++;	
		}
		i--;j--;
		while(j>n-temp-1){
			printf("%d ",num[i][j]);
			j--;
		}
		j++;i++;
		while(i>m-temp-1){
			printf("%d ",num[i][j]);
			i--;
		}	
	
} 
return 0;
}
