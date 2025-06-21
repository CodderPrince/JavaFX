#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define nl "\n"

int main()
{
  ll a, b, c;
  cin >> a >> b >> c;
  ll sum = 0;
  for (int i = 0; i < a - 1; i++)
  {
    ll n;
    cin >> n;
    sum += n;
  }
  // cout<<sum<<nl;
  // ll avg = sum / a;
  // cout<<avg<<nl;

  // find mark needed in last sub.
  ll need = (a * c) - sum;
  // cout<<need;
  // max mark possible 0 to b
  if (0 <= need && need <= b)
  {
    cout << need << nl;
  }

  else if (need < 0)
  {
    cout << "0\n";
  }
  else if (need > b)
  {
    cout << "-1\n";
  }
  // cout<<sum;
  // cout<<sum/a;

  return 0;
}