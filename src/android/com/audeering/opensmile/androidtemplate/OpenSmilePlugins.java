package com.audeering.opensmile.androidtemplate;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import static android.R.attr.max;

public class OpenSmilePlugins implements SmileJNI.Listener {
    public static int i = 1;
    public static String features;
    public static String fileName;
    public static OutputStreamWriter outputStreamWriter;
    public File file;
    public String path;
    File dir;
    private static Activity act;

    public OpenSmilePlugins(Activity act, String name, String filePath) throws IOException {
        this.act = act;
        SmileJNI.registerListener(this);
        dir = new File (Environment.getExternalStorageDirectory().getAbsolutePath() + filePath);
        fileName = name;
        dir.mkdirs();
        file = new File(dir, fileName);
        file.createNewFile();
        FileOutputStream fOut = new FileOutputStream(file);
        outputStreamWriter = new OutputStreamWriter(fOut);
    }

    @Override
    public void onSmileMessageReceived(String text)  {
        final String t = text;
        act.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jo = new JSONObject(t);
                    double treb = jo.getJSONObject("floatData").getDouble("0");
                    double mid = jo.getJSONObject("floatData").getDouble("1");
                    double bas = jo.getJSONObject("floatData").getDouble("2");
                    features = treb + "," + mid + "," + bas + "\n";
                    writeToFile(features);
                    Log.d("SmileLog:",features);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void writeToFile(String data) {
        try {
            outputStreamWriter.append(data);
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public void closeFile() throws IOException {
        outputStreamWriter.close();
    }
}
