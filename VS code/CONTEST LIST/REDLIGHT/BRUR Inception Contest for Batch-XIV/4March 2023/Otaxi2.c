#include <stdio.h>
int main()
{
    int n, c, count[5] = {0};
    scanf("%d", &n);
    while (n--)
    {
        scanf("%d", &c);
        count[c]++;
    }
    int total = count[4] + count[3] + count[2] / 2;
    count[1] = count[1] - count[3];
    if (count[2] % 2 == 1)
    {
        total = total + 1;
        count[1] = count[1] - 2;
    }
    if (count[1] > 0)
    {
        total = total + (count[1] + 3) / 4;
    }
    printf("%d\n", total);
    return 0;
}
