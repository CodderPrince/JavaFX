#include<stdio.h>

int main()
{
    int n;
    printf("Enter a number to check prime or not: ");
    scanf("%d",&n);
    if(prime(n))
    {
        printf("%d is a prime number\n",n);
    }
    else
    {
        printf("%d is not a prime number\n",n);
    }

    return 0;
}

int prime(n)
{
    int i;
    if(n<=1)
    {
        return 0;
    }
    for(i=2; i*i<=n; i++)
    {
        if(n%i == 0)
        {
            return 0;
        }
    }

    if(i*i>n)
    {
        return 1;
    }
}
