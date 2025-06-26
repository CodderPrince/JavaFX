#include<stdio.h>
#include<math.h>
#define ll long long int
    ll power(ll a, ll b)
    {
        ll ans=1;
        for(ll i=1; i<=b; i++)
        {
            ans= ans*a;
        }
        return ans;
    }

int main()
{
    ll a,b,ans;
    scanf("%lld %lld",&a,&b);
    ans= power(a,b);
    printf("Result = %lld\n",ans);
    return 0;
}