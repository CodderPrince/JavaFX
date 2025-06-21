#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define mod 1000000007            // 1e9 + 7
#define PI 3.14159265358979323846 // acos(-1)
#define nl "\n"
ll ans = 0;
ll n, a, b;
/*ll ret1=INT_MAX, ret2= INT_MAX;
int solve(ll n)
{
    if(n==1)
    {
        return a;
    }

    if(n>1)
    {
        ret1 = ans + a + solve(a);
        n--;
    }
    else
    {
         ret2 = ans + b + solve(b);
         n=n-2;
    }
    ret3 =*/

int main()
{
    ll t;
    cin >> t;
    while (t--)
    {

        cin >> n >> a >> b;
        cout << min(n * a, (n / 2) * b + (n % 2) * a) << nl;
    }

    return 0;
}