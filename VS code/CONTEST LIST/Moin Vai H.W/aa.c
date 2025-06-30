#include<stdio.h>
int main()
{
    int i,j,n,count=0;
    scanf("%d",&n);
    for(i=1; i<=n; i++)
    /*1. i=1 , j=2, 1%2!=0, 1=!2 i++
    2. i=2, j=2, print 2
    3. i=3, j=2, 3%2!=0 , 3!=2 j++ j=3, 3%3==0, 3==3, print
    4. i=4, j=2, 4%2==0, break. 4!=2 not print */
    {
        for(j=2; j<=i; j++)
        {
            if(i%j==0)
            {
                break;
            }
        }
        if(i==j)
        {
            printf("%d\n",i);
            //count++;
            
        }
    }
    //printf("%d\n",count);
}