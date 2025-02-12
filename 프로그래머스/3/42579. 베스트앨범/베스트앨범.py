def solution(genres, plays):
    answer = []
    
    d = {}
    for i, (g, p) in enumerate(zip(genres, plays)):
        if g not in d:
            d[g] = {
                "plays": [],
                "total": 0
                   }
        d[g]["plays"].append((p, i))
        d[g]["total"] += p
        
    total = []
    for g, v in d.items():
        d[g]["plays"].sort(key=lambda x: (x[0], -x[1]), reverse=True)
        total.append((d[g]["total"], g))

    total.sort(key=lambda x: x[0], reverse=True)
    
    print(d)
    print(total)
    for g in total:
        genre = g[1]
        first = d[genre]["plays"][0][1]
        answer.append(first)
        try:
            second = d[genre]["plays"][1][1]
            answer.append(second)
        except:
            pass
        
    return answer