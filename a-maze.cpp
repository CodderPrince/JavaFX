#include <bits/stdc++.h>
using namespace std;

// const int mx = 1e4+123;
char mat[505][505];
bool vis[505][505];
int n, m, rtx, rty, k;
int dx[4] = {0, 1, 0, -1}, dy[4] = {1, 0, -1, 0};

void dfs(int x, int y)
{
    vis[x][y] = 1;
    int nx, ny;
    for (int i = 0; i < 4; i++)
    {
        nx = x + dx[i];
        ny = y + dy[i];
        if (nx < 1 || nx > n || ny < 1 || ny > m)
        {
            continue;
        }
        if (vis[nx][ny])
        {
            continue;
        }
        if (mat[nx][ny] != '.')
        {
            continue;
        }
        dfs(nx, ny);
    }
    if (k > 0)
    {
        k--;
        mat[x][y] = 'X';
    }
}

int main()
{
    cin >> n >> m >> k;
    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= m; j++)
        {
            cin >> mat[i][j];
        }
    }

    // calle₫ dfs
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

    // print
    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= m; j++)
        {
            cout << mat[i][j];
        }
        cout << endl;
    }
    
    return 0;
}