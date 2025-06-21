#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define mod 1000000007            // 1e9 + 7
#define PI 3.14159265358979323846 // acos(-1)
#define nl "\n"

int main()
{
    int n;
    cin >> n;
    vector<int> v(n);
    for (auto &x : v)
    {
        cin >> x;
    }
    int s = 0, d = 0, f = 1;
    while (!v.empty())
    {
        if (f == 1)
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
            f = 2;
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
            f = 1;
        }
    }
    cout << s << " " << d << nl;

    return 0;
}