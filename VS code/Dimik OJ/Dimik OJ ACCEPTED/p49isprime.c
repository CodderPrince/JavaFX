#include <stdio.h>
#include <math.h>

// একটি সংখ্যা মৌলিক কি না তা চেক করে তা রিটার্ন করে
int is_prime(long long int n)
{
    if (n <= 1)
        return 0; // 1 বা এর চেয়ে ছোট সংখ্যা মৌলিক না
    if (n <= 3)
        return 1; // 2 এবং 3 মৌলিক
    if (n % 2 == 0 || n % 3 == 0)
        return 0; // 2 এবং 3 ছাড়া যেকোনো জোড় সংখ্যা মৌলিক না
    for (long long int i = 5; i <= sqrt(n); i += 6)
    {
        if (n % i == 0 || n % (i + 2) == 0)
            return 0; // 6k ± 1 রূপে যেকোনো সংখ্যা মৌলিক না
    }
    return 1; // সমস্ত অন্য সংখ্যা মৌলিক
}

int main()
{
    int t;
    scanf("%d", &t);

    while (t--)
    {
        long long int n;
        scanf("%lld", &n);

        //printf("%lld ", n);

        if (is_prime(n))
            printf("%lld is a prime\n", n);
        else
            printf("%lld is not a prime\n", n);
    }

    return 0;
}
