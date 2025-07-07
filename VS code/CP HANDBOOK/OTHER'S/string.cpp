#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define nl endl

int main()
{
    string a = "MD.An Nahian Prince";
    string b = a.substr(4, 10);
    cout << b << nl;

    // push_back function
    vector<ll> x;
    x.push_back(5);
    x.push_back(3);
    x.push_back(2);
    // now it's work as a array
    //{5,3,2}
    // use auto function to print
    for (auto y : x)
    {
        cout << y << "\n";
    }

    return 0;
}