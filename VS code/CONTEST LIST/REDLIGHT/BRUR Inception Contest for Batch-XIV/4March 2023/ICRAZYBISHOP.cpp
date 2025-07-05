#include<bits/stdc++.h>
using namespace std;
#define ll long long int

void usool() {
    int n;
    cin>>n;
    if(n==1 || n==2) {
        cout<<0<<endl;
        return;
    }
    int odd = (n+1)/2;
    int ans;
    if(n%2==0) {
        ans = (odd-1) + 2 + ((n-2)*2);
    }
    else {
        ans = odd + 1 + ((n-1)*2);
    }
    cout << ans << endl;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int t;
    cin>>t;
    while(t--) {
        usool();
    }
}
