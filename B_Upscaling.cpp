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
  while(t--)
  {
     int n;
     cin>>n;
     int i,j;
     for(i=0;i<n*2;i++)
     {
        for(j=0;j<n*2;j++)
        {
            if(i+j<n*2)
            {
                cout<<"#";
            }
            else
            cout<<".";
        }
        cout<<nl;
     }
     cout<<nl;
  }

    return 0;
}