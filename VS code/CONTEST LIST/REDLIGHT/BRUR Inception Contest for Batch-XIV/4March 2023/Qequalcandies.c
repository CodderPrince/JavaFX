#include <stdio.h>
int main() 
{
    int t;
    scanf("%d", &t);
    while (t--) 
    {
        int array_index, sum = 0, minimum_value = 1000000000;
        /*amora ekta minimum value declare kore diye ar etar sathe compare korei amader
        input deya array theke minimum value khuje ber korbo*/
        scanf("%d", &array_index);
        //koto array element add korbo tar index number add korbo
        for (int i = 0; i < array_index; i++) 
        {
            int array_element;
            scanf("%d", &array_element);
            //array er element gulo loop er maddhome input nibo
            sum = sum + array_element;
            //array er element gulor sum korbo
            if (array_element < minimum_value)
            {
                 minimum_value = array_element;
                 /*ekhane ekta condition set kore diyesi jar sahajje khub sohoje amora 
                 array element gulor moddhe theke sob theke minimum value ta khuje ber
                 korte parbo*/
            }  
        }
        printf("%d\n", (sum - array_index * minimum_value));
        //ekhane ekta logic add kore print kora hoyese
    }
    return 0;
}
