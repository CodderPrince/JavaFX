#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define mod 1000000007            // 1e9 + 7
#define PI 3.14159265358979323846 // acos(-1)
#define nl "\n"

const ll mx = 1e8 + 123;
ll dp[mx];
unordered_map<ll, ll> hash_table;

int main()
{
    ll t;
    cin >> t;
    while (t--)
    {
        ll n;
        cin >> n;
        for (int i = 0; i < n; i++)
        {
            cin >> dp[i];
            hash_table[dp[i]]++; // Increment count for each element
        }

        bool printed = false; // Flag to track if any element with count >= 3 is printed
        for (auto entry : hash_table)
        {
            if (entry.second >= 3)
            {
                cout << entry.first << nl;
                printed = true;
                break; // Break after printing the first element with count >= 3
            }
        }

        if (!printed)
            cout << "-1" << nl; // Print -1 if no element with count >= 3 is found
    }
    return 0;
}
