#include<stdio.h>
int main()
{
    int t;
    scanf("%d",&t);
    while(t--)
    {
        int num,count=0;
        scanf("%d",&num);
        for(int i=5; i<=num; i= i*5)
        {
            count = count + num/i;
        }
        printf("%d\n",count);
    }
    return 0;
}
/*in general in number 4 factorial there is no zero in last zero start at number 5
factorial zero is increased a sequence every 5 number after one zero increased so
we start loop at 5 and increased 5++
n=100, i=5, 5<=100, count= 0 + 100/5=20
n=100, i=5x5=25, 25<=100, count= 0 + 100/25=20+4=24
n=100, i=25x5=125, 125!<=100, so print 24 
here we print how many zero in a factorial number*/

/*add more important information
when 25! then last zero increase extra 1 untill 50!
it means 25/5+1=6; 30/5+1=7; 35/5+1=8
but when 50! then last zero increase extra 2 untill 75!
50! means 50/5+2=12; 60/5+2=14; 70/5+2=16
but when 75! then last zero increase extra 4 untill 100!
75! means 75/5+4=19; 90/5+4=22; 100/5+4= 24
*/

/*
in general case 0-4 no zero
zero stat at 5!
when 5-100 ! ever 5 number after zero increase 1
5-9! zero 1
10-14! zero 2
15-19! zero 3
it increase this series
exceptional when 
25! then zero increase extra 1 
50! then zero increase extra 2
75! then zero increase extra 3
100! then zero increase extra 4
125! then zero increase extra 6
150! then zero increase extra 7
175! then zero increase extra 8
200! then zero increase extra 9
*/