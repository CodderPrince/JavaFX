#include<iostream>
using namespace std;
int main()
{
    int t;
    cin>>t;
    while(t--)
    {
        string n;
        cin>>n;
        int k = 20;
        int ans = 0;
        for(int i=0; i<n.length(); i++)
        {
            ans= (ans*10 + (n[i]-'0'))%k;

        }
        cout<<ans<<endl;
    }
    return 0;
}