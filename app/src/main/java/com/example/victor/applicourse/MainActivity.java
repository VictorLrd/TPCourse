package com.example.victor.applicourse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.victor.applicourse.request.RequestFireBase;
import com.example.victor.applicourse.request.RequeteService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,RequeteService {

    private List<Article> articleList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ArticleAdapter aAdapter;
    private Button boutonAjout;
    //rivate C
    private EditText eText;
    RequestFireBase requestFireBase=new RequestFireBase();
    ValueEventListener postListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            // Get Post object and use the values to update the UI
            Article post = dataSnapshot.getValue(Article.class);
            articleList.add(post);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            // ...
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eText = (EditText) findViewById(R.id.editText);

        // Write a message to the database


        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        boutonAjout = (Button) findViewById(R.id.button);
        boutonAjout.setOnClickListener(this);


        aAdapter = new ArticleAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(aAdapter);

        requestFireBase.recuperationBD(this);
    }

    public void ouvrirAjoutArticle() {
        String nomArticle=eText.getText().toString();
        requestFireBase.AjoutBaseDonné(nomArticle,true);
    }


        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button:
                    ouvrirAjoutArticle();
                    break;
            }
        }



    @Override
    public void onDataChange(List<Article> listArticles) {
        this.articleList=listArticles;
        aAdapter.init(listArticles);
    }
}


