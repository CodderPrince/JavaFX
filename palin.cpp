//CHECK A WORD PALINDROME OR NOT WITHOUT USING LIBRARY FUNCTION

#include<bits/stdc++.h>
using namespace std;

int main()
{
    char str[101];
    cout<<"Enter a string: ";
    cin>>str;
    int i,len;
    len=strlen(str);
    for(i=0; i<len/2; i++)
    {
        if(str[i]!=str[len-1-i])
        {
            cout<<"'"<<str<<"' is not a palindrome"<<endl;
            return 0;
        }
    }
    cout<<"'"<<str<<"' is a palindrome"<<endl;

    return 0;
}