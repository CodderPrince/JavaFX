#include <bits/stdc++.h>
using namespace std;

class Solution
{
public:
    vector<vector<string>> groupAnagrams(vector<string> &arr)
    {
        unordered_map<string, vector<string>> anagramGroups;

        // Group strings by their sorted forms
        for (string &str : arr)
        {
            string sortedStr = str;
            sort(sortedStr.begin(), sortedStr.end());
            anagramGroups[sortedStr].push_back(str);
        }

        // Collect all groups of anagrams
        vector<vector<string>> result;
        for (auto &group : anagramGroups)
        {
            result.push_back(group.second);
        }

        return result;
    }
};

int main()
{
    int n;
    cin >> n; // Number of strings
    vector<string> arr(n);
    for (int i = 0; i < n; ++i)
    {
        cin >> arr[i];
    }

    Solution sol;
    vector<vector<string>> groups = sol.groupAnagrams(arr);

    // Print grouped anagrams
    for (auto &group : groups)
    {
        cout << "[";
        for (int i = 0; i < group.size(); ++i)
        {
            cout << group[i];
            if (i != group.size() - 1)
                cout << ", ";
        }
        cout << "]" << endl;
    }

    return 0;
}
