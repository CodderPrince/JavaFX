#include<stdio.h>
int main()
{
    int n;
    printf("Enter a number: ");
    scanf("%d",&n);
    printf("Summation of 1 to n: %d\n",sum(n));
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
