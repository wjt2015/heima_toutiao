package toutiao.game;

import javafx.scene.media.AudioClip;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import toutiao.common.AuxUtils;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

@Slf4j
public class MediaUtilsTest {

    @Test
    public void audioClip() throws URISyntaxException {

        //AudioClip bonk = new AudioClip(this.getClass().getClassLoader().getResource("sounds/bonk.wav"));

        String resource="audio/zhangrunzhen_hua.mp3";

        URI uri = this.getClass().getClassLoader().getResource(resource).toURI();
        AudioClip audioClip=new AudioClip(uri.toString());

        audioClip.setCycleCount(2);
        log.info("audioClip start to play!");
        audioClip.play();

        while (audioClip.isPlaying()){
            AuxUtils.sleep(50000);
        }

        log.info("audio.play !");


    }

}