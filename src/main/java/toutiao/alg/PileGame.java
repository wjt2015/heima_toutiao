package toutiao.alg;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 石子堆博弈;
 */
@Slf4j
public class PileGame {

    private static final int NO_VAL = -1;

    /**
     * piles[0 ...],每一堆石子的个数;
     *
     * @param piles
     * @return 当A, B都很聪明时, A, B两个玩家所得到的石子数;
     */
    public static int[] gameResult(int[] piles) {
        if (piles == null || piles.length < 1) {
            return null;
        }
        //石子总数
        int sum = 0, k, i, j;
        for (int count : piles) {
            sum += count;
        }

        //playerA[m][i][j],表示当前player手中有m个石子,可选的石子堆范围是[i,j]时,当前player先取石子堆,游戏结束时最多可取到的石子总数;
        int[][][] playerA = new int[sum + 1][piles.length][piles.length];
        //playerB[m][i][j],表示当前player手中有m个石子,可选的石子堆范围是[i,j]时,当前player后取石子堆,游戏结束时最多可取到的石子总数;
        int[][][] playerB = new int[sum + 1][piles.length][piles.length];

        j = piles.length - 1;

        initPlayer(playerA);
        initPlayer(playerB);
        playerA[0][0][j] = playerA(piles, playerA, playerB, 0, 0, j);
        playerB[0][0][j] = playerB(piles, playerA, playerB, 0, 0, j);

        //optimalSubstructure(sum, piles, playerA, playerB);

        return new int[]{playerA[0][0][j], playerB[0][0][j]};
    }

    public static int playerA(int[] piles, int[][][] playerA, int[][][] playerB, int k, int i, int j) {
        if (playerA[k][i][j] == NO_VAL) {
            if (i == j) {
                playerA[k][i][j] = k + piles[i];
            } else {
                final int iResult = playerB(piles, playerA, playerB, k + piles[i], i + 1, j);
                final int jResult = playerB(piles, playerA, playerB, k + piles[j], i, j - 1);
                playerA[k][i][j] = (iResult > jResult ? iResult : jResult);
            }
        }
        return playerA[k][i][j];
    }

    public static int playerB(int[] piles, int[][][] playerA, int[][][] playerB, int k, int i, int j) {
        if (playerB[k][i][j] == NO_VAL) {
            if (i == j) {
                playerB[k][i][j] = k;
            } else {
                final int usedCount = usedCount(piles, i, j);
                //playerA拿到的石子数;
                final int aCount = usedCount - k;

                //假设playerA取piles[i];
                final int iResult = playerB(piles, playerA, playerB, aCount + piles[i], i + 1, j);
                //假设playerB取piles[j];
                final int jResult = playerB(piles, playerA, playerB, aCount + piles[j], i, j - 1);

                playerA[aCount][i][j] = (iResult > jResult ? iResult : jResult);

                playerA[k][i + 1][j] = playerA(piles, playerA, playerB, k, i + 1, j);
                playerA[k][i][j - 1] = playerA(piles, playerA, playerB, k, i, j - 1);

                playerB[k][i][j] = ((iResult > jResult) ? playerA[k][i + 1][j] : playerA[k][i][j - 1]);

            }
        }
        return playerB[k][i][j];
    }

    public static void initPlayer(int[][][] player) {
        for (int k = 0; k < player.length; k++) {
            for (int i = 0; i < player[k].length; i++) {
                for (int j = 0; j < player[k][i].length; j++) {
                    player[k][i][j] = NO_VAL;
                }
            }
        }
    }

    /**
     * [i,j]之外的石子堆内的石子总数;
     *
     * @param piles
     * @param i
     * @param j
     * @return
     */
    public static int usedCount(final int[] piles, final int i, final int j) {
        int usedCount = 0;
        for (int k = 0; k < piles.length; k++) {
            if (k < i || k > j) {
                usedCount += piles[k];
            }
        }
        return usedCount;
    }


    public static void optimalSubstructure(final int sum, int[] piles, int[][][] playerA, int[][][] playerB) {
        final int maxDist = piles.length - 1;
        int k, i, j, dist = 0, s = 0, t = 0, r = 0, usedCount = 0;

        for (i = 0; i < piles.length; i++) {
            for (k = sum; k >= 0; k--) {
                playerA[k][i][i] = k + piles[i];
                playerB[k][i][i] = k;
            }
        }

        for (dist = 1; dist <= maxDist; dist++) {
            for (i = 0, j = i + dist; j < piles.length; i++, j++) {
                usedCount = usedCount(piles, i, j);
                for (k = usedCount; k >= 0; k--) {

                    s = k + piles[i];
                    t = k + piles[j];

                    int iResult = (s <= sum ? playerB[s][i + 1][j] : -1);
                    int jResult = (t <= sum ? playerB[t][i][j - 1] : -1);
                    playerA[k][i][j] = (iResult > jResult ? iResult : jResult);

                    s = usedCount - k + piles[i];
                    t = usedCount - k + piles[j];

                    iResult = (s <= sum ? playerB[s][i + 1][j] : -1);
                    jResult = (t <= sum ? playerB[t][i][j - 1] : -1);
                    playerB[k][i][j] = (iResult > jResult ? playerA[k][i][j - 1] : playerB[k][i + 1][j]);
                }
            }
        }
    }
}
