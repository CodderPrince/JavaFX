#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define mod 1000000007            // 1e9 + 7
#define PI 3.14159265358979323846 // acos(-1)
#define nl "\n"

const ll mx = 1e5 + 123; // Adjusted size for cal array
ll dp[mx];
ll cal[mx];
ll ans, n;

int rec(ll i)
{
    // base case
    if (i == n)
    {
        return 0;
    }

    if (cal[i] != -1)
    {
        return cal[i];
    }
    ll ret1 = INT_MAX, ret2 = INT_MAX;

    if (i + 1 <= n)
    {
        ret1 = abs(dp[i] - dp[i + 1]) + rec(i + 1);
    }

    if (i + 2 <= n)
    {
        ret2 = abs(dp[i] - dp[i + 2]) + rec(i + 2);
    }

    return cal[i] = min(ret1, ret2);
}

int main()
{
    cin >> n;
    for (int i = 1; i <= n; i++)
    {
        cin >> dp[i];
    }

    memset(cal, -1, sizeof(cal));
    cout << rec(1) << nl;

    return 0;
}
