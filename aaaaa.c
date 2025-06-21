#include <stdio.h>
#include <string.h>

char convert(char ch); // Keep the return type as char

int main()
{
    char ch;
    printf("Enter a lowercase character: ");
    scanf(" %c", &ch);

    if (ch >= 'a' && ch <= 'z') {
        printf("Lowercase character %c is converted to: %c\n", ch, convert(ch));
    } else {
        printf("Invalid input. Please enter a lowercase character.\n");
    }

    return 0;
}

char convert(char ch)
{
    if (ch >= 'a' && ch <= 'z') {
        return ch - 32; // Convert lowercase to uppercase by subtracting 32
    } else {
        return ch; // Return the character as is if it's not a lowercase letter
    }
}
