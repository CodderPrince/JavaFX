#include<stdio.h>
#include<math.h>
int main(){
    int t;
    scanf("%d",&t);
    while(t--){
    int a,b,s=0,i;
    scanf("%d %d",&a,&b);
    for(i=0;i<=b;i++){
    s+=pow(a,i);
    }
    printf("Result = %d\n",s);
    }
    return 0;
    }