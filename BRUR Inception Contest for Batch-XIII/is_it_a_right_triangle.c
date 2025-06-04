#include<stdio.h>
int main()
{
    int t;
    scanf("%d",&t);
    while(t--)
    {
    int a,b,c;
    scanf("%d %d %d", &a, &b,&c);
    if(a*a+b*b==c*c || b*b+c*c==a*a || c*c+a*a==b*b)
    {
        printf("YES\n");
    }
    else
    {
        printf("NO\n");
    }
    }
    return 0;
}