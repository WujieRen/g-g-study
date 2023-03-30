package cn.rwj.study.dpattern._17适配器模式._02音频播放器;

/**
 * @author rwj
 * @date 2023/3/29
 */
public class VlcPlayer implements AdvancedMediaPlayer{
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: "+ fileName);
    }

}
