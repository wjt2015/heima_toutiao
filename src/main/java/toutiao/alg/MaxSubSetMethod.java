package toutiao.alg;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 电路布线,最大不相交子集;
 * input:a[],a[i]表示连线i->a[i];i=1,2,...,n;
 * output:{1,2,...,n}的最大不相交子集;
 */
@Slf4j
public class MaxSubSetMethod {


    public static List<Integer> findMaxSubSet(final int[] a) {
        if (a == null || a.length < 1) {
            return null;
        }
        final int[][] last = new int[a.length][a.length];
        final int[][] max = new int[a.length][a.length];
        int i = 0, j = 0, prevI = 0;
        final int n = a.length - 1;
        //自底向上计算最优值;
        for (j = 1; j <= n; j++) {
            if (a[1] <= j) {
                max[1][j] = 1;
                last[1][j] = 1;
            } else {
                max[1][j] = 0;
                last[1][j] = 0;
            }
        }
        for (i = 2; i <= n; i++) {
            for (j = 1; j <= n; j++) {
                prevI = i - 1;
                int prevLast = last[prevI][j];
                if (((prevLast >= 1 && a[prevLast] < a[i]) || prevLast < 1) && a[i] <= j) {
                    //i符合添加条件,要添加i;
                    max[i][j] = max[prevI][j] + 1;
                    last[i][j] = i;
                } else {
                    max[i][j] = max[prevI][j];
                    last[i][j] = last[prevI][j];
                }
            }
        }
        //根据最优值计算最优解;
        final List<Integer> vList = new ArrayList<>();
        for (i = j = n; i >= 1 && j >= 1; ) {
            int lastV = last[i][j];
            if (lastV >= 1) {
                vList.add(lastV);
                i = lastV - 1;
                j = a[lastV] - 1;
            } else {
                break;
            }
        }
        return vList;

    }

}



