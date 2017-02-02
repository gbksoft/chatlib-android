package net.gbksoft.chatlibrary.request;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import net.gbksoft.chatlibrary.ChatWebSocket;
import net.gbksoft.chatlibrary.model.output.StoreFile;
import net.gbksoft.chatlibrary.utils.FileUploadUtils;

import java.io.File;

/**
 *
 * Remove Upload files request logic from ChatWebSocket class.
 */

public class ChatFileRequest {
    private static final String PNG_MIME_TYPE = "image/png";
    private static final String MP4_VIDEO_MIME_TYPE = "video/mp4";
    private static final String MP4_AUDIO_MIME_TYPE = "audio/mp4";

    private ChatWebSocket.IChatWebSocketRequestListener mRequestListener;

    public ChatFileRequest(ChatWebSocket.IChatWebSocketRequestListener requestListener) {
        if (requestListener == null) {
            throw new IllegalArgumentException("IChatWebSocketRequestListener cannot ne null");
        }
        mRequestListener = requestListener;
    }

    /**
     * Upload image file to the server
     *
     * @param bitmap bitmap of the image
     * @param messageType type of file visibility {'user' | 'group' | 'broadcast'}
     * @param messageTypeId id of the {'user' | 'group' | 'broadcast'}
     */
    public void uploadImageFile(Bitmap bitmap, String messageType, String messageTypeId) {
        uploadImageFile(bitmap, "", messageType, messageTypeId);
    }

    /**
     * Upload image file to the server with a new filename
     *
     * @param bitmap bitmap of the image
     * @param newFileName set file name
     * @param messageType type of file visibility {'user' | 'group' | 'broadcast'}
     * @param messageTypeId id of the {'user' | 'group' | 'broadcast'}
     */
    public void uploadImageFile(Bitmap bitmap, String newFileName, String messageType,
            String messageTypeId) {

        uploadImageFile(bitmap, newFileName, PNG_MIME_TYPE, messageType, messageTypeId);
    }

    /**
     * Private method for upload image file to the server
     * Contains main logic
     *
     * @param bitmap bitmap of the image
     * @param newFileName set file name
     * @param mimeType mime type of the image
     * @param messageType type of file visibility {'user' | 'group' | 'broadcast'}
     * @param messageTypeId id of the {'user' | 'group' | 'broadcast'}
     */
    public void uploadImageFile(Bitmap bitmap, String newFileName, String mimeType,
                String messageType, String messageTypeId) {

        new Thread(() -> {
                byte[] array = FileUploadUtils.getByteArrayFromBitmap(bitmap);
                StoreFile storeFile = new StoreFile(newFileName, mimeType, array.length,
                        messageType, messageTypeId);

                mRequestListener.createRequest(ChatWebSocket.STORE_FILE, storeFile.toJSONObject(),
                        array);
        }).start();
    }

    /**
     * Upload video file to the server
     * Contains main logic
     *
     * @param context context required for ContentResolver
     * @param fileUri uri of the file
     * @param messageType type of file visibility {'user' | 'group' | 'broadcast'}
     * @param messageTypeId id of the {'user' | 'group' | 'broadcast'}
     */
    public void uploadVideoFile(Context context,Uri fileUri, String messageType,
            String messageTypeId) {

        uploadVideoFile(context, fileUri, "", messageType, messageTypeId);
    }

    /**
     * Upload video file to the server
     * Contains main logic
     *
     * @param context context required for ContentResolver
     * @param fileUri uri of the file
     * @param newFileName set file name
     * @param messageType type of file visibility {'user' | 'group' | 'broadcast'}
     * @param messageTypeId id of the {'user' | 'group' | 'broadcast'}
     */
    public void uploadVideoFile(Context context, Uri fileUri, String newFileName,
            String messageType, String messageTypeId) {

        uploadVideoFile(context, fileUri, MP4_VIDEO_MIME_TYPE, newFileName, messageType,
                messageTypeId);
    }

    /**
     * Upload video file to the server
     * Contains main logic
     *
     * @param context context required for ContentResolver
     * @param fileUri uri of the file
     * @param newFileName set file name
     * @param mimeType mimeType of the file
     * @param messageType type of file visibility {'user' | 'group' | 'broadcast'}
     * @param messageTypeId id of the {'user' | 'group' | 'broadcast'}
     */
    public void uploadVideoFile(Context context, Uri fileUri, String newFileName, String mimeType,
            String messageType, String messageTypeId) {

        new Thread(() -> {
            byte[] videoByteArray = FileUploadUtils.getByteArrayFromFileUri(
                    context.getContentResolver(), fileUri);
            StoreFile storeFile = new StoreFile(newFileName, mimeType, videoByteArray.length,
                    messageType, messageTypeId);

            mRequestListener.createRequest(ChatWebSocket.STORE_FILE, storeFile.toJSONObject(),
                    videoByteArray);
        }).start();
    }

    /**
     * Upload audio file to the server
     * Contains main logic
     *
     * @param context context required for ContentResolver
     * @param filePathName audio file path and name
     * @param messageType type of file visibility {'user' | 'group' | 'broadcast'}
     * @param messageTypeId id of the {'user' | 'group' | 'broadcast'}
     */
    public void uploadAudioFile(Context context, String filePathName, String messageType,
            String messageTypeId) {

        uploadAudioFile(context, filePathName, "", messageType, messageTypeId);
    }

    /**
     * Upload audio file to the server
     * Contains main logic
     *
     * @param context context required for ContentResolver
     * @param filePathName audio file path and name
     * @param newFileName set file name
     * @param messageType type of file visibility {'user' | 'group' | 'broadcast'}
     * @param messageTypeId id of the {'user' | 'group' | 'broadcast'}
     */
    public void uploadAudioFile(Context context, String filePathName, String newFileName,
            String messageType, String messageTypeId) {

        uploadAudioFile(context, filePathName, newFileName, MP4_AUDIO_MIME_TYPE, messageType, messageTypeId);
    }

    /**
     * Upload audio file to the server
     * Contains main logic
     *
     * @param context context required for ContentResolver
     * @param filePathName audio file path and name
     * @param newFileName set file name
     * @param mimeType mimeType of the file
     * @param messageType type of file visibility {'user' | 'group' | 'broadcast'}
     * @param messageTypeId id of the {'user' | 'group' | 'broadcast'}
     */
    public void uploadAudioFile(Context context, String filePathName, String newFileName,
            String mimeType, String messageType, String messageTypeId) {

        new Thread(() -> {
            byte[] audioByteArray = FileUploadUtils.getByteArrayFromFileUri(
                    context.getContentResolver(), Uri.fromFile(new File(filePathName)));

            StoreFile storeFile = new StoreFile(newFileName, mimeType,
                    audioByteArray.length, messageType, messageTypeId);

            mRequestListener.createRequest(ChatWebSocket.STORE_FILE, storeFile.toJSONObject(), audioByteArray);
        }).start();
    }

    /**
     * Remove File from server
     *
     * @param url file url
     */
    public void removeFile(String url) {
        mRequestListener.createRequest(ChatWebSocket.REMOVE_FILE, url);
    }
}