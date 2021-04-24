# -*- coding: utf-8 -*- 

T = int(input())
#상하좌우
dx = [0,0,-1,1]
dy = [1,-1,0,0]

maps = [[0 for _  in range(4001)] for _  in range(4001)]
for test_case in range(1, T + 1):
    answer = 0
   
   # 0.5초 기준 
   # 원자 개수
    N = int(input())
    atom = []

    #원자 x,y 위치, 방향, 방출에너지 
    for i in range(0,N):
        x, y, dirs, energy = map(int, input().split())
        atom.append([x*2 + 2000, y*2 + 2000, dirs, energy])


    # 원자들 이동하고 방출 + 좌표 -2000~2000 넘어간 애들 총 합 N 개 되면 종료 
    count = 0
    ttime = 0

    while count < N:
        ttime += 1
        # 1. 각 원자들 이동 
        # 2. -4000 ,4000 넘어간 애들 count 증가, 좌표 표시 지우기  // 지도 표시하기 
        # 2. 지도에서 겹친 애들 소멸시키기, count 증가, answer 증가 
        nc = []
        nc2 = []
        for i in range(N):

            if atom[i][0] != -1 :
                # 소멸 혹은 지도 나간애들인지 
                x = atom[i][0]
                nx = x + dx[atom[i][2]]
                atom[i][0] = nx
 
                y = atom[i][1]
                ny = y + dy[atom[i][2]]
                atom[i][1] = ny

                nc.append([x,y])
                
                maps[x][y] = 0
       
                # 좌표 나가버림 
                if nx > 4000 or nx < 0 or ny > 4000 or ny < 0 :
                    count += 1
                    atom[i][0] = -1
                    # print('count - ',count)
                else :
                    #지도 표시하기 
                    temp = maps[nx][ny]
                    maps[nx][ny] = temp + 1
                    # if maps[nx][ny] == 2 and test_case==2 :
                    #     print('이쌔끼', ttime, atom[i])
      
                    nc2.append([nx,ny])
            


        #지도 돌고 겹친애들 소멸, count 증가, answer 증가
        #print(atom)

        for c in range(N):
            i = atom[c][0]
            j = atom[c][1]
            if atom[c][0] != -1:
                if maps[i][j] > 1 :
                    count += 1
                    answer += atom[c][3]
                    # print(ttime,'anwer 여기',answer, maps[i][j])
                    # print(atom)
                    atom[c][0] = -1
                   

        for d in range(N):
            i = atom[d][0]
            j = atom[d][1]
            if atom[d][0] != -1:
                maps[i][j] = 0

        for p in nc:
            maps[p[0]][p[1]] = 0
        for pp in nc2:
            maps[pp[0]][pp[1]] = 0
  
  

        if ttime > 4000:
            break

    print('#{} {}'.format(test_case, answer))
    answer = 0