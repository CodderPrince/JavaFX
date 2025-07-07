#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define nl endl

int main()
{
    vector<tuple<ll, ll, ll>> x;
    x.push_back({1, 5, 2});
    x.push_back({4, 3, 1});

    // Sorting the vector of tuples
    sort(x.begin(), x.end());

    // Printing the sorted tuples
    for (const auto &t : x)
    {
        cout << "(" << get<0>(t) << ", " << get<1>(t) << ", " << get<2>(t) << ")"
             << " ";
    }

    cout << nl;

    return 0;
}
