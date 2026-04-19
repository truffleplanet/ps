#include <iostream>

using namespace std;

/*
 * legal move (-2, -1), (-1, -2)
 *
 * x = 2a + b
 * y = a + 2b
 *
 * a, b가 0이거나 양의 정수일 때
 * 입력 값 x == (2a + b), y == (a + 2b)
 *
 * 이 되게 하는 a, b가 존재하는가?
 *
 *  move 총량은 항상 -3이므로, a + b가 3의 배수여야 함.

 * 풀이 1. 비효율적 그리디
 * 좌 우 중 남은 더미가 더 큰 곳에서 더 많이 빼는 움직임을 취하는 것을 반복한다.
 * (0, 0)이 되면 true, 음수가 되면 false
 * 대략 10^9 * 10^5  만큼 해봐야함.
 *
 * 풀이 2. 수학적 접근 - 연립 방정식
 * x = 2a + b
 * y = a + 2b
 * a = y - 2b
 * b = x - 2a
 * a = y - 2(x - 2a)
 * a = y - 2x + 4a
 * a = (2x - y) / 3
 * b = (2y - x) / 3
 *
 * 그렇다면
 * 1) (2x -y) % 3 == 0, 2(2y - x) % 3 == 0 이면 a, b는 정수이고
 * 2) 2x - y >= 0, 2y -x >= 0 이면 a, b는 0보다 크다.
 * 근데 1) 의 경우 (x + y) % 3 == 0이면
 * 3x - (x + y)로 표현할 수 있기 때문에 마찬가지로 %3=0이 된다.
 */

int main()
{
  ios_base::sync_with_stdio(false);
  cin.tie(nullptr);

  int t;
  cin >> t;

  do
  {
    int x, y;
    cin >> x >> y;
    const bool divisible = (x + y) % 3 == 0;
    const bool inRange = (2 * x >= y) && (2 * y >= x);

    if (divisible && inRange)
      cout << "YES\n";
    else
      cout << "NO\n";

  } while (--t);

  return 0;
}
