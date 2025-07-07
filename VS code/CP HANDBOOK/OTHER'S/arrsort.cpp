//Static Array Sort. In static array don't use vector

#include <bits/stdc++.h>
using namespace std;

typedef long long ll;
#define nl endl

int main()
{
    ll arr[] = {3, 2, 1, 5, 7};
    ll n = 5;
    sort(arr, arr + n);

    cout << "Sort in Ascending Order\n";
    for (auto x : arr)
    {

        cout << x << " ";
    }
    cout << nl;
    reverse(arr, arr + n);

    cout << "Reverse Order\n";
    for (auto x : arr)
    {

        cout << x << " ";
    }
    cout << nl;

    random_shuffle(arr, arr + n);
    cout << "Randomly Shuffle\n";
    for (auto x : arr)
    {

        cout << x << " ";
    }
    cout << nl;

    return 0;
}