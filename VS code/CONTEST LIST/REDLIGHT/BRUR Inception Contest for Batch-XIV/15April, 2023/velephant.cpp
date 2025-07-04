
#include <iostream>
using namespace std;
int main()
{
    int x;   
    //scanf("%d", &x); //tar friend er barir position input nibo
    cin>>x;
    int count = 0; // start e count 0 kore dibo
    while (x > 0)
    //ei condition ta question e deya ase tai ei aunujayi loop use korbo
    {
        count++; 
        /*tar friend er barir position jodi 0 theke boro hoi tahole
        start ei count er value 1 increase korbo.. */
        x = x - 5;
        /*question e bola ase hati ta one time e 5 move porjonto jete pare tai every time count
        korbo ar total position theke 5 sub korte thakbo cause ekta count shes holei hati ti 
        5 ta move egiye jabe tai 5 ta move sub kora hoyese...
        eibhabe loop ti totokhon cholbe jotokhon 0 theke value boro thake*/
    }
    //printf("%d\n", count);
    cout<< count<<endl;
    return 0;
}
/*exmaple x=12
s1: count=1; x= 12-5=7
s2: count=2; x= 7-5= 2
s3: count=3; x= 2-5= -3
ar loop cholbe na karon x!>0*/

