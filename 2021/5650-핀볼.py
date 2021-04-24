# -*- coding: utf-8 -*- 
import sys

T = int(input())
# 이동
dx = [0,1,0,-1]
dy = [1,0,-1,0]

# fMAX = 0
N = 0
mp = []
start = []
worm = [0,0,0,0,0,0,[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]
fMAX = 0

# 우하좌상
# 1 블록 2->3, 1->0
# 2 블록 2->1, 3->0
# 3 블록 0->1, 3->2
# 4 블록 0->3, 1->2
# 5 블록 0<->2, 1<->3 180도 방향 변경
# 방향 변환 함수 
def direction(input, block):
    if block == 1:
        if input==0 : return 2
        if input==1 : return 0
        if input==2 : return 3
        if input==3 : return 1
    elif block == 2:
        if input==0 : return 2
        if input==1 : return 3
        if input==2 : return 1
        if input==3 : return 0
    elif block == 3:
        if input==0 : return 1
        if input==1 : return 3
        if input==2 : return 0
        if input==3 : return 2
    elif block == 4:
        if input==0 : return 3
        if input==1 : return 2
        if input==2 : return 0
        if input==3 : return 1
    elif block == 5:
        if input==0 : return 2
        if input==2 : return 0
        if input==1 : return 3
        if input==3 : return 1



# 최댓값 함수 
def gomax(pos, dir, size, point):
    np = [ int(pos[0]+ dx[dir]), int(pos[1] + dy[dir])]
    #벽에 부딪힘
    if np[0] == -1 or np[1] == -1 or np[0] == (size) or np[1] == (size):
        # print('wall', point, '-', pos,'-', dir, ' np', np, ' size', size)
        
        
        # 웜홀체크 
        if mp[pos[0]][pos[1]] > 5 and mp[pos[0]][pos[1]] < 11 :
            # print(worm[mp[pos[0]][pos[1]]][2],'-', pos[0] + dx[direction(dir,5)], pos[1] + dy[direction(dir,5)])
            print('move wormhole!!!!!! - ','dir-', dir, '-', mp[pos[0]][pos[1]], '-', np, '-worm', worm[mp[pos[0]][pos[1]]])
            if(pos == worm[mp[pos[0]][pos[1]]][1]):
                if worm[mp[pos[0]][pos[1]]][2] == [pos[0] + dx[direction(dir,5)], pos[1] + dy[direction(dir,5)]] :
                    print('move wormhole!!!!!! - ')
                    return

                gomax(worm[mp[pos[0]][pos[1]]][2], dir, size, point)
            if(pos == worm[mp[pos[0]][pos[1]]][2]):
                if worm[mp[pos[0]][pos[1]]][1] == [pos[0] + dx[direction(dir,5)], pos[1] + dy[direction(dir,5)]] :
                    print('move wormhole!!!!!! - ')
                    return

                gomax(worm[mp[pos[0]][pos[1]]][1], dir, size, point)

        else :
            point = point + 1
            gomax(np, direction(dir,5), size, point)



    else :
            
        nppo = mp[np[0]][np[1]]
        # print(pos,'-', dir, ' np', np, ' nppo', nppo ,' size', size)

        #종료조건 / 블랙홀 이거나 시작지점 
        if (nppo == -1 or np == start ):
            global fMAX
            if fMAX < point:
                fMAX = point
            # print('exit', point, '-max-', fMAX)
            return 

        else :
            #웜홀, 블록/벽
            #부딪히면 방향 전환 및 점수 증가
            #fMAX = 0
    
            if nppo == 0 :
                #안부딪히면 걍 증가 
                # print('no crash', point, ' index ----', np)
                gomax(np, dir, size, point)
            
            #블록
            if nppo <6  :
                if nppo > 0 :
                    # print('block-worm', point)
                    point = point + 1
                    gomax(np, direction(dir,nppo), size, point)

            if nppo > 5 and nppo < 11 :
                print('dir-', dir, '-', nppo, '-', np, '-worm', worm[nppo])
                if(np == worm[nppo][1]):
                    gomax(worm[nppo][2], dir, size, point)
                if(np == worm[nppo][2]):
                    gomax(worm[nppo][1], dir, size, point)





    



for test_case in range(1, 1+T):
    answer = 0
    # 5 <= N <= 100
    N = int(input())  
    # mp =  [list(map(int, input().split())) for _ in range(N)]
    mp=[list(map(int, sys.stdin.readline().split())) for _ in range(N)]
    worm = [0,0,0,0,0,0,[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]
    for i in range(0,N):
        for j in range(0, N):
            if (mp[i][j] <11 and mp[i][j]>5):
                
                # print(mp[i][j], '-worm', i, '-', j)
                
                if worm[mp[i][j]][0] == 1:
                    worm[mp[i][j]][0] = 2
                    worm[mp[i][j]][2] = [i,j]
                if worm[mp[i][j]][0] == 0:
                    worm[mp[i][j]][0] = 1
                    worm[mp[i][j]][1] = [i,j]


    #다 돌아다니기, 모든 방향 , 지도에서 0 인곳,  각 장소에서 4방향으로 
    for i in range(0,N):
        for j in range(0, N):
            if mp[i][j] == 0:
                for k in range(0,4):
                    fMAX = 0
                    start = [i,j]
                    if i==1 and j == 0 and k==0:
                        print('-')
                    gomax([i,j], k, N, 0)

                    #최댓값
                    if answer < fMAX:
                        answer = fMAX


    
    print('#{} {}'.format(test_case, answer))







