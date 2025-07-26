/*          ----DOCUMENTATION----
 *          --Author: Prince_14
 *          --Title: Sum of two integer number
 */

#include<stdio.h>//LINK SECTION

int sum(int a, int b);//DEFINITION

int x = 5, y = 6;//GLOBAL DECLARATION

int main()
{
    int a,b,result;
    printf("Enter two integer number: ");
    scanf("%d %d",&a,&b);
    result = sum(a,b);
    printf("Sum of two number: %d\n",result);

    return 0;
}

int sum(int a, int b)
{
    return a+b;
}
