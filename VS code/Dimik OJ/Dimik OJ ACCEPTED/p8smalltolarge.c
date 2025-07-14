#include<stdio.h>
int main()
{
    int t;
    scanf("%d",&t);
    for(int i=1; i<=t; i++)
    {
        int a,b,c,n;
        scanf("%d %d %d",&a,&b,&c);
        if(a>b)
        {
            n=a;
            a=b;
            b=n;
        }
        if(a>c)
        {
            n=a;
            a=c;
            c=n;
        }
        if(b>c)
        {
            n=b;
            b=c;
            c=n;
        }
        printf("Case %d: %d %d %d\n",i,a,b,c);
    }
    return 0;
}