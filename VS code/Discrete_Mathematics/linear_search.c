#include<stdio.h>

int main()
{
    int arr[] = {3,4,5,6,8,9,11};
    int searchitem = 9;
    int i;
    for(i=0; i<7; i++)
    {
        if(arr[i] == searchitem)
        {
            printf("Item found at index: %d\n",i);
            return 0;
        }
    }

    printf("Item not found!\n");

    return 0;
}