#include<stdio.h>

int main()
{
    int a,b,c;
    scanf("%d %d %d",&a,&b,&c);
    int *x,*y,*z;
    x=&a;
    y=&b;
    z=&c;
    if(*x>*y && *x>z)
    {
        printf("Largest value is %d\n",*x);
    }
    else if(*y>*x && *y>*z)
    {
        printf("Largest value is %d\n",*y);
    }
    else
    {
        printf("Largest value is %d\n",*z);
    }

    return 0;
}