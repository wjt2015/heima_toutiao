package toutiao.alg;

import com.google.common.base.Joiner;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

@Slf4j
public class AlgMethod {

    public static void kettle() throws FileNotFoundException {

        final String fileName = "/Users/jintao9/linux2014/wjt_projs/heima_toutiao/docs/logs/kettle_solution.txt";
        final PrintWriter printWriter = new PrintWriter(fileName);

        //定义初始状态;
        final KettleState initState = new KettleState(new int[]{8, 0, 0});
        final Stack<KettleState> stateStack = new Stack<>();
        stateStack.push(initState);
        //每个状态转换到新的状态,直到无法推进或达到目标;
        searchKettleState(stateStack, o -> {
            if (o == null) {
                return null;
            }
            final Stack<KettleState> stack = (Stack<KettleState>) o;
            String result = Joiner.on("=>").join(stack.stream().map(s -> s.showState()).collect(Collectors.toList()));
            printWriter.println("solution:");
            printWriter.println(result);
            printWriter.println("------");
            return null;
        });

        printWriter.close();

    }


    public static void searchKettleState(final Stack<KettleState> stateStack, final SolutionTask solutionTask) {
        if (CollectionUtils.isEmpty(stateStack)) {
            return;
        }
        final KettleState nearState = stateStack.peek();
        if (found(nearState)) {
            //找到了;
            showStateList(stateStack);

            if (solutionTask != null) {
                solutionTask.doTask(stateStack);
            }

            return;
        }
        final Set<KettleState> newStates = nearState.newStates();

        log.info("nearState={};newStates={};", nearState.showState(), (newStates.stream().map(s -> s.showState()).collect(Collectors.toList())));
        for (KettleState newState : newStates) {
            if (!stateStack.contains(newState)) {
                stateStack.push(newState);
                searchKettleState(stateStack, solutionTask);
                stateStack.pop();
            }
        }
    }

    interface SolutionTask {
        Object doTask(final Object obj);
    }


    public static void showStateList(final Stack<KettleState> stateStack) {
        String join = Joiner.on("->").join(stateStack.stream().map(s -> s.showState()).collect(Collectors.toList()));
        System.out.println("solution:" + join);
    }


    /**
     * 判断是否达到目标状态;
     *
     * @param kettleState
     * @return
     */
    public static boolean found(final KettleState kettleState) {

        for (int c : kettleState.curCapacity) {
            if (c == 4) {
                return true;
            }
        }

        return false;
    }

    /**
     * 股票收益;
     * [
     * https://www.zhihu.com/search?type=content&q=%E4%B9%B0%E5%8D%96%E8%82%A1%E7%A5%A8%E7%AE%97%E6%B3%95
     * https://zhuanlan.zhihu.com/p/108941062
     * ]
     */
    public static void stockBenifit() {

    }

    /**
     * [i,j]买卖股票的最大收益;
     * @param n
     * @param priceArr
     * @return
     */
    public static int[][] benifits(final int n, final int[] priceArr) {
        final int[][] maxBenifits = new int[n][n];
        int i, j;
        for (int size = 1; size < n; size++) {
            if (size == 1) {
                for (i = 0, j = i + size; j < n; i++, j++) {
                    //当天买卖;隔天买卖;
                    maxBenifits[i][j] = (priceArr[j] > priceArr[i] ? (priceArr[j] - priceArr[i]) : 0);
                }
            } else {
                for (i = 0, j = i + size; j < n; i++, j++) {
                    maxBenifits[i][j] = priceArr[j] - priceArr[i];
                    if (maxBenifits[i][j] < maxBenifits[i][j - 1]) {
                        maxBenifits[i][j] = maxBenifits[i][j - 1];
                    }
                    if (maxBenifits[i][j] < maxBenifits[i + 1][j]) {
                        maxBenifits[i][j] = maxBenifits[i + 1][j];
                    }
                }
            }
        }
        return maxBenifits;
    }


}
