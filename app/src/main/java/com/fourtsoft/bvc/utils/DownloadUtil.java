package com.fourtsoft.bvc.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadUtil {

    /**
     * @param context
     * @param urlVideo
     * @param savePath
     * @param progressBar
     */
    public static void downloadVideo(Context context, String urlVideo, String savePath, ProgressBar progressBar) {
        final DownloadVideo downloadTask = new DownloadVideo(context, progressBar, savePath);
        downloadTask.execute(urlVideo);
    }

    private static class DownloadVideo extends AsyncTask<String, Integer, String> {
        private final Context context;
        private PowerManager.WakeLock mWakeLock;
        private ProgressBar progressBar;
        private String filePath;
        // todo next version will be implement it
//        private int seconds = 0;
//        private Handler handler = new Handler();

        public DownloadVideo(Context context, ProgressBar progressbar, String filePath) {
             this.context = context;
            this.filePath = filePath;
            this.progressBar = progressbar;

            progressBar.setIndeterminate(false);
            progressBar.setMax(100);
        }

        @Override
        protected String doInBackground(String... sUrl) {
            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            try {
                URL url = new URL(sUrl[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                // expect HTTP 200 OK, so we don't mistakenly save error report
                // instead of the file
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return "Server returned HTTP " + connection.getResponseCode() + " " + connection.getResponseMessage();
                }

                // this will be useful to display download percentage
                // might be -1: server did not report the length
                int fileLength = connection.getContentLength();

                // download the file
                input = connection.getInputStream();
                output = new FileOutputStream(filePath);

                byte data[] = new byte[4096];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    // allow canceling with back button
                    if (isCancelled()) {
                        input.close();
                        return null;
                    }
                    total += count;
                    // publishing the progress....
                    if (fileLength > 0) {// only if total length is known
                        publishProgress((int) (total * 100 / fileLength));
                    }
                    output.write(data, 0, count);
                }
                // todo next version will be implement it
//                while (seconds != 180){
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            seconds++;
//                        }
//                    }, 1000);
//                }
//
//                if( seconds == 180 ){
//                    ViewUtils.showDialogError((AppCompatActivity) context, 1);
//                    this.cancel(true);
//                }
//

            } catch (Exception e) {
                return e.toString();
            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                }

                if (connection != null)
                    connection.disconnect();
            }
            return "Success";
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // take CPU lock to prevent CPU from going off if the user
            // presses the power button during download
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
//            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, getClass().getName());
//            mWakeLock.acquire();
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            // if we get here, length is known, now set indeterminate to false
            progressBar.setProgress(progress[0]);
        }

        @Override
        protected void onPostExecute(String result) {
//            mWakeLock.release();

            if (result.equalsIgnoreCase("Success")) {
                Toast.makeText(context, "Succes", Toast.LENGTH_LONG).show();
            } else {
//                ViewUtils.showDialogError((AppCompatActivity) context, 1);
                Log.e("Error", "ss");
            }
        }
    }
}
