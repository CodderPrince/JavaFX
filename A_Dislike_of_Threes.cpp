#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define nl "\n"

int main()
{
    // prefix sum
    ll i, n = 0, arr[1000];
    for (i = 0;; i++)
    {
        if (i % 3 != 0 && i % 10 != 3)
        {
            arr[n++] = i;
        }
        if (n == 1000)
        {
            break;
        }
    }

    ll t;
    cin >> t;
    while (t--)
    {
        int n;
        cin >> n;
        cout << arr[n - 1] << nl;
    }

    return 0;
}

/*
value:  1   2   4   5   7   8   10  11  14  16  17
index:  1   2   3   4   5   6   7   8   9   10  11
*/