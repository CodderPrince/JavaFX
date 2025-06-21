#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define mod 1000000007            // 1e9 + 7
#define PI 3.14159265358979323846 // acos(-1)
#define nl "\n"
/*
const ll n = 1e5 + 10;
ll hashArray[n];

void createHashArray()
{
    int j = 0;
    for (int i = 1; i < n; i ++)
    {
        hashArray[j] = i;
        cout<<hashArray[j]<<nl;
        j++;
    }
}
*/
/****MAIN FUNCTION IS HERE****/

int main()
{

    /*createHashArray();
    for(int i=0;i<100;i++)
    {
        cout<<hashArray[i]<<nl;
    }*/

    ll t;
    cin >> t;

    while (t--)
    {
        int a;
        cin >> a;

        ll sum = 0;
        for (int i = 0; i < a; i++)
        {
            int num;
            cin >> num;
            sum += num;
        }

        /*bool found = false;
        for (int i = 0; i < n; i++)
        {
            if (hashArray[i] == sum)
            {
                found = true;
                break;
            }
        }*/
        ll root = sqrt(sum);

        if (root * root == sum)
            cout << "YES\n";
        else
            cout << "NO\n";
    }

    return 0;
}
