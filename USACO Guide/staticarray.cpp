#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define nl endl

int main()
{
    vector<ll> arr = {5, 1, 3, 2, 4};
    ll n = 5;

    sort(arr.begin(), arr.end()); // Corrected the sorting

    ll i;
    for (i = 0; i < n; i++)
    {
        cout << arr[i] << " ";
    }
    cout << nl;

    return 0;
}
