#include<stdio.h>

int main()
{
    int n;
    scanf("%d",&n);
    printf("%d\n",fact(n));
    return 0;
}

int fact(n)
{
    if(n==1)
    {
        return 1;
    }
    else
    {
        return n*fact(n-1);
    }
}