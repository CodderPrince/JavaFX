#include<bits/stdc++.h>
#define ll long long int
using namespace std;

ll pow(ll a, ll b)
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
    cin>>a>>b;
    ans= pow(a,b);
    cout<<ans<<endl;
}