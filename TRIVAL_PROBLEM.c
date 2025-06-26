/*muloto problem tai bola hoyese factorial er sheshe kotogulo zero ase seta print korte
amora jani sorbonimno zero pawa jai 5-9 porjonto 1ta kore and 5 number porpor zero ekta kore 
barte thake
input 1-4 porjonto dile kotogulo zero pawa jai sei sonkhagulo print hobe
ekkothai jei sonkhata input dibo totogulo zero kon kon sonkhar factorial er shese pawa jai seigulo
print korte hobe 6 input dile jei jei sonkhar fact er sheshe 6 ta zero pawa jai seigulo print hobe*/
#include<stdio.h>
int main()
{
    int m;
    scanf("%d",&m);
    int num, x, sum=0;
    for(int i=1; i<=100000; i++)//condition deya ase kotokhon loop ta cholbe
    {
        num=1;
        x=i;
        while(1)
        {
            if(x%5==0)//x ke vag kore jodi vagfol 0 pawa jai tahole niche kaj korbe
            {
                num++;
                x= x/5;//x ke 5 dara vag korbe jate x er man 1 hoi
            }
            else
            {
                break;
            }
        }
        sum= sum + num;
        if(sum == m)
        {
            printf("5\n");
            for(int j=5*i; j<5*i+5; j++ )
            {
                printf("%d ",j);
            }
            return 0;
        }
        else if(sum>m)
        {
            break;
        }
    }
    printf("0");
    return 0;
}