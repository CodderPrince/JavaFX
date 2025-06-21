#include <iostream>
#include <bits/stdc++.h>

#define ll long long
#define endl "\n"
using namespace std;

const ll mx = 1e4 + 123;

int k;
char mat[mx][mx];

void dfs(int x, int y)
{
    //base case
    if (mat[x][y] != '.' || !k)
    {
        return;
    }
    mat[x][y] = 'O';
    dfs(x + 1, y);
    dfs(x, y + 1);
    dfs(x - 1, y);
    dfs(x, y - 1);

    if (k > 0)
    {
        k--;
        mat[x][y] = 'X';
        return;
    }
    else
    {
        mat[x][y] = '.';
    }
}

signed main()
{
    int n, m;
    cin >> n >> m >> k;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            cin >> mat[i][j];
        }
    }

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (mat[i][j] == '.')
            {
                dfs(i, j);
            }
        }
    }
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            cout << mat[i][j];
        }
        cout << endl;
    }

    return 0;
}
