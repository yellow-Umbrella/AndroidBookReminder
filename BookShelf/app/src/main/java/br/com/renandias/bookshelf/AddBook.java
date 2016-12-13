package br.com.renandias.bookshelf;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import br.com.renandias.bookshelf.DataBase.DataBase;
import br.com.renandias.bookshelf.models.Book;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddBook extends AppCompatActivity {

    @Bind(R.id.book_name)
    EditText bookName;
    @Bind(R.id.pages)
    EditText bookPages;
    @Bind(R.id.book_image_view)
    ImageView imageView;
    @Bind(R.id.take_pic)
    Button takePic;

    static final int REQUEST_IMAGE_CAMERA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        ButterKnife.bind(this);

        if(!hasCamera()) {
            imageView.setEnabled(false);
            takePic.setEnabled(false);
        }
    }

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

        } else
            Toast.makeText(this, "Missing data", Toast.LENGTH_SHORT).show();

    }

    //Camera
    @OnClick(R.id.take_pic)
    public void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(intent, REQUEST_IMAGE_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAMERA && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
            imageView.setImageBitmap(photo);
        }
    }

    public boolean hasCamera() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }
}
