/*LONG FACTORIAL
6!= 6x5x4x3x2x1=720*/
#include<stdio.h>
int main()
{
    int n,carry=0,i,j,mul,dig,len=1;
    printf("Enter any big integer number: ");
    scanf("%d",&n);
    int arr[1000]={0};//zero index er man 0 rakhbo
    arr[0]=1;//let zero index value is 1
    for(i=2; i<=n; i++)//so we start a loop from 2 and end equal at n
    {
        for(j=0; j<len; j++)//j er loop len porjonto cholbe
        {
            mul= i*arr[j]+carry;//CARRY er man count hote thakbe jokhon carry er value dui sonkha theke boro hobe
            dig= mul%10;//eta tokhon kaj korbe jokhon mul er value 2 sonkha theke boro hobe,let24%10=4
            arr[j]=dig;//dig er man array er j index e rakhbo---4
            carry= mul/10;//mul er man jodi 9 theke boro hoi tahole extra value carry er moddhe rakhbo---carry 1
        }
        while(carry>0)//ebar carry er man zero theke boro howar aag porjonto ekta loop use korbo carry er valuer jonno
        {
            dig= carry%10;//carry er man 1 sonkha theke boro hole eta kaj korbe 5!=120 here carry= 12 and here value 
            arr[len]=dig;//last er digit ta array er len er moddhe rakhbo, len=12
            len++;//upore len er man 1 kore increase korbo
            carry= carry/10;//carry er value decrease korte hobe 12/10=1 ekhon 2 er kaj shes
        }
    }
    for(i=len-1; i>=0; i--)//len er man shes theke 1 bad diye print korte hobe
    {
        printf("%d",arr[i]);
    }
    return 0;
    
}