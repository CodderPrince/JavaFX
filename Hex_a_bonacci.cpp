#include <bits/stdc++.h>
using namespace std;

#define ll long long
// 1e9 + 7
#define PI 3.14159265358979323846 // acos(-1)
#define nl "\n"
const ll mod = 1e7 + 7; // 10000007; // 10000007
const int mx = 1e4 + 9; // 10009
ll dp[mx];

int main()
{
    ll t;
    cin >> t;
    for (int i = 1; i <= t; i++)
    {
        ll a, b, c, d, e, f, n;
        cin >> a >> b >> c >> d >> e >> f >> n;
        dp[0] = a % mod;
        dp[1] = b % mod;
        dp[2] = c % mod;
        dp[3] = d % mod;
        dp[4] = e % mod;
        dp[5] = f % mod;

        // already fixed that 0 to 5 answer

        for (int j = 6; j <= n; j++)
        {
            dp[j] = ((dp[j - 1] % mod + dp[j - 2] % mod + dp[j - 3] % mod + dp[j - 4] % mod + dp[j - 5] % mod + dp[j - 6] % mod) % mod);
        }
        cout << "Case " << i << ": " << dp[n] << nl;
    }

    return 0;
}