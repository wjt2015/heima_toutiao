package toutiao.alg;

import org.apache.commons.collections.CollectionUtils;

import java.util.Set;
import java.util.Stack;

public class AlgMethod {

    public static void kettle() {
        //定义初始状态;
        final KettleState initState = new KettleState(new int[]{8, 0, 0});
        final Stack<KettleState> stateStack = new Stack<>();
        stateStack.push(initState);
        //每个状态转换到新的状态,直到无法推进或达到目标;
        while (true) {
            //从目前的最新状态开始搜索;
            final KettleState topState = stateStack.lastElement();
            final Set<KettleState> newStates = topState.newStates();
            for (KettleState newState : newStates) {
                if (!stateStack.contains(newState)) {
                    stateStack.push(newState);
                }
            }
        }
    }


    public static void searchKettleState(final Stack<KettleState> stateStack){
        if(CollectionUtils.isEmpty(stateStack)){
            return;
        }

    }


    /**
     * 判断是否达到目标状态;
     *
     * @param kettleState
     * @return
     */
    public static boolean found(final KettleState kettleState) {
        for (int i = 0; i < kettleState.curCapacity.length; i++) {
            if (kettleState.curCapacity[i] == 4) {
                return true;
            }
        }
        return false;
    }


}
