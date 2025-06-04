#include <stdio.h>
int main() {
    int stones;  
    scanf("%d", &stones); 
    if(stones%3==0)
    {
        printf("%d\n",stones/3*2);
    }
    else
    {
        printf("%d\n",stones/3*2 + 1);
    }  
    return 0;
}
