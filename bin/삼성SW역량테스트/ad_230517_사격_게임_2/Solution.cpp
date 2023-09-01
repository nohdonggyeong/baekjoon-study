#include <cstdio>
#include <utility> // pair
 
using namespace std;
 
#define WH_MAX 10
 
enum DIR {
    DIR_H,   // →
    DIR_HU,  // ↗
    DIR_HD,  // ↘
    DIR_W,   // ↓
    DIR_WR,  // ↘
    // H >= W 상태의 map으로 DIR_WL은 DIR_HU에서 모두 처리 가능
    DIR_END
};
 
typedef struct _shoot {
    int score; // 라인의 점수 합
    DIR dir;   // 사격 방향
    int index; // 사격 지점 index
} shoot;
 
shoot shoots[WH_MAX * DIR_END]; // 사격 정보
int map[WH_MAX+1][WH_MAX+1];    // 점수판
 
void setShoots(int& score, DIR dir, int index, int& si) {
    shoots[si].score = score;
    shoots[si].dir = dir;
    shoots[si].index = index;
    si++;
    score = 0;
}
 
// 방향 순서대로 사격 정보 초기화
int fillShoots(int H, int W) {
    int score = 0, si = 0;
    for (int h = 1; h <= H; h++) {
        for (int w = 1; w <= W; w++) {
            score += map[h][w];
        }
        setShoots(score, DIR_H, h, si);
    }
 
    for (int h = 1; h <= H; h++) {
        for (int r = h, w = 1; w <= W && r > 0; r--, w++) {
            score += map[r][w];
        }
        setShoots(score, DIR_HU, h, si);
    }
 
    for (int h = 1; h <= H; h++) {
        for (int r = h, w = 1; w <= W && r <= H; r++, w++) {
            score += map[r][w];
        }
        setShoots(score, DIR_HD, h, si);
    }
 
    for (int w = 1; w <= W; w++) {
        for (int h = 1; h <= H; h++) {
            score += map[h][w];
        }
        setShoots(score, DIR_W, w, si);
    }
 
    // w = 1은 DIR_HD의 h = 1과 동일해서 2부터 시작
    for (int w = 2; w <= W; w++) {
        for (int c = w, h = 1; c <= W && h <= H; c++, h++) {
            score += map[h][c];
        }
        setShoots(score, DIR_WR, w, si);
    }
    return si;
}
 
// 두 사격 정보의 겹치는 좌표를 구함 ( 없거나 하나만 존재 )
pair<int, int> getIntersectCoord(int p, int n, int H, int W) {
    pair<int, int> ret = make_pair(0, 0);
    shoot prev = shoots[p];
    shoot next = shoots[n];
    int temp = 0;
 
    // 같은 방향은 겹칠 수 없음
    // prev와 next의 방향은 순서가 정해져 있음 ( ex. prev가 DIR_W이면 next는 DIR_W 이상 )
    switch (prev.dir) {
        case DIR_H:
            if (next.dir == DIR_HU && prev.index <= next.index) {
                ret = make_pair(prev.index, next.index - prev.index + 1);
            } else if (next.dir == DIR_HD && next.index <= prev.index) {
                ret = make_pair(prev.index, prev.index - next.index + 1);
            } else if (next.dir == DIR_W) {
                ret = make_pair(prev.index, next.index);
            } else if (next.dir == DIR_WR && prev.index + next.index - 1 <= H) {
                ret = make_pair(prev.index, prev.index + next.index - 1);
            }
            break;
        case DIR_HU:
            temp = prev.index - next.index;
            if (next.dir == DIR_HD && temp >= 0 && temp % 2 == 0) {
                temp = temp / 2;
                ret = make_pair(prev.index - temp, temp + 1);
            } else if (next.dir == DIR_W && temp >= 0) {
                ret = make_pair(temp + 1, next.index);
            } else if (next.dir == DIR_WR && temp >= 0 && temp % 2 == 0) {
                temp = (prev.index + next.index) / 2;
                ret = make_pair(prev.index - temp + 1, temp);
            }
            break;
        case DIR_HD:
            if (next.dir == DIR_W && prev.index + next.index - 1 <= H) {
                ret = make_pair(prev.index + next.index - 1 , next.index);
            }
            // DIR_WR은 DIR_HD와 같은 방향
            break;
        case DIR_W:
            if (next.dir == DIR_WR && prev.index >= next.index) {
                ret = make_pair(prev.index - next.index + 1, prev.index);
            }
            break;
        default:
            break;
    }
    return ret;
}
 
// 세 사격 정보의 중복된 점수를 구함
int intersectScore(int i, int j, int k, int H, int W) {
    int score = 0;
    pair<int, int> c1, c2, c3;
 
    c1 = getIntersectCoord(i, j, H, W);
    score = score + map[c1.first][c1.second];
 
    c2 = getIntersectCoord(j, k, H, W);
    score = score + map[c2.first][c2.second];
 
    c3 = getIntersectCoord(i, k, H, W);
    // 좌표 세 개가 같으면 두 번만 제거
    if (c3 != c1 || c3 != c2) {
        score = score + map[c3.first][c3.second];
    }
 
    return score;
}
 
int solve(int H, int W) {
    int size = fillShoots(H, W);
    int currentSum = 0;
    int max = 0;
 
    for (int i = 0; i < size - 2; i++) {
        for (int j = i + 1; j < size - 1; j++) {
            for (int k = j + 1; k < size; k++) {
                currentSum = shoots[i].score + shoots[j].score + shoots[k].score;
                if (currentSum < max) {
                    continue;
                }
                currentSum -= intersectScore(i, j, k, H, W);
                if (currentSum > max) {
                    max = currentSum;
                }
            }
        }
    }
    return max;
}
 
int main(int argc, char** argv) {
    int T, H, W, r, c, v, result;
    scanf("%d", &T);
    for (int t = 1; t <= T; t++) {
        scanf("%d %d", &H, &W);
        // 사격 방향을 단순화 하기 위해, 항상 H가 크도록 map 설정 ( DIR_WL은 DIR_HU에서 모두 처리 )
        if (H > W) {
            for (int h = 1; h <= H; h++) {
                for (int w = 1; w <= W; w++) {
                    scanf("%d", &map[h][w]);
                }
            }
            result = solve(H, W);
        } else {
            for (int h = 1; h <= H; h++) {
                for (int w = 1; w <= W; w++) {
                    scanf("%d", &map[w][h]);
                }
            }
            result = solve(W, H);
        }
        printf("#%d %d\n", t, result);
    }
    return 0;
}