#include<stdio.h>
int main()
{
    int t;
    scanf("%d",&t);
    for(int i=1; i<=t; i++)
    {//forasmuch print case number so we use for loop
        int num;
        scanf("%d",&num);
        printf("Case %d:",i);//here we print case number frequently
        for(int j=1; j<=num; j++)
        {//second inner loop will be continue untill the given number end
            if(num%j==0)
            {//here we print divisor of given number so we use modulus
                printf(" %d",j);
            }//already first printf colon wait to print another thing so secondly use before space  
        }
         printf("\n");
    }//after complete one case stay out inner loop and print a new line
    return 0;
}