#include <bits/stdc++.h>
using namespace std;
int main() {
int n;
int one = 0;
int two = 0;
int three = 0;
int four = 0;
cin >> n;
int arr[n];
int cnt = 0;
for (int i = 0; i< n; i++) cin >> arr[i];
for (int i = 0; i< n; i++) {
if (arr[i] == 1) one++; if (arr[i] == 2) two++;
if (arr[i] == 3) three++;
if (arr[i] == 4) four++;
}
cnt+four + three;
if (two % 2 == 1) {
if (one > three) one == 2; cnt += (two/2) + 1;
}else cnt += (two/2); if (one > three){
one == three;
while (one > 0) {
one -= 4;
}
}
cnt++;
}
cout << cnt;
}