#include<stdio.h>
int main()
{
    int i,count=0;
    for(i=1000; i>=1; i--)
    {
        printf("%d\t",i);
        count++;//every time print one number and count one by one
     if(count==5)
     {//when count is equal to 5 then create a new line and count will be 0 and again start
        printf("\n");
        count=0;
     }
    }
    return 0;
}