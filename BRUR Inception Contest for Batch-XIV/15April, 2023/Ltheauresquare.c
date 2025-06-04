#include<stdio.h>
#include<math.h>
int main()
{
    long long int theature_height, theature_width, granite_size, total_granite_need;
    double granite_height_need, granite_width_need;
    scanf("%lld %lld %lld",&theature_height,&theature_width,&granite_size);
    granite_height_need = ceil((double)theature_height/(double)granite_size);
    granite_width_need = ceil((double)theature_width/(double)granite_size);
    total_granite_need = granite_height_need * granite_width_need;
    printf("%lld\n",total_granite_need);
    return 0;
}