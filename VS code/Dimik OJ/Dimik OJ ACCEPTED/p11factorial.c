#include<stdio.h>
int main()
{
    int t;
    scanf("%d",&t);
    while(t--)
    {
        int num;
        scanf("%d",&num);//num=6
        long long int factorial=1;//factorial means multiplication of 1 to that number so at first we declare factorial=1
        for(int i=1; i<=num; i++)
        {
        factorial= factorial*i;
        }//1x1 1x2 2x3 6x4 24x5 120x6
        printf("%lld\n",factorial);//mind it 20 factorial value is big so we declare long long integer
    }
    return 0;
}