package net.gbksoft.chatlibrary.utils;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * Provides useful methods for file uploading
 */

public class FileUploadUtils {

    /**
     * Create and return byte array of file by it`s Uri
     *
     * @param contentResolver needs for open input stream and read the file
     * @param fileUri uri of the file
     */
    public static byte[] getByteArrayFromFileUri(ContentResolver contentResolver, Uri fileUri) {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        try {
            InputStream inputStream = contentResolver.openInputStream(fileUri);
            if (inputStream == null) {
                return new byte[0];
            }

            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];


            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                byteBuffer.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }

        return byteBuffer.toByteArray();
    }

    /**
     * Create and return byte array from image Bitmap
     *
     * @param bitmap of image file
     */
    public static byte[] getByteArrayFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream blob = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 /* Ignored for PNGs */, blob);
        return blob.toByteArray();
    }

    /**
     * Start recording
     * return MediaRecorder for stop/pause/resume methods
     *
     * @param filePathName name and path of the file for start record
     */
    public static MediaRecorder startRecording(String filePathName) {
        return startRecording(null, null, filePathName);
    }

    /**
     * Start recording
     * return MediaRecorder for stop/pause/resume methods
     *
     * @param onErrorListener Interface definition for a callback to be invoked when an error
     *      occurs while recording.
     * @param onInfoListener Interface definition for a callback to be invoked when an error
     *      occurs while recording.
     * @param filePathName name and path of the file for start record
     */
    public static MediaRecorder startRecording(
            MediaRecorder.OnErrorListener onErrorListener,
            MediaRecorder.OnInfoListener onInfoListener,
            String filePathName) {

        MediaRecorder recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile(filePathName);

        if (onErrorListener != null) {
            recorder.setOnErrorListener(onErrorListener);
        }

        if (onInfoListener != null) {
            recorder.setOnInfoListener(onInfoListener);
        }

        try {
            recorder.prepare();
            recorder.start();
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }

        return recorder;
    }

    /**
     * Generate file ane and path for recording
     *
     */
    public static String getNewFileRecordingName() {
        return getNewFileRecordingName("mp4");
    }

    /**
     * Generate file ane and path for recording
     *
     * @param extension extension of the file without dot
     */
    public static String getNewFileRecordingName(String extension) {
        String filepath = Environment.getExternalStorageDirectory().getPath();
        File file = new File(filepath/*,AUDIO_RECORDER_FOLDER*/);

        return (file.getAbsolutePath() + "/" + System.currentTimeMillis() + "." + extension);
    }

    /**
     * Stop recording
     *
     * @param recorder that require to stop
     */
    public static void stopRecording(MediaRecorder recorder) {
        if (recorder != null) {
            recorder.stop();
            recorder.reset();
            recorder.release();
        }
    }

    /**
     * Returns Bitmap of image from byte array
     *
     * @param array of image file
     */
    public static Bitmap getBitmapFromByteArray(byte[] array) {
        return BitmapFactory.decodeByteArray(array, 0, array.length);
    }
}