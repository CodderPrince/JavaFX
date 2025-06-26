#include<stdio.h>
int main()
{
    int t,n,i,j;
    scanf("%d",&t);
    for(i=0;i<t;i++){
        scanf("%d",&n);
    if(n%2==0){
        for(j=2;j<=(2*n);j=j+2)
        {
            printf("%d ",j);
        }

    }
    else {  for(j=1;j<=n;j++)
        {
            printf("%d ",j);
        }
        }
        printf("\n");}
        return 0;

}
