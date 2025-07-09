// CPP DEFAULT SORTING [DESCENDING ORDER]
// TIME COMPLEXITY: AVERAGE CASE(nlogn)

#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define nl endl

int main()
{
    vector<ll> arr = {4, 5, 7, 1, 2};
    sort(arr.rbegin(), arr.rend());
    ll i;
    for (i = 0; i < 5; i++)
    {
        cout << arr[i] << " ";
    }
    cout << nl;

    return 0;
}