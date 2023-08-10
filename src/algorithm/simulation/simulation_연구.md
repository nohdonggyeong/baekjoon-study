## 시뮬레이션(simulation)
- O(1)의 시간이 소모되는 queue로 모든 case를 계산하여 푼다.
- ArrayList는 시간 소모가 O(n)으로 크다.
- ArrayList
    add(): O(1)
    remove(): O(n)
    get(): O(1)
    contains(): O(n)
- PriorityQueue
    offer(): O(logn)
    peak(): O(1)
    poll(): O(logn)
    size(): O(1)
- LinkedList
    offer(): O(logn)
    peak(): O(1)
    poll(): O(1)
    size(): O(1)
- HashMap
    get(): O(1)
    containsKey(): O(1)
    next(): O(h/n)