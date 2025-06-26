#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define nl endl

int main()
{
    while (1)
    {
        ll a, b;
        cin >> a >> b;
        if (a == 0 && b == 0)
        {
            break;
        }
        // first row
        for (int i = 0; i < b; i++)
        {
            cout << "#";
        }
        cout << nl;

        for (int i = 1; i < a - 1; i++)
        {
            // first index
            cout << "#";

            // handle middle index
            // use dp array visualization for better und.
            for (int j = 1; j < b - 1; j++)
            {
                cout << ".";
            }
            // last index
            cout << "#\n";
        }

        // last row
        for (int i = 0; i < b; i++)
        {
            cout << "#";
        }
        cout << nl << nl;
    }

    return 0;
}