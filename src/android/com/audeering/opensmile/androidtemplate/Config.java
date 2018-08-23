package com.audeering.opensmile.androidtemplate;

import java.util.ArrayList;
import java.util.List;

public class Config {
    public String[] assets = {
            "liveinput_android.conf",
						"GenevaExtended_func.conf",
						"GenevaExtended_lld.conf",
						"raw_audio_bin.conf",
            "BufferModeRb.conf.inc",
            "features.conf.inc",
            "messages.conf.inc"
            //"BufferModeLive.conf.inc",
            //"music_features.conf.inc",
            //"FrameModeMoodWin.conf.inc"
    };

    public String mainConf = "liveinput_android.conf";
    List<OpenSmilePlugins> plugins = new ArrayList<>();

    public Config(ArrayList<OpenSmilePlugins> op) {
        for(int i=0; i < op.size(); i++)
            plugins.add(op.get(i));
    }
}
