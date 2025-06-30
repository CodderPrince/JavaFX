#include<stdio.h>
int main()
{
    int a,b;
    printf("Enter two integer number: ");
    scanf("%d %d", &a, &b);
    if(a>b)
    {
        printf("Maximum: %d\n",a);
    }
    else if(b>a)
    {
          printf("Maximum: %d\n",b);
    }
    else
    {
        printf("Both number are equal!\n");
    }
    return 0;
}