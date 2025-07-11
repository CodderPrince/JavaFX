#include<stdio.h>
int main()
{
    int t;
    scanf("%d",&t);
    for( int i=0 ; i<t ; i++)
    {
        int n;
        scanf("%d",&n);
        for( int i=n ; i>=0 ; i--)
        {

            if( i==1)
            {
                printf("2 + ");
            }
             else if(i==0)
            {
                printf("1\n");
            }
            else
            {
                printf("2^%d + ",i);
            }
        }

    }
    return 0;
}
