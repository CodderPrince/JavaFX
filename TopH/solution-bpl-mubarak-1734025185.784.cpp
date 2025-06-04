
#include<bits/stdc++.h>
using namespace std;

int main()
{

    int n; cin>>n;
    while(n--){
    string s;
   cin>>s;
   int ct=0;
   for(int i=0;i<s.size();i++)
   {
       if(s[i]=='O' || s[i]=='1' ||  s[i]=='2' || s[i]=='3'
          || s[i]=='4' || s[i]=='5' || s[i]=='6' || s[i]=='0' ) ct++;


   }


   int over=ct/6;
   int ball= ct%6;


   if(over>0)
   {

       if(over==1) cout<<"1 OVER ";
       else  cout<<over<<" OVERS ";
   }

    if(ball>0)
   {

       if(ball==1) cout<<"1 BALL ";
       else  cout<<ball<<" BALLS ";
   }
   cout<<endl;
    }



}
