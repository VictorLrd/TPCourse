

/**
 * Created by Victor on 08/11/2017.
 */
package com.example.victor.applicourse;

import android.support.v7.widget.RecyclerView;

        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.MyViewHolder> {

    private List<Article> articleList = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nom;
        public CheckBox check;
       // public Button supprimer;

        public MyViewHolder(View view) {
            super(view);
            nom = (TextView) view.findViewById(R.id.nom);
            check = (CheckBox) view.findViewById(R.id.check);
            //supprimer =(Button) view.findViewById(R.id.supprimer);
        }
    }

    public void init(List<Article> listArticle){
        articleList.clear();
        articleList.addAll(listArticle);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.nom.setText(article.getNom());
        holder.check.setChecked(true);
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }
}
