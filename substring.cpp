//find single substring

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
  while(t--)
  {
     string s1,s2;
     getline(cin,s1);
     getline(cin,s2);

     if(s1.find(s2) != string::npos)
     {
        cout<<"YES\n";
     }
     /*
     Those sub string try to find this string is goes to inside find()
     npos = no position
     If this is valid then print Yes else no
     */
     else
     {
        cout<<"NO\n";
     }
  }

    return 0;
}