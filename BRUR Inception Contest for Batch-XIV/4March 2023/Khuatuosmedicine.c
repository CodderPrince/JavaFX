#include <stdio.h>
int main() 
{
    int t;
    while(scanf("%d",&t)==1)
  {
    for (int i = 1; i <= t; i++) 
    {
        int number_of_medicine;
        scanf("%d", &number_of_medicine);
        int total_bottles = 1 + (number_of_medicine - 1) * 2;
        printf("Case #%d: %d\n", i, total_bottles);
    }
 }
    return 0;
}
