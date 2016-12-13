package br.com.renandias.bookshelf.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.renandias.bookshelf.R;
import br.com.renandias.bookshelf.models.Book;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Renan on 12/12/2016.
 */

/**
 * Classe que define um custom adapter com imagem e nome do livro para a lista de livros.
 */
public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Context context, List<Book> bookList) {
        super(context, R.layout.book_adapter, bookList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if(convertView == null) {
            convertView = View.inflate(getContext(), R.layout.book_adapter, null);

            holder = new ViewHolder();

            ButterKnife.bind(holder, convertView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        Book book = getItem(position);
        if(book != null) {
            holder.name.setText(book.getName());
            holder.image.setImageBitmap(book.getBitmapImage());
        }

        return convertView;
    }

    /**
     * Layout da Nó da lista (onde fica o nome e a imagem)
     */
    class ViewHolder {

        @Bind(R.id.book_image)
        ImageView image;

        @Bind(R.id.book_name_adapter)
        TextView name;

    }


}
