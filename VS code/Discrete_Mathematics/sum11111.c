//sum 1 to n
#include<stdio.h>

int main()
{
    int n;
    scanf("%d",&n);
    printf("%d\n",sum(n));
    return 0;
}

int sum(n)
{
    if(n==0)
    {
        return 0;
    }
    else
    {
        return n+sum(n-1);
    }
}
