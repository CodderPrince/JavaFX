#include <bits/stdc++.h>
using namespace std;

int main()
{
    int n;
    cin >> n;
    while (n--)
    {
        int k;
        cin >> k;
        string s;
        cin >> s;
        int cnt[26] = {0};
        for (int i = 0; i < s.size(); i++)
        {
            cnt[s[i] - 'A']++;
        }
        int ans = 0;
        for (int i = 0; i < 26; i++)
        {
            if (cnt[i] > 1)
                ans += cnt[i] + 1;
            else
                ans += 2 * cnt[i];
        }
        cout << ans << endl;
    }
    return 0;
}