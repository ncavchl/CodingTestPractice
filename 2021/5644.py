# -*- coding: utf-8 -*- 
import sys

#무, 상, 우, 하, 좌
dx = [0,-1,0,1,0]
dy = [0,0,1,0,-1]

initx = 0
inity = 0

def batterydfs(i, j, C, P, num, visit):
    # print(visit)
    # print('-----????????----', visit[2][3])
    if visit[i][j] == 0 :
        # print('------battery', i, j, visit[i][j], C, P, visit[2][3])
        visit[i][j] = 1
        
        if map[i][j][0] == 0:
            map[i][j][0] = P
            map[i][j][2] = num
        else :
            #다른 배터리 입장에서 겹칠경우 지도에 최대 2개만 남김
            if map[i][j][0] <= P:
                temp = map[i][j][0]
                tempc = map[i][j][2]
                map[i][j][0] = P
                map[i][j][1] = temp
                map[i][j][2] = num
                map[i][j][3] = tempc
            else :
                map[i][j][1] = P
                map[i][j][3] = num

        for k in range(1,5):
            x = i + dx[k]
            y = j + dy[k]
            cc = abs(x-initx) + abs(y-inity)
            # if C == 0 and x >= 0 and y>=0 and x < 10 and y < 10:
            #     print()
            # else:
            if cc <= C :
                # print(x,'-',y, '-', cc, visit[x][y])
                if x >= 0 and y>=0 :
                    if x<10 and y<10 :
                        # print('position', x, y, 'pupupu', cc)
                        # print('iuninin', x, y, visit[x][y] ,'----', cc)
                        batterydfs(x, y, C, P, num ,visit)
    else :
        return


T = int(input())

for test_case in range(1, T + 1):
    answer = 0

    #백준 제출용
    # M, A = map(int, input().split())
    # ma = list(map(int, input().split()))
    # mb = list(map(int, input().split()))
    # ap = [list(map(int, input().split())) for _ in range(A)]

    #좌표1,2 거리 용량
    #vscode 용 
    M, A = map(int, sys.stdin.readline().split())
    ma = list(map(int, sys.stdin.readline().split()))
    mb = list(map(int, sys.stdin.readline().split()))
    ap = [list(map(int, sys.stdin.readline().split())) for _ in range(A)]

    # print(M, '-', A)
    # print(ma, '-', mb)
    # print(ap)


    # for kkk in range(10):
    #     for jjj in range(10):
    #         map[kkk][jjj] = [0,0,0,0]
    #     # map.append([[0]*10 for _ in range(10)])

    map = [[[0 for k in range(4)] for j in range(10)] for i in range(10)]
    # print(map)

    for k in range(0, A):
        # visit = [[0] * 10]*10
        # print(ap[i][0], ap[i][1], ap[i][2], ap[i][3], i)
        initx = ap[k][0]-1
        inity = ap[k][1]-1
        visit = [[0]*10 for _ in range(10)]
        
        batterydfs(ap[k][0]-1, ap[k][1]-1, ap[k][2], ap[k][3], k, visit)
        print(visit)

    for n in range(10):
        print(map[n])

    print(map[2][3][0])
    print('#{} {}'.format(test_case, answer))



