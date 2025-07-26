//calculate fibonacci number
/*
 Fibonacci Series:  0   1   1   2   3   5   8   13
  Number:                    1   2   3   4   5   6    7

*/

#include<stdio.h>
int main()
{
    int n;
    printf("Enter a number to know fibonacci value: ");
    scanf("%d",&n);
    printf("Fibonacci value of n: %d\n",fibonacci(n));
    return 0;
}

int fibonacci(n)
{
    if(n<=1)
    {
        return n;
    }
    else
    {
        return fibonacci(n-1) + fibonacci(n-2);
    }
}
