#include<bits/stdc++.h>
using namespace std;
#define ll long long int
void usool()
{
    int n,odd;
    cin>>n;
    if(n==1 || n==2)
    {
        cout<<0<<endl;
        return;

    }
    int num = n/2;
    if(n%2==0)
    {
    int ans = (odd-1) + 2 + ((num-2)*2) <<endl;
    }
    else if(n%2)
    {
        int ans = odd + 1 + ((num-1)*2);
    }
}
    int main()
    {
        ios_base::sync_with_stdio(false);
        cin.tie(NULL);
        int t;
        while(t--)
        {
            usool();
        }
    }
