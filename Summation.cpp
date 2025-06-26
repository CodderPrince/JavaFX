#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define nl endl

int main()
{
    ll t;
    cin >> t;
    while (t--)
    {
        ll n;
        cin >> n;
        ll f = n / 10000;
        ll l = n % 10;
        cout << "Sum = " << f + l << nl;
    }

    return 0;
}