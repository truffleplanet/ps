#include <iostream>

using namespace std;

/*
 *  풀이 2. 알려진 수학적 공식 활용
 *  i번째 gray code는 i ^ (i >> 1) 로 표현될 수 있다.
 */

int main()
{
  ios_base::sync_with_stdio(false);
  cin.tie(nullptr);

  int n;
  cin >> n;

  for (int i{}; i < (1 << n); ++i)
  {
    const int gray = i ^ (i >> 1);
    for (int bit = n - 1; bit >= 0; --bit)
      cout << ((gray >> bit) & 1);
    cout << '\n';
  }

  return 0;
}