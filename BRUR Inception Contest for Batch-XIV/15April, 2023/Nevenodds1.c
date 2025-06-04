#include<stdio.h>
int main()
{
    long long int n,k;
    scanf("%lld %lld",&n, &k);// n=10, k=3
    if(k<= (n+1)/2)//k= 5>3
    {
        printf("%lld\n",k*2-1);//
    }
    else
    {
        printf("%lld\n",(k-(n+1)/2)*2);
    }
    return 0;
}