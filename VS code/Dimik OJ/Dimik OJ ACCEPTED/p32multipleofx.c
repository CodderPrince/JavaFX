#include<stdio.h>
int main()
{
    int t;
    scanf("%d",&t);
    while(t--)
    {
        int i,x,n;
        scanf("%d %d",&x,&n);
        if(x>n)
        {
            printf("Invalid!\n");
        }
        else
        {
        for( i=x ; i<=n ; i=i+x)
        {
            if(i%x==0)
            {
                printf("%d\n",i);
            }
        }
            //printf("\n");
        }
        printf("\n");

    }
    return 0;
}
