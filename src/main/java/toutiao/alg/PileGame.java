package toutiao.alg;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 石子堆博弈;
 */
@Slf4j
public class PileGame {

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
        int[][][] playerA = new int[sum + 1][0][piles.length];
        //playerB[m][i][j],表示当前player手中有m个石子,可选的石子堆范围是[i,j]时,当前player后取石子堆,游戏结束时最多可取到的石子总数;
        int[][][] playerB = new int[sum + 1][0][piles.length];


        j = piles.length - 1;
        return new int[]{playerA[0][0][j], playerB[0][0][j]};
    }

    public static void optimalSubstructure(int[] piles, int[][][] playerA, int[][][] playerB) {
        final int sum = playerA.length - 1;
        int k, i, j, dist = 0, maxDist = piles.length - 1;
        for (k = 0; k < sum; k++) {



            for (dist =1; dist <= maxDist; dist++) {
                for (i = 0, j = i + dist; j < piles.length; i++, j++) {

                }
            }
        }

    }


}
