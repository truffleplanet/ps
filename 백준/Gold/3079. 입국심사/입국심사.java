import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 제약조건: 
 * 입국 심사대 N은 최대 10만 
 * 줄서있는 사람의 수는 최대 10억 
 * 최대 대기시간은 10억 
 * 
 * 제한시간 1초.
 * 
 * 
 * 1. 줄 서 있는 사람의 수가 매우 크므로, 순회 불가능함. 
 * 2. 입국 심사대를 기준으로 생각하는 것이 현실적임. 
 * 
 * 구현:
 * 시간 t를 매개변수화하고,
 *  t / 입국 심사에 걸리는 시간은 그 시간 동안 해당 심사대가 감당할 수 있는 인원임.
 *  t를 이분탐색하며, 입국 심사대가 해당 시간에 주어진 인원이 감당 가능한지 구한다.
 *  
 *  고민지점: 
 *  10만 크므로, 입국 심사대 정렬해두고, 조기종료 조건 세우도록 할지..
 *  아니면 정렬 안하고 조기 종료 조건만 사용할지 고민됨. 
 *  둘다 제출해보자
 * 
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] times = new int[N];
		long maxT = 0;
		for (int i = 0; i < N; i++) {
			times[i] = Integer.parseInt(br.readLine());
			maxT = Math.max(maxT, times[i]);
		}
		long lo = 0;
		long hi = maxT * M;
//		System.out.println(Long.MAX_VALUE);
		long bestTime = Long.MAX_VALUE;
		// inclusive
		while (lo <= hi) {
			long mid = lo + ((hi - lo) >>> 1);
			long sum = 0;
			for (int i = 0; i < N; i++) {
				sum += mid / times[i];
				if (sum >= M) {
					break;
				}
			}

			if (sum >= M) {
				bestTime = Math.min(bestTime, mid);
				hi = mid - 1; // 좌측 탐색
			} else {
				lo = mid + 1;
			}
		}

		System.out.println(bestTime);
	}
}
