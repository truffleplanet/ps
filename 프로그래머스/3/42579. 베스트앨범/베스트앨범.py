from collections import defaultdict


def solution(genres, plays):
    
    d = defaultdict(lambda: {
        "songs": [],
        "total": 0
    })
    for idx, (genre, play_count) in enumerate(zip(genres, plays)):
        d[genre]["songs"].append((play_count, idx))
        d[genre]["total"] += play_count 
        
    sorted_d = sorted(d.items(), key=lambda x: x[1]["total"], reverse=True)
    
    answer = []
    for _, info in sorted_d:
        info["songs"].sort(key=lambda x: (-x[0], x[1]))
        
        # slicing은 index out of range error 발생 x
        tops = info["songs"][:2]
        for _, idx in tops:
            answer.append(idx)
        
    return answer