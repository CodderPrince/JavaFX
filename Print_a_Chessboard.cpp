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
        for (int i = 0; i < a; i++)
        {
            for (int j = 0; j < b; j++)
            {
                // think simply
                // just use dp 2d array then better und.
                if ((i + j) % 2 == 0)
                {
                    cout << "#";
                    // cout<<".";
                }
                else
                {
                    cout << ".";
                    // cout<<"#";
                }
            }
            cout << nl;
        }
        cout << nl;
    }

    return 0;
}