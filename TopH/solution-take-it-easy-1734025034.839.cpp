
#include<bits/stdc++.h>

#define int long long int

using namespace std;
const int N=1e9+7;

 int32_t main()
{


int n; cin>>n;

int a =((n*(n+1))/2)%N;

int b=1;
for(int i=1;i<=n;i++)
{

    b*=n;
    b=b%N;
}

int c=1;
for(int i=1;i<=n;i++)
{

    c*=i;
    c=c%N;
}

int x=1; int y=1;

for(int i=1;i<=n;i++)
{

   x*=2;
   x=x%N;
   y*=3;
   y=y%N;
}

int d=(x+y)%N;

cout<<a<<" "<<b<<" "<<c<<" "<<d<<endl;


}
