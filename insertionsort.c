#include<stdio.h>
int main()
{
    int n,i,j,left,right,middle,temp;
    int array[]= {3,2,4,5,1,6};
    for(i=1; i<n; i++)
    {
        temp= array[i];
        left=0;
        right= i-1;
        while(left<=right)
        { 
            middle= (left+right)/2;
            if(temp<array[middle])
            {
                right= middle-1;
            }
            else
            {
                left= middle+1;
            }

        }
        for(j=i-1; j>=left; j--)
        {
            array[j+1]=array[j];
        }
        array[left]=temp;
    }
    for(i=0; i<=n; i++)
    {
        printf("%d ",array[i]);
    }
    return 0;
}