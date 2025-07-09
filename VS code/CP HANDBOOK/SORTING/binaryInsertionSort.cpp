// Binary insertion sort

#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define nl endl

int main()
{
    ll arr[] = {5, 4, 8, 1, 9, 6, 2};
    ll i, value, hole;
    for (i = 1; i < 7; i++)
    {
        value = arr[i];
        hole = i;
        while (hole > 0 && value < arr[hole - 1])
        {
            arr[hole] = arr[hole - 1];
            hole--;
        }
        arr[hole] = value;
    }

    for (i = 0; i < 7; i++)
    {
        cout << arr[i] << " ";
    }
    cout << nl;

    return 0;
}