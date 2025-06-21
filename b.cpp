#include <bits/stdc++.h>
using namespace std;

#define ll long long

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    ll t;
    cin >> t;
    while (t--)
    {
        string s1, s2, s3;
        int ca = 0, cb = 0, cc = 0;

        cin >> s1;
        for (int i = 0; i < s1.length(); i++)
        {
            if (s1[i] == 'A')
                ca++;
            else if (s1[i] == 'B')
                cb++;
            else if (s1[i] == 'C')
                cc++;
        }
        cin >> s2;
        for (int i = 0; i < s2.length(); i++)
        {
            if (s2[i] == 'A')
                ca++;
            else if (s2[i] == 'B')
                cb++;
            else if (s2[i] == 'C')
                cc++;
        }
        cin >> s3;
        for (int i = 0; i < s3.length(); i++)
        {
            if (s3[i] == 'A')
                ca++;
            else if (s3[i] == 'B')
                cb++;
            else if (s3[i] == 'C')
                cc++;
        }

        if (ca == 3 && cb == 3)
            cout << "C\n";
        else if (ca == 3 && cc == 3)
            cout << "B\n";
        else if (cb == 3 && cc == 3)
            cout << "A\n";
    }

    return 0;
}
