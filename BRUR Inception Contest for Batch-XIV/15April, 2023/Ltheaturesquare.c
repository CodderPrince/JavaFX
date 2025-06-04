#include<stdio.h>
#include<math.h>
int main()
{
    long long int theature_height, theature_width, granite_size, total_granite_need;
    //10^9 onek long number tai ekhane lld use korte hobe
    double granite_height_need, granite_width_need;
    scanf("%lld %lld %lld",&theature_height,&theature_width,&granite_size);
    granite_height_need = ceil((double)theature_height/(double)granite_size);
    /*ceiling ekta function jar kaj hosse float number ke tar nearest integer number e 
    convert kore deya.. ekhane jehetu bola hoyese theature square fill up korar jonno
    amora granite ke vangte parbo na tai float number ke tar nearest integer number e 
    convert kore amader kaj korte hobe...jodi output 2.22 or 2.66 hoi tahole ceiling
    function use korar jonno amader value store hobe 3*/
    granite_width_need = ceil((double)theature_width/(double)granite_size);
    /*ekhane amora arekta kaj koresi seta holo typecasting koresi. karon duita integer
    value ke divide korle alltime tar value integer number ei ase tai amora manually
    typecasting kore diyesi karon amader float value lagbe ar amora setake ceiling kore
    tar nearest integer number e convert kore nibo*/
    total_granite_need = granite_height_need * granite_width_need;
    printf("%lld\n",total_granite_need);
    return 0;
}
/* h=10, w=6 ar g=4
1  2 3 4 5 6 
1  2 3 4 5 6 
1  2 3 4 5 6 
1  2 3 4 5 6 
1  2 3 4 5 6 
1  2 3 4 5 6 
1  2 3 4 5 6 
1  2 3 4 5 6 
1  2 3 4 5 6 
1  2 3 4 5 6 
eta holo theature square er aunupat akhon 4x4 granite diye eta fillup korte hobe
1 2 3 4
1 2 3 4
1 2 3 4
1 2 3 4
jehetu amora granite ke vangte parbo na tai ceiling kore extra nite hobe*/