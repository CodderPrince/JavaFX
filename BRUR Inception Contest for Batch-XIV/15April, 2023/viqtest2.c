#include<stdio.h>
#include<string.h>
#include<math.h>
int main()
{
    int x,i, count1=0, count2=0, even=0, odd=0,;
    int t;
    scanf("%d", &t);
    int arr[100];
    for(i=0; i<t; i++)
    {
            scanf("%d", arr[i]);
            if(arr[i]%2==0)
            {
                count1++;
                even=i;
            }
            else
            {
                odd++;
            }
    }
    if(even>odd)
    {
        for(i=0; i<t; i++)
            {
                if(arr[i]%2!=0)
                {
                    printf("%d\n",i+1);
                    break;
                }
            }
    }
        else
            {
                    for(i=0; i<t; i++)
                    {
                        if(arr[i]%2==0)
                        {
                            printf("%d\n",i+1);
                            break;
                        }
                    }  
           }

}
/*bob kheyal kore dekhbe onekgulo number input deyar por kon number ta exceptional
se even and odd er maddhome check korbe ar seta print korbe
jehetu array index 0 theke start hoyese tai amora i+1 print korbo*/