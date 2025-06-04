#include <stdio.h>
#include <string.h>
int main() 
{
  char sentence[1000001];
  //sring size 10^5 hote para tai sei size neya hoyese
  scanf("%s", sentence);
  int len = strlen(sentence);
  while(1)
  {
    if(len == 0)//1
    //ami ekhane word er delete korbo ekta sequence aunujayi jodi 0 hoi tahole yes
    {
      printf("YES\n");
      break;
    }
    else if(len < 4)//2
    //dream and erase every word er minimum length 4 . so er theke length kom holei no
    {
      printf("NO\n");
      break;
    }
    if(strncmp(sentence + len - 5, "dream", 5) == 0)//3 
    {
      len = len - 5;
    }
    else if(strncmp(sentence + len - 5, "erase", 5) == 0) //4
    {
      len = len - 5;
    }
    else if(len >= 6 && strncmp(sentence + len - 6, "eraser", 6) == 0) //5
    {
      len = len - 6;
    }
    else if(len >= 7 && strncmp(sentence + len - 7, "dreamer", 7) == 0) //6
    {
      len = len - 7;
    }
    else
    {
      printf("NO\n");//7
      break;
    }
  }
  
  return 0;
}
/*exmaple: dreamerase
string start at 0 index here len 0-9==10
1st e string ta scanf howar loop er moddhe diye aste thakbe ar condition check hote thakbe
4 no condition e ese milbe 1st e condition diyesi sentence er moddhe total len theke 5 sub
kore mane len 5 and 5 index theke last porjonto (jehetu character compare hobe 5 ta set kora)
jodi 'erase' word ta pai tahole len= len(10)-5=5 korbe and seta holo akhon len=5
loop abar while e chole jabe ebar niche aste aste 3 number loop e dukbe karon ei condition
ta fill up hoi ar amar len er moddhe akhon shudu 'dream' word baki ase condition fill up 
howar por len= len(5)-5=0 hobe abar while er start e giye 1 number condition fillup hoi tai
YES print kore dibe
*/

/*input 1: erasedream
1. 3 number condition fill up hobe len= 10-5=5; len= 'erase'
2. 4 number condition fill up hobe len= 5-5=0; len=0
3. print YES*/

/*input 2: dreameraser
1. 5 number condition fill up hobe len= 11-6=5; len= 'dream'
2. 3 number condition fill up hobe len= 5-5=0; len=0
3. print YES*/

/*input 3: dreamerer
1. while loop er moddhe theke ektar por ekta condition check korte thakbe
kintu ektao fill up hobe na ta 7 number condtion er moddhe dhukbe
3. print NO*/