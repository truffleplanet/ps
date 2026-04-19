#include <iostream>

using namespace std;

/*
 *  gray code의 기본적인 순서
 *  원래 순서의 배열 앞에는 모두 0, 뒤집은 배열 앞에는 모두 1을 붙이고 concat
 *  n == 1 -> [0, 1], reversed == [1, 0]
 *  n == 2 -> [00, 01, 11, 10], reversed == [10, 11, 01, 00]
 *  n == 3 -> [000, 001, 011, 010, 110, 111, 101, 100]
 *
 *  위와 같은 규칙을 생각하여 backtrack 구성하기.
 *
 *  start state - depth == 0, isReversed == false
 *  base case - depth == n. 출력, return
 *  if (!isReversed)
 *    output[depth]에 0을 넣고 backtrack(depth + 1, false)
 *    output[depth]에 1을 넣고 backtrack(depth + 1, true)
 *  else
 *    output[depth]에 1을 넣고 backtrack(depth + 1, false)
 *    output[depth]에 0을 넣고 backtrack(depth + 1, true)
 *
 *    0 0 0
 *    0 0 1
 *    0 1 1
 *    0 1 0
 *    1 1 0
 *    1 1 1
 *    1 0 1
 *    1 0 0
 */

char output[17];
int n;

void backtrack(int depth, bool isReversed)
{
  if (depth == n)
  {
    cout << output << '\n';
    return;
  }

  if (!isReversed)
  {
    output[depth] = '0';
    backtrack(depth + 1, false);
    output[depth] = '1';
    backtrack(depth + 1, true);
  } else
  {
    output[depth] = '1';
    backtrack(depth + 1, false);
    output[depth] = '0';
    backtrack(depth + 1, true);
  }

}

int main ()
{
  ios_base::sync_with_stdio(false);
  cin.tie(nullptr);

  cin >> n;

  output[n] = '\0';

  backtrack(0, false);

  return 0;
}