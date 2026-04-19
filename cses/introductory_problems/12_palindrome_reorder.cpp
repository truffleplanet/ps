#include <iostream>
#include <string>

using namespace std;

/*
 *  가장 간단하게 생각했을 때,
 *  먼저 알파벳별로 카운팅하고
 *  순회하며 앞뒤로 놓는다
 *  만약 count가 홀수인 경우가 두개 이상이면 false
 *  그 홀수인게 하나면, size도 홀수이고, 중간에 두면 된다.
 */

constexpr int MAX_N = 1'000'000;
char output[MAX_N + 1];

int main()
{
  ios_base::sync_with_stdio(false);
  cin.tie(nullptr);

  string s;
  cin >> s;

  int count[26]{};

  for (char ch : s)
  {
    ++count[ch - 'A'];
  }

  int oddIdx{-1};
  int oddCount{};
  for (int j{}; j < 26; ++j)
  {
    if (count[j] & 1)
    {
      oddIdx = j;
      ++oddCount;
    }
  }

  if (oddCount >= 2)
  {
    cout << "NO SOLUTION\n";
    return 0;
  }

  if (oddIdx != -1)
  {
    output[s.size() / 2] = static_cast<char>('A' + oddIdx);
  }

  output[s.size()] = '\0';
  int i{};
  for (int j{}; j < 26; ++j)
  {
    int pairs = (count[j] >> 1);
    while (pairs--)
    {
      output[i] = static_cast<char>('A' + j);
      output[s.size() - i - 1] = static_cast<char>('A' + j);
      ++i;
    }
  }

  cout << output << '\n';

  return 0;
}
