// bubble sort
// O(n2)

#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define nl endl

int main()
{
    ll arr[] = {2, 3, 6, 5, 7, 1, 4};
    ll n = 7;
    for (ll i = 0; i < n; i++)
    {
        for (ll j = 0; j < n - 1 - i; j++)
        {
            if (arr[j] > arr[j + 1])
            {
                swap(arr[j], arr[j + 1]);
            }
        }
    }
    for (ll i = 0; i < n; i++)
    {
        cout << arr[i] << " ";
    }
    cout << nl;

    return 0;
}