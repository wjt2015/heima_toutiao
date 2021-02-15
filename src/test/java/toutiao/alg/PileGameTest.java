package toutiao.alg;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class PileGameTest {

    @Test
    public void gameResult() {

        int[] piles = {1, 5, 10, 8, 9, 12, 13, 5, 7, 18};
        int[] gameResult = PileGame.gameResult(piles);
        log.info("gameResult={};", gameResult);

        piles = new int[]{2, 3, 5, 8, 20, 7};
        gameResult = PileGame.gameResult(piles);
        log.info("gameResult={};", gameResult);

    }
}