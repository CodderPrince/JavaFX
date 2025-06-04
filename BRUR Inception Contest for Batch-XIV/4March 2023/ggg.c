#include<stdio.h>
int main()
{
    int t;
    scanf("%d",&t);
    int n[t],num,max,i;
    for(i=0;i<t;i++)
    {
        scanf("%d",&n[i]);
    } 
    max=n[0];
    for(i=0;i<t;i++)
    {
        if(max<n[i])
        {
            max=n[i];
            num=i;
        }
    }
    printf("%d\n",num+1);
    return 0;
}