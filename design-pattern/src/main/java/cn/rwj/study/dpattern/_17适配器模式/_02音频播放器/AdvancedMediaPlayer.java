package cn.rwj.study.dpattern._17适配器模式._02音频播放器;

/**
 * @author rwj
 * @date 2023/3/29
 */
public interface AdvancedMediaPlayer {

    default void playVlc(String fileName) {

    }

    default void playMp4(String fileName) {

    }

}
