import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 땅의 높이  0 <= H <= 256
 * 블럭 제거 2초
 * 블럭 놓기 1초 
 * 
 * 블럭 제거하면 인벤토리에 추가.
 * 
 * 1 0 2
 * 2 2 2
 * 2 2 2
 * 
 * 인벤토리에 블럭 3개인 경우.
 * 3초 
 * 
 * 인벤토리에 블럭 3개 미만인 경우.
 * 어쨌든 1로 높이를 맞춰야함.
 * 
 * 즉 가진 블럭수에 따라서 최대 높이가 정해진다.
 * 그 상황에서 최대 높이 솔루션이 좋은지,
 * 한 층 더 내려가는게 빠른지 확인한다..
 * 
 * 2 2 10^7
 * 256 0 
 * 0   0 
 * 
 * 이 경우 한층 올리는 비용은 3
 * 한층 내리는 비용은 2 이므로. 내려가는게 유리하다. 
 * 
 * 1 2 10^7
 * 256 0
 * 이 경우 한층 쌓는게 유리하다. 
 * 
 * 이럴 때는..?
 * 54 56 55
 * 55 55 55 
 * 
 * 혹은 ? 
 * 3 3 0
 * 54 55 56
 * 53 57 58
 * 99 0  55
 *
 *
 * 결과적으로 순서는 중요하지 않다. 
 * 
 * 최소 높이와 최대 높이 사이의 모든 높이에 대해
 * 만드는 것이 가능한지, 비용이 얼마인지 탐색한다.
 * 
 * 제거 쌓기에 드는 비용을 계산해본후,
 * 인벤토리가 음수면 false. 
 *   
 * 
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		// O(N * M * 256) -- > 500 * 500 * 256 < 10^8
		// 최대 가능성 범위를 알 수 있나? 가능
		// 배열의 값 전부 + 인벤토리를 M * N 으로 나눈 값.(버림)

		// 모든 높이 순회
		// 배열 순회하며
		// 제거
		// 추가
		// 가능성 확인하기

		st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int inventory = Integer.parseInt(st.nextToken());

		int mapSize = H * W;
		int[][] map = new int[H][W];
		int blockTotal = inventory;
		int minHeight = 257;

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				int v = Integer.parseInt(st.nextToken());
				map[i][j] = v;
				blockTotal += v;
				minHeight = Math.min(minHeight, v);
			}
		}

		int maxHeight = blockTotal / (mapSize);
		maxHeight = Math.min(maxHeight, 256);
		int height = 0;
		int minTime = Integer.MAX_VALUE;

		for (int h = minHeight; h <= maxHeight; h++) {
			int inv = inventory;
			int time = 0;
			traversal: for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {

					if (time > minTime) // 가지치기.
						break traversal;

					int v = map[i][j]; // 제거 추가 계산
					if (v > h) {
						int diff = v - h;
						inv += diff;
						time += diff * 2; // 칸당 2초
					} else { // 같거나.. 낮을 때
						int diff = h - v;
						inv -= diff;
						time += diff; // 칸당 1초
					}
				}
			}

			// 결산
			if (inv >= 0 && time <= minTime) { // 가장 높은 땅을 출력해야 하니 같거나 작다로.
				minTime = time;
				height = h;
			}
		}

		System.out.println(minTime + " " + height);

	}

}
