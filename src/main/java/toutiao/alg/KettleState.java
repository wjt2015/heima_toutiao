package toutiao.alg;

import java.util.HashSet;
import java.util.Set;

/**
 * 三只水壶的状态;
 */
public class KettleState {
    public int[] maxCapacity = new int[]{8, 5, 3};
    //public int[] curCapacity = new int[]{8, 0, 0};
    public int[] curCapacity;

    public KettleState(int[] curCapacity) {
        this.curCapacity = curCapacity;
    }

    /**
     * 可能达到的新状态;
     *
     * @return
     */
    public Set<KettleState> newStates() {

        final Set<KettleState> newStates = new HashSet<>();
        for (int i = 0; i < this.curCapacity.length; i++) {
            if (this.curCapacity[i] <= 0) {
                continue;
            }

            //从i号水壶分别向其他两个水壶倒水;
            for (int j = 0; j < this.curCapacity.length; j++) {
                if (i != j && this.curCapacity[j] < this.maxCapacity[j]) {
                    continue;
                }

                int translateCapacity = Math.min(this.curCapacity[i], (this.maxCapacity[j] - this.curCapacity[j]));
                final int[] newCapacity = new int[this.curCapacity.length];
                //倒水,构造新的状态;
                for (int k = 0; k < newCapacity.length; k++) {
                    if (k == i) {
                        newCapacity[k] = this.curCapacity[k] - translateCapacity;
                    } else if (k == j) {
                        newCapacity[k] = this.curCapacity[k] + translateCapacity;
                    } else {
                        newCapacity[k] = this.curCapacity[k];
                    }
                }
                newStates.add(new KettleState(newCapacity));
            }
        }
        return newStates;

    }


    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof KettleState)) {
            return false;
        }
        final KettleState otherState = (KettleState) other;
        if (this.maxCapacity.length != otherState.maxCapacity.length || this.curCapacity.length != otherState.curCapacity.length) {
            return false;
        }
        for (int i = 0; i < curCapacity.length; i++) {
            if (this.curCapacity[i] != otherState.curCapacity[i]) {
                return false;
            }
        }
        return true;
    }

}
