#include <bits/stdc++.h>
using namespace std;

class Solution
{
public:
    long subarrayXor(vector<int> &arr, int k)
    {
        unordered_map<int, int> prefixXORCount; // To store the frequency of prefix XORs
        int prefixXOR = 0;                      // Running prefix XOR
        long count = 0;                         // Number of subarrays with XOR k

        for (int num : arr)
        {
            prefixXOR ^= num; // Update the prefix XOR

            // If the prefix XOR itself equals k, increment the count
            if (prefixXOR == k)
            {
                count++;
            }

            // Check if (prefixXOR ^ k) exists in the map
            int targetXOR = prefixXOR ^ k;
            if (prefixXORCount.find(targetXOR) != prefixXORCount.end())
            {
                count += prefixXORCount[targetXOR];
            }

            // Update the frequency of the current prefix XOR
            prefixXORCount[prefixXOR]++;
        }

        return count;
    }
};

int main()
{
    int t;
    cin >> t; // Number of test cases
    while (t--)
    {
        int n, k;
        cin >> n >> k;
        vector<int> arr(n);
        for (int i = 0; i < n; ++i)
        {
            cin >> arr[i];
        }

        Solution sol;
        cout << sol.subarrayXor(arr, k) << endl;
    }
    return 0;
}
