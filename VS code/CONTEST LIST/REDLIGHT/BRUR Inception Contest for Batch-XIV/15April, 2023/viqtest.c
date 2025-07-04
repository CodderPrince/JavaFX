#include <stdio.h>

int main()
{
    int n;
    int num[101];
    scanf("%d", &n); // Input the number of integers
    int evenCount = 0; // Count of even numbers
    int oddCount = 0; // Count of odd numbers
    int evenIndex = 0; // Index of the number that differs in evenness, initialized to -1
    int oddIndex = 0; // Index of the number that differs in evenness, initialized to -1

    // Loop to input n numbers and count even and odd numbers
    for (int i = 1; i <= n; i++)
    {
        //int num;
        scanf("%d", &num[i]);
        if (num[i] % 2 == 0)
        {
            evenCount++;
            evenIndex = i; // Update the index of the even number
        }
        else
        {
            oddCount++;
            oddIndex = i; // Update the index of the odd number
        }
    }

    // Output the index of the number that differs in evenness
    if (evenCount == 1)
    {
        printf("%d\n", evenIndex);
    }
    else
    {
        printf("%d\n", oddIndex);
    }

    return 0;
}
