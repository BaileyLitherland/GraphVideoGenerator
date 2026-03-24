package com.engmig;

import org.bytedeco.javacv.FFmpegFrameRecorder;

import java.util.ArrayList;

public class AudioScheduler {

    private FFmpegFrameRecorder recorder;


    public AudioScheduler(FFmpegFrameRecorder recorder){
        this.recorder = recorder;
    }


}
