import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * [기본 정보]
 * 1. 지나갈 수 있는 길 -> 한 행/열의 높이가 모두 같아야 함
 * 2. 높이가 1 차이가 나는 경우 경사로로 연결 가능(낮은 칸에 배치)
 * 3. 경사로의 길이는 1 ~ N
 * 
 * [기획]
 * 최대로 지나갈 수 있게 경사로를 배치하는 문제가 아니다.
 * 어떤 행/열이 지나갈 가능성이 있는지 여부만 확인하는 문제임.
 * 즉 모든 행과, 모든 열의 경우는 독립적임.
 * 그렇다면 어떤 숫자 배열이 있을 때, 그 배열이 경사로를 두면 평평해지는지, 아닌지 평가하는 로직만 만들면 됨.
 * 배열 순차로 읽어가며 true/false 반환하는 상태머신 만들 수 있다면 best일 듯 함.
 * - 양방향 동시에 고려하며 가는 상태머신 vs 단방향 상태머신만 만들고 역방향 포함하여 두번 체크
 * - 양방향이 시간복잡도 줄어들 것 같지만, 체크할 if문이 증가하기 때문에 많이 줄 것이 기대되지 않음.
 * - 다만 단방향시, 경사로 위에 경사로를 지을 수 없으므로 별도의 boolean 배열 등이 필요해지므로 해당 복잡도 증가
 * 
 * 결론: 양방향이 좀 더 복잡해보이지만 한번 도전해보기로
 * 
 * [로직]
 * 상태: 인접 동일 높이 등장 횟수 cnt, 현재 높이 h, 지금 높이에서 이전 방향으로 경사로 필요 -  needBuild 
 * 초기: 1, h_0, false 로 시작 
 * diff = h_i - h_(i-1)
 * 
 * 	1. abs(diff)가 2 이상이면 false 
 * 	2. abs(diff)가 0 이면 cnt++ (needBuild true && cnt >= L, then cnt -= L, needBuild false)
 * 	3. abs(diff)가 1이면
 * 		1) if diff == 1  
 * 			cnt >= L then true, else false (낮은 곳에 경사를 놓을 수 있음)
 * 			cnt = 1, h = h_i
 *  	2) if diff == -1 && !needBuild
 *  		cnt = 1, h = h_i, needBuild == true
 *  
 *  마지막에 이제
 *  needBuild - false 이고 문제 없으면 ok 
 */

public class Main {

	static int N;
	static int L;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		int[][] horizontals = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				horizontals[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] verticals = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				verticals[i][j] = horizontals[j][i];
			}
		}

		int ans = 0;
		for (int[] seq : horizontals) {
			if (isAvailable(seq)) {
				ans++;
			}
		}
		for (int[] seq : verticals) {
			if (isAvailable(seq)) {
				ans++;
			}
		}

		System.out.println(ans);

	}

	static boolean isAvailable(int[] seq) {
		int cnt = 1;
		int h = seq[0];
		boolean needBuild = false;

		for (int i = 1; i < N; i++) {
			int diff = seq[i] - h;

			if (diff >= 2 || diff <= -2) {
				return false;
			}

			if (diff == 0) {
				cnt++;
			}
			
			if (needBuild && cnt >= L) {
				cnt -= L;
				needBuild = false;
			}
			
			if (diff == 1) {
				if (needBuild || cnt < L) {
					return false;
				}
				cnt = 1;
				h = seq[i];
			}
			
			if (diff == -1) {
				if (needBuild) {
					return false;
				}
				cnt = 1;
				h = seq[i];
				needBuild = true;
				
			}
		}
		
		if (needBuild && cnt >= L) {
			cnt -= L;
			needBuild = false;
		}


		if (needBuild) {
			return false;
		} else {
			return true;
		}
	}

}
