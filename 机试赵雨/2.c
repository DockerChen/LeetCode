#include<stdio.h>
typedef struct{
	int data;
	Tree *lchild,*rchild;
}Tree;
typedef struct{
	Tree tree1[max];
	int m,n;
}Treenode;
int main(){
	int t,m,n,u,v;
	scanf(%d,&t);
	while(--t){
	Treenode *tree=(Treenode *)malloc(sizeof(Treenode));
	scanf("%d %d",&m,&n);
	m=tree.m;
	n=tree.n;
	for(int i=0;i<tree.n;i++){
		tree.tree1[i].data=i+1;	
		tree.tree1[i].lchild=tree.tree1[i].rchild=null;
		
	}
	for(int i=0;i<tree.m;i++){
		scanf("%d %d",&u,&v); 
		if(tree.tree1[u-1].lchild==null)
		   	tree.tree1[u-1].lchild=tree.tree1[v-1];
		else tree.tree1[u-1].rchild=tree.tree1[v-1];
	}
	scanf("%d",&m);
	int count=0;
	for(i=0;i<m;i++){
		scanf("%d",&n);
	     //利用栈遍历 该顶点 
		 pop(sqstack,tree.tree1[u-1].data);
		  while(!issqstack){
		  	push(sqstack,tree.tree1[u-1].data);
		  	count++;
		  	pop(sqstack,tree.tree1[u-1].lchild);
		  	pop(sqstack,tree.tree1[u-1].rchild);
		  }
		  count-=1;
		  printf("%d",count);
	}
	
	} 
} 
