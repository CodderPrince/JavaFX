#include <bits/stdc++.h>
using namespace std;

int main()
{
    int t;
    cin >> t;
    cin.ignore();
    while (t--)
    {
        string substringsArray[] = {"it", "stated"};
        string str;
        getline(cin, str);

        for (const auto &x : substringsArray)
        {
            if (str.find(x) != string::npos)
            // npos = no position. if it's non-valid then print yes
            {
                cout << "YES" << endl;
                break;
            }
            else
            {
                cout << "NO" << endl;
                break;
            }
        }
    }
    return 0;
}
