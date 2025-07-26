/*
Summation 1 to 100
when first(1) and last(100) digit is known
*/
#include<stdio.h>
int main()
{
    int x=0;
    printf("Summation %d\n",sum(x));
    return 0;
}

int sum(x)
{
    if(x>100)
    {
        return 0;
    }

    else
    {
        return x+sum(x+1);
    }
}
