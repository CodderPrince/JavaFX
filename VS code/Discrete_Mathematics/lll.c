#include<stdio.h>
int main()
{
    int a[] = {1,3,4,5,6,8,9,11};
    int i, value=9;
    for(i=0; i<8; i++)
    {
        if(a[i] == value)
        {
            printf("Item found at index: %d\n", i);
            return 0;
        }
    }
    printf("Item not found\n");
    return 0;
}