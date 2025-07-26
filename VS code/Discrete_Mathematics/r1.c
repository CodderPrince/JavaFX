#include<stdio.h>
int main()
{
    int x=0;
    printf("Summation %d\n",sum(x));
    return 0;
}

int sum(x)
{
    if(x==101)
    {
        return 0;
    }

    else
    {
        return x+sum(x+1);
    }
}
