#include<stdio.h>
#include<math.h>

int main()
{
	int t,n,r,x1,x2,y1,y2,i,j,a,b,l,k;
	scanf("%d",&t);
	while(t--){
		
		scanf("%d %d %d %d %d", &x1,&y1, &r, &x2,  &y2);
		a=(x2-x1)*(x2-x1);
		b=(y2-y1)*(y2-y1);
		l=sqrt(a+b);
		if(l>r){
			
			printf("The point is not inside the circle\n");
		}
		else
		{
		
		printf("The point is inside the circle\n");
		}

	//printf("\n");
	}
	return 0;
}
