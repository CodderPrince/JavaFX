#include <stdio.h>
 
int main() {
  
  long long int n,x,y;
  scanf("%lld",&n);
  x=n/2;
 if(n%2==0){
     printf("%lld",x);
 }
 else{
     y=x+1;
     printf("%lld",-y);
 }
    return 0;
}