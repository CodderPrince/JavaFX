#include<stdio.h>
#include<math.h>
int main()
{
        long long int n;
        scanf("%lld",&n);
        long long int fact=1, i;
        for(i=1; i<=n; i++)
        {
            fact= fact*i;
        }
        printf("%lld\n",fact);
    return 0;
}
