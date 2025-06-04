#include<bits/stdc++.h>
using namespace std;
int main()
{
    int n;
    cin>>n;
    int arr[n];
    int one=0, two=0;
    int indone=0, indtwo=0;
    for(int i=1; i<=n; i++)
    {
        cin>>arr[i];
        if(arr[i]%2==0)
        {
            two++;
            indtwo=i;
        }
        else
        {
            one++;
            indone=i;
        }
    }
    if(one>two)
    {
        cout<<indtwo;
    }
    else
    {
        cout<<indone;
    }
    return 0;
}