//-1 2 4 -3 5 2 -5 2
// want to find maximum sum of sub array

// algorithm 1
/*
#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define nl endl

int main()
{

    ll arr[] = {-1, 2, 4, -3, 5, 2, -5, 2};
    ll n = 8;
    ll best = 0;
    ll a, b;
    for (a = 0; a < n; a++)
    {
        for (b = a; b < n; b++)
        {
            ll sum = 0;
            for (ll k = a; k <= b; k++)
            {
                sum = sum + arr[k];
            }
            best = max(best, sum);
        }
    }
    cout << best << nl;

    return 0;
}*/

// algorithm 2

/*
#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define nl endl

int main()
{

    ll arr[] = {-1, 2, 4, -3, 5, 2, -5, 2};
    ll n = 8;
    ll best = 0;
    ll a, b;
    for (a = 0; a < n; a++)
    {

        ll sum = 0;
        for (ll b = a; b < n; b++)
        {
            sum = sum + arr[b];
            best = max(best, sum);
        }
    }
    cout << best << nl;

    return 0;
}*/

// ALGORITHM 3 MOST EFFICIENT

// Kadane's algorithm for finding the maximum sum of a subarray:

#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define nl endl

int main()
{

    ll arr[] = {-1, 2, 4, -3, 5, 2, -5, 2};
    ll n = 8;
    ll best = 0, sum = 0;
    ;
    ll a, b;
    for (a = 0; a < n; a++)
    {
        sum = max(arr[a], sum + arr[a]);

        best = max(best, sum);
    }
    cout << best << nl;

    return 0;
}