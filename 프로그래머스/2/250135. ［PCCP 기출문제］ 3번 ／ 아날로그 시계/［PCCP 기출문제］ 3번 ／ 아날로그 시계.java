/*
초침이 시침 or 분침과 겹치면 알람이 울림.
(초침, 시침) (초침, 분침) (초침, 시침, 분침)


도는 시간을 생각해보면
초침이 1 움직이면
분침은 1/60 움직이고
시침은 1/(3600 * 12)움직인다

이러한 규칙을 생각하면, 
초침이 60 움직여서 제자리로 돌아오면 분침은 1 움직였고
61초 움직이면 분침은 61.xxx에 있을 것이므로, (61, 62) 사이 움직일 때 초침과 분침이 만난다.

시침도 이러한 규칙 적용해서 몇번 만나는지 확인할 수 있을 것이다.
그런데. 세개가 동시에 겹치는 순간은? 어떻게 파악할것인가?
아마 12시 정각 외에는 없으니 그것만 예외케이스로 두기로 한다. 즉 범위 내에 12 0 0 이 있으면 결과에서 1 빼주면 된다.


초별로 시뮬레이션하며, 초침이 시침이나 분침을 넘어가는 순간마다 카운팅을 한다.
둘 다 넘었으면 한번만 카운팅을 한다. 

평소에는 초침이 그전타임에는 분침보다 작았는데, 초침이 더 커지면.. 일것이다.
다만 이런 계산식이면
*/

import java.util.*;

class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        final int dh = 1;
        final int dm = 12;
        final int ds = 720;
        final int total = 43200;
        
        int totalSec = ((h2 - h1) * 3600) + ((m2 - m1) * 60) + (s2 - s1);
        int startSec = (h1 * 3600) + (m1 * 60) + s1;
        int hAngle = 0;
        int mAngle = 0;
        int sAngle = 0;
        
        for (int i = 0; i < startSec; i++) {
            hAngle = (hAngle + dh) % total;
            mAngle = (mAngle + dm) % total;
            sAngle = (sAngle + ds) % total;
        }
            
        int cnt = 0;
                
        if (hAngle == sAngle || mAngle == sAngle) {
            cnt++;
        }
        
        for (int i = 0; i < totalSec; i++) {
            int n_hAngle = (hAngle + dh) % total;
            int n_mAngle = (mAngle + dm) % total;
            int n_sAngle = (sAngle + ds) % total;
            
            if ((n_hAngle == n_mAngle) && (n_mAngle == n_sAngle)) {
                cnt++;
                hAngle = n_hAngle;
                mAngle = n_mAngle;
                sAngle = n_sAngle;
                continue;
            }
            if (n_sAngle < sAngle) {
                if (sAngle < mAngle) 
                    cnt++;
                else if (sAngle < hAngle)
                    cnt++;
                else {
                    if (n_sAngle >= n_mAngle)
                        cnt++;
                    else if (n_sAngle >= n_hAngle)
                        cnt++;
                }
            } else {
                if (sAngle < mAngle && n_sAngle >= n_mAngle)
                    cnt++;
                if (sAngle < hAngle && n_sAngle >= n_hAngle)
                    cnt++;
            }
            
            hAngle = n_hAngle;
            mAngle = n_mAngle;
            sAngle = n_sAngle;
        }
        
        
        return cnt;
    }
}