#include<stdio.h>
int main()
{
    int t;
    scanf("%d",&t);
    for(int i=1; i<=t; i++)
    {
        int a,b,c,x;
        scanf("%d%d%d",&a,&b,&c);//a=3,b=2,c=1
        if(a>b)//3>2
        {
            x=a;//x=3
            a=b;//a=2
            b=x;//b=3
        }//a=2, b=3
        if(a>c)//3>1
        {
            x=a;
            a=c;
            c=x;
        }//a=1, c=3
        if(b>c)//2>1
        {
            x=b;
            b=c;
            c=x;
        }//b=1, c=2
        printf("Case %d: ",i);
        printf("%d %d %d\n",a,b,c);
    }
    return 0;
}