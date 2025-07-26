#include<stdio.h>

int main()
{
    int n;
    scanf("%d",&n);
    if(prime(n))
    {
        printf("It is prime\n");
    }
    else
    {
        printf("It is not prime\n");
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
