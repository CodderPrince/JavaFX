#include<stdio.h>
int main()
{
    int n;
    printf("Enter a number to want fibonacci: ");
    scanf("%d",&n);
    printf("Fibonacci number: %d\n",fib(n));
    return 0;
}

int fib(n)
{
    if(n<=1)
    {
        return n;
    }
    else
    {
        return fib(n-1)+fib(n-2);
    }
}
