package toutiao.alg;

import lombok.extern.slf4j.Slf4j;

/**
 * kmp,模式匹配;
 */
@Slf4j
public class KMP {

    public static int matchIndex(final char[] s, final char[] p, final int[] next) {
        int i, j;
        for (i = j = 0; i < s.length && j < p.length; ) {
            if (s[i] == p[j]) {
                i++;
                j++;
            } else {
                j = next[j];
                if (j < 0) {
                    i++;
                    j++;
                }
            }
        }
        return (j >= p.length ? (i - j) : -1);
    }

    public static int[] next(final char[] pattern) {
        if (pattern == null) {
            return null;
        }
        int[] next = new int[pattern.length];
        int k = 0, j, s, t;
        boolean match = false;
        for (j = 0; j < pattern.length; j++) {
            //计算next[j];
            for (k = j - 1; k >= -1; k--) {
                //[0,k-1],[j-k,j-1];
                for (match = true, s = 0, t = j - k; s < k && t < j; s++, t++) {
                    if (pattern[s] != pattern[t]) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    break;
                }
            }
            next[j] = k;

        }

        return next;
    }

}
