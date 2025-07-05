#include <stdio.h>

int main()
{
    int t;
    scanf("%d", &t);
    while (t--)
    {
        int n;
        scanf("%d", &n);
        int a[n];
        for (int i = 0; i < n; i++)
        {
            scanf("%d", &a[i]);
        }
        float sum = 0, count = 0;
        for (int i = 0; i < n; i++)
        {
            sum = sum + a[i];
            float num_of_balls = i + 1;
            if (sum == 0)
            {
                continue;
            }
            float strikeRate = (sum / num_of_balls);
            if (strikeRate * 100 == 100)
                count++;
        }
        printf("%.0f\n", count);
    }
    return 0;
}
