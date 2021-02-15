package toutiao.alg;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class PileGame2Test {

    @Test
    public void sumScores() {

        PileGame2 pileGame2;

        int[] piles = {1, 5, 10, 8, 9, 12, 13, 5, 7, 18};
        pileGame2=new PileGame2(piles);
        log.info("sumScore={};",pileGame2.sumScores());

        piles = new int[]{2, 3, 5, 8, 20, 7};
        pileGame2=new PileGame2(piles);
        log.info("sumScore={};",pileGame2.sumScores());


    }


}