/********************************************************
 *..........Bismillahir Rahmanir Raheem.................
 *------------------------------------------------------
 * Name:  MD. AN NAHIAN PRINCE
 * University: BEGUM ROKEYA UNIVERSITY, RANGPUR
 * Department: COMPUTER SCIENCE AND ENGINEERING
 * Email: annahian44@gmail.com
 *------------------------------------------------------
 ********************************************************/

#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define all(x) (x).begin(), (x).end()
#define rall(x) (x).rbegin(), (x).rend()
#define pb push_back
#define mp make_pair
#define F first
#define S second

#define FOR(i, a, b) for (int i = (a); i < (b); i++)
#define ROF(i, a, b) for (int i = (a); i > (b); i--)
#define F0R(i, n) FOR(i, 0, n)
#define ROF0R(i, n) ROF(i, n, 0)
#define EACH(x, a) for (auto &x : a)

#define fastio                        \
    ios_base::sync_with_stdio(false); \
    cin.tie(NULL);                    \
    cout.tie(NULL)
#define ld long double

#define mod 1000000007            // 1e9 + 7
#define PI 3.14159265358979323846 // acos(-1)
#define nl "\n"

typedef vector<int> vi;
typedef pair<int, int> pii;

// Additional Macros and Constants
#define sz(v) (int)v.size()
#define INF 1000000000

// Additional Data Structures
typedef vector<pii> vpii;
typedef vector<ll> vll;
typedef set<int> si;
typedef unordered_set<int> usi;
typedef map<int, int> mi;
typedef unordered_map<int, int> umi;

ll const mx = 1e5 + 123;
ll dp[mx], arr[mx];
ll n;
/****CREATE NEW FUNCTION HERE****/

int solve(int i)
{
    // base case
    if (i == n)
    {
        return 0;
    }

    ll ret1 = INT_MAX, ret2 = INT_MAX;

    if (arr[i] != -1)
    {
        return arr[i];
    }

    if (i + 1 <= n)
    {
        ret1 = abs(dp[i] - dp[i + 1]) + solve(i + 1);
    }

    if (i + 2 <= n)
    {
        ret2 = abs(dp[i] - dp[i + 2]) + solve(i + 2);
    }

    return arr[i] = min(ret1, ret2);
}
/****MAIN FUNCTION IS HERE****/

int main()
{
    memset(arr, -1, sizeof(arr));
    // ll n;
    cin >> n;
    for (int i = 1; i <= n; i++)
    {
        cin >> dp[i];
    }

    cout << solve(1) << nl;

    return 0;
}