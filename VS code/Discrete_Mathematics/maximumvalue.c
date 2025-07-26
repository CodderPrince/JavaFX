//find a maximum value in finite series
#include<stdio.h>

int main()
{
    int n;
    printf("Enter how many values you want to store: ");
    scanf("%d",&n);
    int arr[n],i;
    for(i=0; i<n; i++)
    {
        scanf("%d",&arr[i]);
    }
    int max = arr[0];
    for(i=1; i<n; i++)
    {
        if(max < arr[i])
        {
            max = arr[i];
        }
    }
    printf("The maximum value is: %d\n",max);

    return 0;
}