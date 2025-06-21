/*#include <iostream>
#include <vector>
using namespace std;

int main() {
    int a, b;
    cin >> a >> b;
    string pattern, text;
    cin >> pattern >> text;
    int m = pattern.length();
    int n = text.length();

    vector<bool> dp(n + 1, false);
    dp[0] = true;

    // Initialize dp[0][j] for patterns like "*"
    for (int j = 1; j <= n; ++j) {
        if (pattern[0] == '*')
            dp[j] = true;
        else
            break;
    }

    // Fill the dp table iteratively
    for (int i = 1; i <= m; ++i) {
        bool prev = dp[0];  // Store the value of dp[i-1][0] before updating it
        dp[0] = dp[0] && (pattern[i - 1] == '*');
        for (int j = 1; j <= n; ++j) {
            bool temp = dp[j]; // Store the value of dp[i-1][j] before updating it
            if (pattern[i - 1] == '?' || pattern[i - 1] == text[j - 1])
                dp[j] = prev;
            else if (pattern[i - 1] == '*')
                dp[j] = dp[j] || dp[j - 1];
            else
                dp[j] = false;
            prev = temp; // Update prev for the next iteration
        }
    }

    if (dp[n])
        cout << "YES\n";
    else
        cout << "NO\n";

    return 0;
}
#include <iostream>
#include <vector>
#include <string>
using namespace std;

vector<vector<int>> memo;

bool matchPattern(const string& pattern, const string& str, int pIndex, int sIndex) {
    if (pIndex == pattern.size() && sIndex == str.size()) {
        return true;
    }

    if (pIndex == pattern.size() || sIndex == str.size()) {
        return false;
    }

    if (memo[pIndex][sIndex] != -1) {
        return memo[pIndex][sIndex];
    }

    if (pattern[pIndex] == '*') {
        for (int i = sIndex; i <= str.size(); ++i) {
            if (matchPattern(pattern, str, pIndex + 1, i)) {
                return memo[pIndex][sIndex] = true;
            }
        }
        return memo[pIndex][sIndex] = false;
    }

    if (pattern[pIndex] == '?' || pattern[pIndex] == str[sIndex]) {
        return memo[pIndex][sIndex] = matchPattern(pattern, str, pIndex + 1, sIndex + 1);
    }

    return memo[pIndex][sIndex] = false;
}

int main() {
    int n, m;
    cin >> n >> m;

    string pattern, str;
    cin >> pattern >> str;

    memo.assign(n + 1, vector<int>(m + 1, -1));

    if (matchPattern(pattern, str, 0, 0)) {
        cout << "YES" << endl;
    } else {
        cout << "NO" << endl;
    }

    return 0;
}

*/

#include <bits/stdc++.h>
using namespace std;

int main()
{
    int n, m, i = 0;
    string s, t;
    cin >> n >> m >> s >> t;
    while (s[i] == t[i] && i < min(n, m))
    {
        i++;
    }
    while (s[n - 1] == t[m - 1] && i < min(n, m))
    {
        n--, m--;
    }
    if (s[i] == '*' && n - i == 1 || s == t)
    {
        cout << "YES" << endl;
    }
    else
    {
        cout << "NO" << endl;
    }
    return 0;
}

/*
#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define mod 1000000007            // 1e9 + 7
#define PI 3.14159265358979323846 // acos(-1)
#define nl "\n"

int main()
{
    int a, b;
    cin >> a >> b;
    string pattern, text;
    cin >> pattern >> text;
    int m = pattern.length();
    int n = text.length();

    vector<vector<int>> dp(m + 1, vector<int>(n + 1, 0));

    // Base cases initialization
    dp[0][0] = 1;
    for (int i = 1; i <= m; ++i) {
        if (pattern[i - 1] == '*')
            dp[i][0] = dp[i - 1][0];
    }

    // Constructing the dp table
    for (int i = 1; i <= m; ++i) {
        for (int j = 1; j <= n; ++j) {
            if (pattern[i - 1] == '?' || pattern[i - 1] == text[j - 1])
                dp[i][j] = dp[i - 1][j - 1];
            else if (pattern[i - 1] == '*')
                dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
        }
    }

    if (dp[m][n])
        cout << "YES\n";
    else
        cout << "NO\n";

    return 0;
}
#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define mod 1000000007            // 1e9 + 7
#define PI 3.14159265358979323846 // acos(-1)
#define nl "\n"

int main()
{
    int a, b;
    cin >> a >> b;
    string pattern, text;
    cin >> pattern >> text;
    int m = pattern.length();
    int n = text.length();

    vector<vector<int>> dp(m + 1, vector<int>(n + 1, 0));

    // Base cases initialization
    dp[0][0] = 1;
    for (int i = 1; i <= m; ++i) {
        if (pattern[i - 1] == '*')
            dp[i][0] = dp[i - 1][0];
    }

    // Constructing the dp table
    for (int i = 1; i <= m; ++i) {
        for (int j = 1; j <= n; ++j) {
            if (pattern[i - 1] == '?' || pattern[i - 1] == text[j - 1])
                dp[i][j] = dp[i - 1][j - 1];
            else if (pattern[i - 1] == '*')
                dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
        }
    }

    if (dp[m][n])
        cout << "YES\n";
    else
        cout << "NO\n";

    return 0;
}

*/