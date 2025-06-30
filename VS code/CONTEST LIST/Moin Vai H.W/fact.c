#include<stdio.h>
int main()
{
    char c;
    int s=0;
    while(scanf("%c",&c)&& c!='\n')
    {
        s= s+c-'0';
    }
        long long int fact=1,i;
        for(i=1; i<=n; i++)
        {
            fact= fact*i;
        }
        printf("%lld\n",fact);
    }
}