#include <bits/stdc++.h>
using namespace std;

class Solution
{
public:
    int countSubarrays(vector<int> &arr, int k)
    {
        unordered_map<int, int> prefixSumCount; // To store the frequency of prefix sums
        int prefixSum = 0;                      // Running prefix sum
        int count = 0;                          // Number of subarrays with sum k

        for (int num : arr)
        {
            prefixSum += num; // Update the prefix sum

            // If the prefix sum equals k, it means a subarray from the start has sum k
            if (prefixSum == k)
            {
                count++;
            }

            // Check if there is a prefix sum such that (prefixSum - k) exists
            if (prefixSumCount.find(prefixSum - k) != prefixSumCount.end())
            {
                count += prefixSumCount[prefixSum - k];
            }

            // Update the frequency of the current prefix sum
            prefixSumCount[prefixSum]++;
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
        cout << sol.countSubarrays(arr, k) << endl;
    }
    return 0;
}
