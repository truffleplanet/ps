#include <iostream>

using namespace std;

long long fastexp(long long x, long long y);

constexpr int MOD = 1'000'000'007;

int main()
{
  ios_base::sync_with_stdio(false);
  cin.tie(nullptr);

  long long n;
  cin >> n;

  cout << fastexp(2, n) << '\n';
}

long long fastexp(long long x, long long y)
{
  long long res = 1;
  while (y > 0)
  {
    if (y & 1)
      res = (res * x) % MOD;
    x = (x * x) % MOD;
    y >>= 1;
  }
  return res;
}
