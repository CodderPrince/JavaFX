#include<stdio.h>
int main(){
    long signed int t;
    scanf("%ld",&t);
    while(t--){
        int n,a[21],b[21],i,j,k,x;
        scanf("%d",&n);
        x=n+1;
        a[0]=1;
        a[1]=1;
            printf("1\n");
            printf("%d %d\n",a[0],a[1]);
            for(i=3;i<=x;i++){
                if(i%2!=0){
                    b[0]=1;
                    for(j=1;j<i-1;j++){
                        b[j]=a[j-1]+a[j];
                    }
                    b[j]=1;
                    for(k=0;k<i;k++){
                        printf("%d",b[k]);
                        if(k<i-1)
                        printf(" ");
                    }
                    printf("\n");
                }
                else{
                    a[0]=1;
                    for(j=1;j<i-1;j++){
                        a[j]=b[j-1]+b[j];
                    }
                    a[j]=1;
                    for(k=0;k<i;k++){
                        printf("%d",a[k]);
                        if(k<i-1)
                        printf(" ");
                    }
                    printf("\n");
                }
            }
            printf("\n");
        }
        return 0;
        
    }