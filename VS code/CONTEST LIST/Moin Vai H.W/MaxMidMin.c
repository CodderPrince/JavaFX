#include<stdio.h>
int main()
{
    int a,b,c;
    printf("Enter three integer number: ");
    scanf("%d %d %d",&a,&b, &c);
    if(a>b && a>c)
    {
        if(b>c)
        {
            printf("Maximum: %d\nMiddle: %d\nMinimum: %d\n",a,b,c);
        }
        else if(c>b)
        {
            printf("Maximum: %d\nMiddle: %d\nMinimum: %d\n",a,c,b);
        }

    }
      else if(b>a && b>c)
    {
        if(a>c)
        {
            printf("Maximum: %d\nMiddle: %d\nMinimum: %d\n",b,a,c);
        }
        else if(c>a)
        {
            printf("Maximum: %d\nMiddle: %d\nMinimum: %d\n",b,c,a);
        }

    }
    else if(c>a && c>b)
    {
        if(a>b)
        {
            printf("Maximum: %d\nMiddle: %d\nMinimum: %d\n",c,a,b);
        }
        else if(b>a)
        {
            printf("Maximum: %d\nMiddle: %d\nMinimum: %d\n",c,b,a);
        }

    }
    return 0;

}