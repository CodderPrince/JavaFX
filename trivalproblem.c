#include<stdio.h>
int main()
{
    int m;
    scanf("%d",&m);
    int num, x, sum=0;
    for( int i=1; i<=100000; i++)
    {
        num=1; x=i;
        while(1)
        {
            if(x%5==0)
            {
                num++;
                x= x/5;
            }
            else
            {
                break;
            }
        }
        sum = sum+num;
        if(sum==m)
        {
            printf("5\n");
            for(int j= 5*i; j<5*i+5; j++)
            {
                printf("%d ",j);
            }
            return 0;
        }
        else if(sum>m)
        {
            break;
        }
    }
    printf("0");
    return 0;
}