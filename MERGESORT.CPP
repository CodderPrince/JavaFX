#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define nl endl

void merge(ll arr[], ll l, ll mid, ll r)
{
    ll n1 = mid - l + 1;
    ll n2 = r - mid;

    ll a[n1];
    ll b[n2];

    for (ll i = 0; i < n1; i++)
    {
        a[i] = arr[l + i];
    }
    for (ll i = 0; i < n2; i++)
    {
        b[i] = arr[mid + i + 1];
    }
    ll i = 0, j = 0, k = l;
    while (i < n1 && j < n2)
    {
        if (a[i] < b[j])
        {
            arr[k] = a[i];
            k++;
            i++;
        }
        else
        {
            arr[k] = b[j];
            k++;
            j++;
        }
    }
    while (i < n1)
    {
        arr[k] = a[i];
        k++;
        i++;
    }
    while (j < n2)
    {
        arr[k] = b[j];
        k++;
        j++;
    }
}
void mergeSort(ll arr[], ll l, ll r)
{
    if (l < r)
    {
        ll mid = (l + r) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);

        merge(arr, l, mid, r);
    }
}

int main()
{
    ll arr[] = {4, 8, 1, 5};
    mergeSort(arr, 0, 3);
    ll i;
    for (i = 0; i < 4; i++)
    {
        cout << arr[i] << " ";
    }
    cout << nl;

    return 0;
}