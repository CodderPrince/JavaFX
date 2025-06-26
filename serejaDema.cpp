#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define mod 1000000007            // 1e9 + 7
#define PI 3.14159265358979323846 // acos(-1)
#define nl "\n"

int main()
{
    ll n;
    cin >> n;
    vector<ll> v(n);
    for(int i = 0; i < n; i++) cin >> v[i];

    ll s = 0, d = 0, flag = 1;
    while (!v.empty())
    {
        if (flag == 1)
        {
            if (v.front() > v.back())
            {
                s += v.front();
                v.erase(v.begin());
            }
            else
            {
                s += v.back();
                v.pop_back();
            }
            flag = 2;
        }
        else
        {
            if (v.front() > v.back())
            {
                d += v.front();
                v.erase(v.begin());
            }
            else
            {
                d += v.back();
                v.pop_back();
            }
            flag = 1;
        }
    }

    cout << s << " " << d << nl;

    return 0;
}
