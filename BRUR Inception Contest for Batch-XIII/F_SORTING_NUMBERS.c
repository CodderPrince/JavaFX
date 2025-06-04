#include<stdio.h>
int main()
{
    int a,b,c;
    int large,middle,minimum;
    scanf("%d%d%d",&a,&b,&c);
    if(a>b && a>c)
    {
        if(b>c)
        {
            middle= b;
            minimum= c;
        }
        else if(c>b)
        {
            middle=c;
            minimum= b;
        }
        large=a;
        }
       else  if(b>a && b>c)
    {
        if(a>c)
        {
            middle= a;
            minimum= c;
        }
        else if(c>a)
        {
            middle=c;
            minimum= a;
        }
        large=b;
        }
        else  if(c>a && c>b)
    {
        if(a>b)
        {
            middle= a;
            minimum= b;
        }
        else if(b>a)
        {
            middle=b;
            minimum= a;
        }
        large=b;
        }
        printf("%d %d %d\n",minimum,middle,large);

}