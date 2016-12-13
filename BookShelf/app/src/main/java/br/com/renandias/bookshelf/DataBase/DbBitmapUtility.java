package br.com.renandias.bookshelf.DataBase;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Created by Renan on 13/12/2016.
 */

/**
 * Classe utilitária para converter Bitmaps(Imagens) em Arrays de bytes e para converter
 * Arrays de bytes em Bitmaps(Imagens).
 */
public class DbBitmapUtility {

    /**
     * Recebe um Bitmap e converte em um Vetor de bytes.
     * @param bitmap bitmap
     * @return Vetor de bytes dp Bitmap.
     */
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    /**
     * Recebe um vetor de bytes de uma imagem e converte em Bitmap.
     * @param image image
     * @return Bitmap da imagem.
     */
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

}
