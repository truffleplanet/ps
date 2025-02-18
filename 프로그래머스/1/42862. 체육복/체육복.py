
def solution(n, lost, reserve):
    lost_set = set(lost) - set(reserve)
    reserve_set = set(reserve) - set(lost)
    
    for k in sorted(lost_set):
        if k - 1 in reserve_set:
            reserve_set.remove(k - 1)
        elif k + 1 in reserve_set:
            reserve_set.remove(k + 1)
        else:
            n -= 1

    return n