#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define mod 1000000007            // 1e9 + 7
#define PI 3.14159265358979323846 // acos(-1)
#define nl "\n"

const ll mx = 1e5 + 123;
ll arr[mx], dp[mx], n, k;

int solve(int i)
{
    // base case
    if (i == n)
    {
        return 0;
    }

    ll ret = INT_MAX;

    if (dp[i] != -1)
    {
        return dp[i];
    }

    for (int j = 1; j <= k; j++)
    {
        if (i + j <= n)
        {
            ret = min(ret, abs(arr[i] - arr[i + j]) + solve(i + j));
        }
    }

    return dp[i] = ret;
}

int main()
{
    cin >> n >> k;

    // memorization
    memset(dp, -1, sizeof(dp));

    for (int i = 1; i <= n; i++)
    {
        cin >> arr[i];
    }
    cout << solve(1) << nl;

    return 0;
}