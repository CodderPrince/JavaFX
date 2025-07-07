// Binary Search Best way

#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define nl endl

int main()
{
    ll arr[] = {1, 3, 34, 45, 78, 113, 114, 124, 145, 160, 200};
    ll left = 0, right = 10, middle = 0, find_item = 114;
    while (left <= right)
    {
        middle = (left + right) / 2;
        if (arr[middle] == find_item)
        {
            cout << "Item found at index: " << middle << nl;
            return 0;
        }
        else if (arr[middle] < find_item)
        {
            left = middle + 1;
        }
        else
        {
            right = middle - 1;
        }
    }
    cout << "Item not found\n";

    return 0;
}