#include<stdio.h>

int main()
{
    int n;
    scanf("%d", &n);
    int arr[n];
    int one=0, two=0;
    int indone=0, indtwo=0;
    for(int i=1; i<=n; i++)
    {
        scanf("%d", &arr[i]);
        if(arr[i]%2==0)
        {
            two++;
            indtwo=i;
        }
        else
        {
            one++;
            indone=i;
        }
    }
    if(one>two)
    {
        printf("%d", indtwo);
    }
    else
    {
        printf("%d", indone);
    }
    return 0;
}
