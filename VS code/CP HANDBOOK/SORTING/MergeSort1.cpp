// MERGE SORT

#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define nl endl

void merge(ll arr[], ll l, ll mid, ll r)
{
    ll left_size = mid - l + 1;
    ll right_size = r - mid;

    ll L[left_size];
    ll R[right_size];
    for (ll i = 0; i < left_size; i++)
    {
        L[i] = arr[l + i];
    }
    for (ll i = 0; i < right_size; i++)
    {
        R[i] = arr[mid + 1 + i];
    }

    ll i = 0;
    ll j = 0;
    ll k = l;
    while (i < left_size && j < right_size)
    {
        if (L[i] < R[j])
        {
            arr[k] = L[i];
            k++;
            i++;
        }
        else
        {
            arr[k] = R[j];
            k++;
            j++;
        }
    }

    while (i < left_size)
    {
        arr[k] = L[i];
        k++;
        i++;
    }

    while (j < right_size)
    {
        arr[k] = R[j];
        k++;
        j++;
    }
}

void mergeSort(ll arr[], ll l, ll r)
{

    if (l == r)
    {
        return;
    }

    if (l < r)
    {
        ll mid = l + (r - l) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);

        merge(arr, l, mid, r);
    }
}

int main()
{
    ll arr[] = {5, 4, 3, 1, 2};
    mergeSort(arr, 0, 4);
    for (ll i = 0; i < 5; i++)
    {
        cout << arr[i] << " ";
    }
    cout << nl;

    return 0;
}
