#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define mod 1000000007            // 1e9 + 7
#define PI 3.14159265358979323846 // acos(-1)
#define nl "\n"

const ll mx = 1e8 + 10;
ll dp[mx];

int main()
{
    dp[0] = 0;
    dp[1] = 1;
    ll n;
    cin >> n;
    int i, j;
    for (i = 2; i <= n; i++)
    {
        dp[i] = ((dp[i - 1] % mod + dp[i - 2] % mod) % mod);
    }
    cout << dp[n] << nl;

    return 0;
}