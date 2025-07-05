#include<stdio.h>
 
int main()
{
    int t;
    scanf("%d", &t);
    while(t--)
    {
        long long int a, b;
        scanf("%lld", &a);
        b=a%14;
        if((b<7 && b!=0) && a>14)
            printf("YES\n");
        else
            printf("NO\n");
    }
    return 0;
}