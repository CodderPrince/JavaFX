#include <stdio.h>
#include <string.h>
int main() {
    int t;
    scanf("%d", &t);
    while (t--) 
    {
        char ticket[7];
        scanf("%s", ticket);
        int sum_first_3digit = 0, sum_last_3digit = 0;
        for (int i = 0; i < 3; i++) 
        {
            sum_first_3digit = sum_first_3digit + ticket[i] - '0';
            sum_last_3digit = sum_last_3digit + ticket[i+3] - '0';
        }
        /*ami string er maddhome value input nissi ja character value print korbe
        kintu amar integer value lagbe tai '0' sub kore diyesi tai int print hobe*/
        if (sum_first_3digit == sum_last_3digit) 
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
