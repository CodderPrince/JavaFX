#include <bits/stdc++.h>
using namespace std;

#define ll long long

#define nl "\n"

const ll mx = 100000, mod = 1e9 + 7;
ll dp[mx];
int main()
{
    ll t;
    cin >> t;
    dp[0] = dp[1] = 1;
    for (int i = 2; i <= t; i++)
    {
        dp[i] = dp[i - 1] + dp[i - 2];
    }
    cout << dp[t] << nl;
    return 0;
}