#include<stdio.h>
int main()
{
int t;
scanf("%d", &t);
while(t--)
{
 int num, temp, r, sum=0;
 scanf("%d", &num);
 temp = num;
 while(temp!=0)
 /*num=temp==0 holei loop off hoye jabe*/
 {
    r= temp%10;
    sum = sum+ r*r*r;
    temp = temp/10;
    /*num=153=temp
    r= 153%10= 3; sum = 0+3x3x3= 27; temp= 153/10=15
    r= 15%10= 5; sum = 27+5x5x5= 152; temp= 15/10=1
    r= 1%10= 1; sum = 27+5x5x5 + 1x1x1= 153; temp= 1/10=0.1==0 
    karon integer kokohono floating count korbe na
    jehetu 0 hoye gese tai loop theke ber hoye giye nicher 
    condition check korbe ar print korbe
    */
 }
 if(num == sum)
 {
    printf("%d is an armstrong number!\n", num);
 }
 else
 {
    printf("%d is not an armstrong number!\n", num);
 }

}
return 0;
}
