#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define nl endl

int main()
{
    ll t;
    cin >> t;
    for (int i = 1; i <= t; i++)
    {
        vector<int> arr(3);
        for (auto &x : arr)
        {
            cin >> x;
        }
        sort(arr.begin(), arr.end());
        cout << "Case " << i << ": ";
        for (auto &x : arr)
        {
            // cout<<"Case "<<i<<": "<<x<<" ";
            cout << x << " ";
        }
        cout << nl;
    }

    return 0;
}