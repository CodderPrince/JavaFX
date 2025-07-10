#include <stdio.h>

int main() {
    int n;
    char str[101];

    scanf("%d", &n); // টেস্ট কেসের সংখ্যা নেওয়া হল

    while (n--) { // টেস্ট কেস অনুসারে কর্মক্ষমতা প্রদর্শন করা হবে
        scanf("%s", str); // ক্যারেক্টার ইনপুট নেওয়া হল

        // ক্যারেক্টার ধরণ নির্ণয় করা হল
        if (str[0] >= 'a' && str[0] <= 'z') {
            printf("Lowercase Character\n");
        } else if (str[0] >= 'A' && str[0] <= 'Z') {
            printf("Uppercase Character\n");
        } else if (str[0] >= '0' && str[0] <= '9') {
            printf("Numerical Digit\n");
        } else {
            printf("Special Characters\n");
        }
    }

    return 0;
}