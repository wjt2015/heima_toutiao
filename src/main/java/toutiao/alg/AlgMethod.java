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


}
