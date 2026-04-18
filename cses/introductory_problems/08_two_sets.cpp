#include <iostream>

using namespace std;

/*
 *  1~n까지의 홀수가 홀수개이면 합도 홀수이다
 *  1, 5, 9... 위치들은 홀수가 홀수이고
 *  2, 6, 10 ... 위치들도 홀수가 홀수개이다.
 *  1 + 4k, 2 + 4k 인 위치들은 홀수가 홀수개인 위치이다
 *  k % 4 == 1 || k % 4 == 2 이면 따라서 false이다.
 *
 *  나머지 경우를 보자.
 *  k % 4 == 0이면
 *  4는 2로 나눌 시 여전히 짝수이므로
 *  따라서 1~n까지 수를 (1, n), (2, n-1)... 대칭하게 묶은 쌍의 개수도 짝수이므로
 *  각 집합에 반반씩 할당할 수 있다.
 *
 *  마지막으로 k % 4 == 3 인 경우
 *  3은 (1, 2), (3) 으로 분리할 수 있는데
 *  그 이후 경우인 7, 11, 15 ... 의 경우 항상 4개의 연속된 숫자가 새로 추가되는 꼴이다.
 *  따라서 이후 연속된 네개의 수를 대칭하게 묶은 후 그 쌍을 양 집합에 나눠주면 된다.
 */

int main()
{
  ios_base::sync_with_stdio(false);
  cin.tie(nullptr);

  int n;
  cin >> n;

  if (n % 4 == 1 || n % 4 == 2)
  {
    cout << "NO\n";
  }
  else if (n % 4 == 3)
  {
    cout << "YES\n";

    const int half{n / 2};
    cout << half + 1 << '\n';

    const int midpointAfterBase {(4 + n) / 2};
    cout << "1 2 ";
    for (int i{4}; i < midpointAfterBase; i += 2)
    {
      cout << i << ' ' << n - i + 4 << ' ';
    }
    cout << '\n';

    cout << half << '\n';
    cout << "3 ";
    for (int i{5}; i <= midpointAfterBase; i += 2)
    {
      cout << i << ' ' << n - i + 4 << ' ';
    }
    cout << '\n';
  }
  else if (n % 4 == 0)
  {
    cout << "YES\n";

    const int half{n >> 1};
    cout << half << '\n';

    for (int i{1}; i < half; i += 2)
    {
      cout << i << ' ' << n - i + 1 << ' ';
    }
    cout << '\n';

    cout << half << '\n';
    for (int i{2}; i <= half; i += 2)
    {
      cout << i << ' ' << n - i + 1 << ' ';
    }
    cout << '\n';
  }

  return 0;
}
