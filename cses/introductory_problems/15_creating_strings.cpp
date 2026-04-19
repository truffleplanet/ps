#include <cstdio>

using namespace std;

/*
 * 연습삼아 qsort 구현해보고
 * backtrack으로도 구현해보고
 * nextPermutaion도 구현해보기로 한다.
 */

void mySwap(char *arr, int x, int y);
void quickSort(char *arr, int l, int r);
bool nextPermutation(char *arr, int size);
int factorial(int n);
// void backtrack(char *output, char *arr, bool *used, int depth, int size);

int main()
{
  char s[9];
  scanf("%s", s);

  int size{};
  for (int i{}; i < 9; ++i)
  {
    if (s[i] == '\0')
    {
      size = i;
      break;
    }
  }
  s[size] = '\0';

  int count[26]{};
  for (char c: s)
  {
    ++count[c - 'a'];
  }

  int totalCase{factorial(size)};
  for (int n: count)
  {
    totalCase /= factorial(n);
  }
  printf("%d\n", totalCase);

  quickSort(s, 0, size - 1);

  do
  {
    printf("%s\n", s);
  } while (nextPermutation(s, size));

  return 0;
}

void mySwap(char *arr, int x, int y)
{
  const char temp = arr[x];
  arr[x] = arr[y];
  arr[y] = temp;
}

void quickSort(char *arr, int l, int r)
{
  if (l >= r)
    return;

  const int mid = (l + r) / 2;
  if (arr[l] > arr[mid]) mySwap(arr, l, mid);
  if (arr[l] > arr[r]) mySwap(arr, l, r);
  if (arr[mid] > arr[r]) mySwap(arr, mid, r);
  mySwap(arr, mid, r);

  const char pivot{arr[r]};
  int i{l - 1};

  for (int j{l}; j < r; ++j)
  {
    if (arr[j] < pivot)
    {
      ++i;
      mySwap(arr, i, j);
    }
  }

  mySwap(arr, i + 1, r);
  quickSort(arr, l, i);
  quickSort(arr, i + 2, r);
}

bool nextPermutation(char *arr, int size)
{
  int i{size - 2};
  while (i >= 0 && arr[i] >= arr[i + 1])
    --i;

  if (i < 0)
    return false;

  int j{size - 1};
  while (arr[j] <= arr[i])
  {
    --j;
  }
  mySwap(arr, i, j);

  int left{i + 1};
  int right{size - 1};
  while (left < right)
  {
    mySwap(arr, left, right);
    ++left;
    --right;
  }
  return true;
}

int factorial(int n)
{
  int res{1};
  for (int i{2}; i <= n; ++i)
  {
    res *= i;
  }
  return res;
}

// void backtrack(char *output, char *arr, bool *used, int depth, int size)
// {
//   if (depth == size)
//   {
//     printf("%s\n", output);
//     return;
//   }
//
//   for (int i = 0; i < size; ++i)
//   {
//     if (used[i]) continue;
//
//     if (i > 0 && arr[i] == arr[i - 1] && !used[i - 1])
//     {
//       continue;
//     }
//     output[depth] = arr[i];
//     used[i] = true;
//     backtrack(output, arr, used, depth + 1, size);
//     used[i] = false;
//   }
// }
