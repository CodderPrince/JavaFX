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

/****CREATE NEW FUNCTION HERE****/

/****MAIN FUNCTION IS HERE****/

int main()
{
    ll t;
    cin >> t;
    while (t--)
    {
        string s1, s2, s3;
        int ca = 0, cb = 0, cc = 0;

        cin >> s1;
        for (int i = 0; i < s1.length(); i++)
        {
            if (s1[i] == 'A')
                ca++;
            else if (s1[i] == 'B')
                cb++;
            else if (s1[i] == 'C')
                cc++;
        }
        cin >> s2;
        for (int i = 0; i < s2.length(); i++)
        {
            if (s2[i] == 'A')
                ca++;
            else if (s2[i] == 'B')
                cb++;
            else if (s2[i] == 'C')
                cc++;
        }
        cin >> s3;
        for (int i = 0; i < s3.length(); i++)
        {
            if (s3[i] == 'A')
                ca++;
            else if (s3[i] == 'B')
                cb++;
            else if (s3[i] == 'C')
                cc++;
        }

        if (ca == 3 && cb == 3)
            cout << "C\n";
        else if (ca == 3 && cc == 3)
            cout << "B\n";
        else if (cb == 3 && cc == 3)
            cout << "A\n";
    }

    return 0;
}