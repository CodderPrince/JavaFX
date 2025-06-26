//multiple substring

#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define mod 1000000007            // 1e9 + 7
#define PI 3.14159265358979323846 // acos(-1)
#define nl "\n"
int main()
{
    ll t;
    cin >> t;
    cin.ignore();
    while (t--)
    {
        string array[] = {"prince", "hi", "raajkumar"};

        string s;
        getline(cin, s);
        
        ll flag = 0;
        for (const auto &x : array)
        {
            if (s.find(x) != string ::npos)
            {
                flag = 1;
                break;
            }
        }

        if(flag)
        {
            cout<<"YES\n";
        }
        else
        {
            cout<<"NO\n";
        }
    }

    return 0;
}