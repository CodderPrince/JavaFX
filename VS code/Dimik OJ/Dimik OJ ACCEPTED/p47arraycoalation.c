#include<stdio.h>
int main(){
    int t;
    scanf("%d",&t);
    while(t--){
        int n1,n2,i,j,k=0;
        scanf("%d",&n1);
        long unsigned int a[n1];
        for(i=0;i<n1;i++){
            scanf("%lu",&a[i]);
        }
        scanf("%d",&n2);
        long unsigned int b[n2];
        for(i=0;i<n2;i++){
            scanf("%lu",&b[i]);
        }
        long unsigned int c[n1+n2],x;
        for(i=0;i<n1;i++){
            c[k]=a[i];
            k++;
        }
        for(i=0;i<n2;i++){
            c[k]=b[i];
            k++;
        }
         for(i=0;i<n1+n2;i++){
            for(j=i+1;j<n1+n2;j++){
                if(c[i]>c[j]){
                    x=c[i];
                    c[i]=c[j];
                    c[j]=x;
                }
            }
        }
       
        for(i=0;i<n1+n2;i++){
            printf("%lu",c[i]);
            if(i<n1+n2-1){
                printf(" ");
            }
        }
        printf("\n");
    }
    return 0;
}