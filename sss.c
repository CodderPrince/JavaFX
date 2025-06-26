#include <stdio.h>
int main()
{
    int t;
    scanf("%d",&t);
    while (t--)
    {
        int n,i;
        float sum=0,count=0,ball;
        scanf("%d",&n);
        int a[n];
        for(i=1;i<=n;i++)
        {
            scanf("%d",&a[i]);
        }
        for(i=1;i<=n;i++)
        {
            sum=sum+a[i];
            ball=i ;

            if (sum==0)
            {
                continue;
            }
            float strikeRate=((sum/ball)*100);
            if (strikeRate==100)
            {
                count++;
            }
        }
            printf("%.0f\n", count);
    }
    return 0;
}