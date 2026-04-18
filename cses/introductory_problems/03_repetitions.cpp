#include <iostream>

using namespace std;

int main()
{
  ios_base::sync_with_stdio(false);
  cin.tie(nullptr);
  cout.tie(nullptr);

  char s[1000001];
  cin >> s;

  int res{};
  int cnt{};
  char prev{'0'};
  for (int i{}; s[i] != '\0'; ++i)
  {
    char c = s[i];
    if (c != prev)
    {
      res = max(res, cnt);
      prev = c;
      cnt = 0;
    }
    ++cnt;
  }
  res = max(res, cnt);

  cout << res << '\n';
}