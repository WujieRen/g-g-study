package cn.rwj.study.dpattern._17适配器模式._02音频播放器;

/**
 * @author rwj
 * @date 2023/3/29
 */
public class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: " + fileName);
    }
}
