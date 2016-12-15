package br.com.renandias.bookshelf.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.SyncStateContract;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;

import br.com.renandias.bookshelf.DataBase.DataBase;
import br.com.renandias.bookshelf.R;
import br.com.renandias.bookshelf.models.Book;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Activity que gerencia a tela de adicionar livros.
 */
public class AddBook extends AppCompatActivity {

    //Binding dos Views e Buttons da tela.
    @Bind(R.id.book_name)
    EditText bookName;
    @Bind(R.id.pages)
    EditText bookPages;
    @Bind(R.id.book_image_view)
    ImageView imageView;
    @Bind(R.id.take_pic)
    Button takePic;
    @Bind(R.id.add_pic_gallery)
    Button gallery;

    static final int REQUEST_IMAGE_CAMERA = 0;
    static final int REQUEST_IMAGE_GALLEY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        ButterKnife.bind(this);

        if(!hasCamera()) {
            imageView.setEnabled(false);
            takePic.setEnabled(false);
        }

        if(!(Build.VERSION.SDK_INT >= 23)) {
            imageView.setEnabled(false);
            takePic.setEnabled(false);
            gallery.setEnabled(false);
        }
    }

    /**
     * Método que ativa quando o botão save_button é clicado.
     *Método salva livro do usuario no Banco de Dados.
     */
    @OnClick(R.id.save_button)
    public void saveBook() {
        String name = bookName.getText().toString();
        Integer pages;
        imageView.buildDrawingCache();
        Bitmap photo = imageView.getDrawingCache();

        try {
            pages = Integer.valueOf(bookPages.getText().toString());
        } catch(NumberFormatException exception) {
            pages = null;
        }

        if(!name.isEmpty() && pages != null) {
            DataBase db = new DataBase(this);
            db.saveBook(new Book(name, pages, photo));

            Toast.makeText(this, "Book Saved", Toast.LENGTH_SHORT).show();

            //goMyBooks
            Intent intent = new Intent(this, MyBooks.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();

        }
        else
            Toast.makeText(this, "Missing data", Toast.LENGTH_SHORT).show();

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.add_pic_gallery)
    public void goGallery() {

        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, REQUEST_IMAGE_GALLEY);
        }
        else
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
    }

    //Camera
    /**
     * Abre a camera para tirar uma foto do livro.
     */
    @OnClick(R.id.take_pic)
    public void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(intent, REQUEST_IMAGE_CAMERA);
    }

    /**
     * Pega a foto tirada e coloca na imageView ou imagem da galeria.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_IMAGE_CAMERA && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
            imageView.setImageBitmap(photo);
        }

        if(requestCode == REQUEST_IMAGE_GALLEY && resultCode == RESULT_OK) {
            Uri targetUri = data.getData();
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                imageView.setImageBitmap(bitmap);
            }catch(FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Checa se o celular possui uma camera.
     * @return
     */
    public boolean hasCamera() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }
}
