#include<stdio.h>
#include<math.h>
 int main()
{
    long signed int t;
    scanf("%ld",&t);
    while(t--){
    int n,i,j;
    scanf("%d",&n);
    long long a[52][52];
    a[0][0]=1;
    a[1][0]=1;
    a[1][1]=1;
    for(i=2;i<=n;i++){
    a[i][0]=1;
    for(j=1;j<i;j++){
    a[i][j]=a[i-1][j-1]+a[i-1][j];
    }
    a[i][j]=1;
    }
    for(i=0;i<=n;i++){
    for(j=0;j<=i;j++){
    printf("%lld",a[i][j]);
    if(j<i)
    printf(" ");
    }
    printf("\n");
    }
    printf("\n");
    }
return 0;
}