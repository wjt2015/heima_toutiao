package toutiao.alg;

import lombok.extern.slf4j.Slf4j;

/**
 * 石子堆博弈游戏;
 */
@Slf4j
public class PileGame2 {
    private static final int NO_VAL = -1;

    public int[] piles;
    public int[][][] playerA;
    public int[][][] playerB;
    public int sum;


    public PileGame2(int[] piles) {
        this.piles = piles;
        init();
    }

    private void init() {
        int k, i, j;
        sum = 0;
        for (i = 0; i < piles.length; i++) {
            sum += piles[i];
        }

        playerA = new int[sum + 1][piles.length][piles.length];
        playerB = new int[sum + 1][piles.length][piles.length];
        for (k = 0; k < playerA.length; k++) {
            for (i = 0; i < playerA[k].length; i++) {
                for (j = 0; j < playerA[k][i].length; j++) {
                    playerA[k][i][j] = playerB[k][i][j] = NO_VAL;
                }
            }
        }

    }

    public int[] sumScores() {
        return new int[]{playerA(0, 0, piles.length - 1), playerB(0, 0, piles.length - 1)};
    }

    private int playerA(int k, int i, int j) {
        if (playerA[k][i][j] == NO_VAL) {
            if (i == j) {
                playerA[k][i][j] = k + piles[i];
            } else {
                //取出i;
                final int iResult = playerB(k + piles[i], i + 1, j);
                //取出j;
                final int jResult = playerB(k + piles[j], i, j - 1);

                playerA[k][i][j] = (iResult > jResult ? iResult : jResult);
            }
        }
        return playerA[k][i][j];
    }

    private int playerB(int k, int i, int j) {
        if (playerB[k][i][j] == NO_VAL) {
            if (i == j) {
                playerB[k][i][j] = k;
            } else {
                //等待playerA选取;
                final int r = usedCount(i, j) - k;
                //playerA[r][i][j] = playerA(r, i, j);
                playerB[r + piles[i]][i + 1][j] = playerB(r + piles[i], i + 1, j);
                playerB[r + piles[j]][i][j - 1] = playerB(r + piles[j], i, j - 1);

                //当前的playerA选择;
                playerA[k][i + 1][j] = playerA(k, i + 1, j);
                playerA[k][i][j - 1] = playerA(k, i, j - 1);
                //playerA选择后余下的供playerB再选择;
                playerB[k][i][j] = (playerB[r + piles[i]][i + 1][j] > playerB[r + piles[j]][i][j - 1] ? playerA[k][i + 1][j] : playerA[k][i][j - 1]);
            }
        }
        return playerB[k][i][j];
    }

    private int usedCount(int i, int j) {
        int usedCount = 0, k;
        for (k = 0; k < piles.length; k++) {
            if (k < i || k > j) {
                usedCount += piles[k];
            }
        }
        return usedCount;
    }


}
