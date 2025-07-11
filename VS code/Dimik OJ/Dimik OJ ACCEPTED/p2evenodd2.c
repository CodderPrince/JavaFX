#include<stdio.h>
#include<string.h>
int main()
{
    int t;
    scanf("%d",&t);
    getchar();//when use string then use this function after main function
    while(t--)
    {
        char str[101];//string always a character type array .. use int means it's an array only
        gets(str);//if we use this function then getcharacter function must be use
        int len;
        len = strlen(str);
        if(str[len-1] % 2 == 0)
        /*we know that every string last cell has a null character so we delete this last cell and
        then count this string.. we also know than any big number's last if divide by 2 then the
        number is even number otherwise this number is odd.. so we count the last digit of any big
        number and that is divide by two*/
        {
            printf("even\n");
        }
        else
        {
            printf("odd\n");
        }
    }
    return 0;
}