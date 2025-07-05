#include<iostream>
using namespace std;
int main()
{
    int t;
    cin>>t;
    while(t--)
    {
        int n;
        cin>>n;
        int a[n];
        for(int i=0; i<n; i++)
        
            cin>>a[i];
            float sum=0,count=0;
             for(int i=0; i<n; i++)
        {
            sum= sum+a[i];
            float num_of_balls= i+1;
            if(sum==0)
            {
                continue;
            }
            float strikeRate= (sum/num_of_balls);
            if(strikeRate*100==100)
            count++;
        }
        cout<<count<<endl;
    }
    return 0;
}