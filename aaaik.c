#include <stdio.h>
int main() 
{
    int t;
    scanf("%d", &t);
    while(t--) 
    {
        int n, i,array_index;
        scanf("%d", &n);//n=5
        int arr[n];
        for(array_index=0; array_index<n; array_index++) 
        {
            scanf("%d", &arr[array_index]);//2,4,6,8,10
        }
        /*let n=5 then i store every array element in 0 index to n-1*/
        int count = 1;
        for(array_index=1; array_index<n; array_index++) 
        {
            if(arr[array_index] < arr[array_index-1]) 
            {
                count = 0;
                break;
            }
            /*ekhane ekta tricks use koresi array index 1 theke start korbo ar jodi count==1
            ei hoi tahole array sorted hobe nahole unsorted hobe
            1. array_index=1==4 ; array_index=1=1-1=0==2 4<2 condition false; count==1
            2. array_index=2==6 ; array_index=2=2-1=1==4 6<4 condition false; count==1
            3. array_index=3==8 ; array_index=3=3-1=2==6 8<6 condition false; count==1
            4. array_index=4==10 ; array_index=4=4-1=3==8 10<8 condition false; count==1
            jehetu count er value 1 ei thakse tai YES print hobe*/
        }
        if(count==1) 
        {
            printf("YES\n");
        } 
        else 
        {
            printf("NO\n");
        }
    }
    return 0;
}
