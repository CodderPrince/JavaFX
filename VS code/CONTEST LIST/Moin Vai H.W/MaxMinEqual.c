#include<stdio.h>
int main()
{
    int a,b;
    printf("Enter any two integer number: ");
    scanf("%d %d",&a, &b);
    if(a>b)
    {
        printf("Maximum: %d\n",a);
        printf("Minimum: %d\n",b);
    }
     else if(b>a)
    {
        printf("Maximum: %d\n",b);
        printf("Minimum: %d\n",a);
    }
    else
    {
        printf("%d && %d\nBoth numbers are equal!\n",a,b);
    }
    return 0;
}