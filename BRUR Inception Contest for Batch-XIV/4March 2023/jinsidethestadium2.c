#include <stdio.h>
#include<string.h>
#include<math.h>
int main()
{
    int t;
    scanf("%d", &t);
    while (t--)
    {
        int array_index;
        scanf("%d", &array_index);//array te jotogulo number input dibo tar size
        int array[array_index];
        for (int i = 1; i <= array_index; i++)
        {
            scanf("%d", &array[i]);//number gulo input nibo
        }
        float sum = 0, count = 0;
        for (int i = 1; i <= array_index; i++)
        {
            sum = sum + array[i];
            float num_of_balls = i ;
            if (sum == 0)//total run jodi zero hoi tahole to strike rate hobe na tai continue
            {
                continue;
            }
            float strikeRate = (sum / num_of_balls)*100;
            if (strikeRate  == 100)
                count++;
        }
        printf("%.0f\n", count);
    }
    return 0;
}
/*question holo batter er n ta ball e run deya thakbe amar hisab korte hobe se kotogulo
ball e 100 strike rate rekhese
test case 1: 1 0 2 3
s1: a[1]= 1; strike rate = 1/1 * 100 = 100; count=1;
s2: a[2]= 0; strike rate = 1+0/2 * 100 = 50; count=1+0=1 ei ball e se zero run korleo previous ball e se 1 run koresilo.. akhon batter er run 2 ball e 1 tai previous run add hobe and tar new strike rate 50;
s3: a[3]= 2; strike rate = 1+0+2/3 * 100 = 100; count=1+0+1=2;
s4: a[4]= 3; strike rate = 1+0+2+3/4 * 100 = 150; count=1+0+1+0=2; 
eibhabei single by single ball er batter er run check korte thakbe ar number of ball 1 kore
increase korte thake and strike rate er formula aunujayi strike ber korbe ar condition 
er moddhe giye check korbe strike rate 100 holo kina jodi 100 hoi tahole count korbe
ar last e print korbe total kotogulo ball e 100 strike rate niye bat korese*/
