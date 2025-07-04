#include<stdio.h>
#include<string.h>
#include<math.h>
int main()
{
    int t;
    scanf("%d",&t);
    int arr[t];
    int even=0,odd=0,even_index=0,odd_index=0;
    for(int i=1; i<=t; i++)
    {
        scanf("%d",&arr[i]);
        if(arr[i]%2==0)
        {
            even++;
            even_index=i;
        }
        else
        {
            odd++;
            odd_index=i;
        }
    }
    if(odd>even)
    {
        printf("%d",even_index);
    }
    else
    {
        printf("%d",odd_index);
    }
    return 0;
}