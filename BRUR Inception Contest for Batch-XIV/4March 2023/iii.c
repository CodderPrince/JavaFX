#include<stdio.h>
int main()
{
    int test;
    long long int n;
    scanf("%d",&test);
    while(test-- && scanf("%lld",&n))
        printf("%lld\n" , n == 1 ? 0 : ((n/2 - 1) - (n%2 == 0)) * 2 + (n/2 + 1));
    return 0;
}