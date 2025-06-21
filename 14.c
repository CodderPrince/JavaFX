#include<stdio.h>
#include<string.h>
int main()
{
    int T, n, i, j, count;
    char str[100];
    char ch[5];

    printf("Enter the number of test cases: ");
    scanf("%d", &T);
    getchar(); // consume the newline character

    for(j = 0; j < T; j++){
        count = 0;
        printf("Enter a string: ");
        scanf("%[^\n]", str);//read the string input until the \n appears
        getchar(); // consume the newline character
        printf("Enter a character: ");
        scanf("%s", ch);
        getchar(); // consume the newline character
        for(i = 0; str[i] != '\0'; i++){
            if(str[i] == ch[0]){
                count++;
            }
        }
        if(count == 0){
            printf("'%c' is not present\n", ch[0]);
        }
        else{
            printf("Occurrence of '%c' in '%s' = %d\n", ch[0], str, count);
        }
    }
    return 0;
}
