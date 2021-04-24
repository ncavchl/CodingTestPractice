# -*- coding: utf-8 -*- 

T = int(input())
#상하좌우
dx = [0,-1,1,0,0]
dy = [0,0,0,-1,1]
def diroppsite(x):
    if x == 1:
        x = 2
        return x
    if x == 2:
        x = 1
        return x 
    if x == 3:
        x = 4
        return x 
    if x == 4:
        x = 3
        return x 


for test_case in range(1, T + 1):
    answer = 0
    # N 한변 셀의 개수 
    # M 엠 시간후 남아있는 
    # K 미생물 수 

    N, M, K = map(int, input().split())
    micros = []
    # maps = Array[N][N]
    for i in range(K):
        x, y, number, dirs = map(int, input().split())
        micros.append([x, y, number, dirs])

    for time in range(M):
        #시간마다 초기화 
        # for pp in range(N):
        #     for zz in range(N):
        #         maps[N][N] = [0]
        maps = [[[0,0,0,0,0] for _  in range(N)] for _  in range(N) ]
        #미생물 이동 
        for mi in range(K):
            if micros[mi][0] != 0 or micros[mi][1] !=0:
                # print(mi, '변경전', micros[mi])
                x = micros[mi][0]
                nx =  x + dx[micros[mi][3]]
                micros[mi][0] = nx

                y = micros[mi][1]
                ny = y + dy[micros[mi][3]]
                micros[mi][1] = ny
                
                # print("nx ny N", nx, ny ,N, mi, micros[mi][0])
                #이동 좌표 기록 
                num = maps[nx][ny][0] + 1
                maps[nx][ny][0] = num
                maps[nx][ny][num] = mi

            #벽면부터 감지 
            if nx == 0 or nx == N-1 or ny == 0 or ny == N-1:
                #군집 절반 / 방향 반대 
                micornum = micros[mi][2]
                micros[mi][2] = int(micornum/2)
                micros[mi][3] = diroppsite(micros[mi][3])
            # print(mi, '변경후', micros[mi])
        
        # for ii in range(N):
                # print(maps[ii])
        # print('---------------------------------', time+1)

        #맵 이동하면서 합쳐진거 계산 및 미생물 변경 
        for i in range(N):
            for j in range(N):
                if maps[i][j][0] > 1 :
                    newmi = []
                    summicro = 0
                    for kk in range(1, maps[i][j][0]+1):
                        newmi.append(micros[maps[i][j][kk]][2])
                    summicro = sum(newmi)
                    maxindex = newmi.index(max(newmi))
                    maxmicro = maps[i][j][maxindex+1]

                    for kk in range(1, maps[i][j][0]+1):
                        #최대 미생물은 더하기 
                        #아닌 미생물은 0 만들기 
                        if kk == maxindex+1:
                            micros[maxmicro][2] = summicro
                        else:
                            micros[maps[i][j][kk]][0]=0
                            micros[maps[i][j][kk]][1]=0
                            micros[maps[i][j][kk]][2]=0
                            micros[maps[i][j][kk]][3]=0
        # print(micros)

    #각자 이동부터함, 맵에 남기기(있는 미생물 개수와 번호)
    #맵 돌면서 미생물수 변경, 방향 변경 저장 + 모여있거나 겹쳐진 미생물 수(최대 4) 계산 
    #다시 시간 이동 

    
    #남은 총 미생물 수 
    for i in range(K):
        answer += micros[i][2]
    
    print('#{} {}'.format(test_case, answer))