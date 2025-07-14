#include<stdio.h>
int main()
{
    int t;
    scanf("%d",&t);
    for(int i=1; i<=t; i++)
    {
        int num;
        scanf("%d",&num);
        for(int row=1; row<=num; row++)//firstly input num=3 then total box row is 3
        {//row print korte hobe box make korar jonno
            for(int col=1; col<=num; col++)
            {//ebar column print korte hobe box er jonno
                printf("*");
            }//every column star start 1 and equivalent too num here mind that only outer loop value will be increase not inner loop value will be increase or decrease
            printf("\n");
        }
         if(t!=i)
        {
         printf("\n");
        }
        /*presentation error avoid korar jonno ekhane ekta condition set koresi jate kono 
        extra new line print na hoi . if t=3 and n= 2,4,5
        t=3 then 2ta star box make kore 3!=1 so t!=i check kore new line print korbe
        t=3 then 2ta star box make kore 3!=2 so t!=i check kore new line print korbe
        t=3 then 2ta star box make kore 3!=3 so t!=i check kore dekhbe value equal tai
        loop er moddhe na dhuke program close kore dibe ebhabe condition set kora hoyese*/
    }
    return 0; 
}