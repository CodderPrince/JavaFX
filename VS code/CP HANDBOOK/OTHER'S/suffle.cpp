#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define nl endl

int main()
{
    vector<ll> arr = {45, 35, 32, 31, 65, 6, 7};
    random_shuffle(arr.begin(), arr.end());
    //reverse(arr.begin(), arr.end());
    for (auto x : arr)
    {
        cout << x << " ";
    }
    cout << nl;

    return 0;
}