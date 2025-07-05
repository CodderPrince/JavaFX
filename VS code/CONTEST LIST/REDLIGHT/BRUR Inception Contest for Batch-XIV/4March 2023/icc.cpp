#include<bits/stdc++.h>
#define f(n) for (ll i=0; i<n; i++)
#define pb push_back
#define all(v) v.begin(), v.end()
#define sortall(v) sort(all(v))
#define fitr(it, v) for(auto it=v.begin(); it!=v.end(); it++)
#define maxa(a,n) *max_element(a, a+n)
#define mina(a,n) *min_element(a, a+n)
#define maxv(v) *max_element(all(v))
#define minv(v) *min_element(all(v))
#define ha "YES" <<endl
#define na "NO" <<endl
#define e endl
typedef long long ll;
#define cl vector<long long>
typedef long long ll;
const int m= 1e9 + 7;
using namespace std;
bool isprime(int);
ll computerXOR(ll);



int main()
{
    int t;
    cin>>t;
    while(t--)
    {
        ll n;
        cin>>n;
        if(n==1 || n==2)
        {
            cout<<0<<e;
        }
        else
        {
            if(n%2==0)
            {
                ll x= n-3;
                ll y= x/2;
                cout<<(y*2)+(n-y-1)<<e;
            }
            else
            {
                ll p= n-2;
                ll q= p/2;
                cout<<(q*2)+(n-q-1)<<e;
            }
        }

    }
}