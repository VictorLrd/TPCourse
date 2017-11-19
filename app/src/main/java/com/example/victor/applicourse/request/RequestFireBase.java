package com.example.victor.applicourse.request;

import com.example.victor.applicourse.Article;
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

/**
 * Created by Victor on 08/11/2017.
 */

public class RequestFireBase {

    private FirebaseDatabase database;
    private DatabaseReference myRef;


    public RequestFireBase(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Article");
    }

    public void AjoutBaseDonné(String nomArticle,boolean bool){

        String key = myRef.child("posts").push().getKey();
        Article prod = new Article(nomArticle,bool);

        Map<String, Object> prodValues = prod.recuperer();
        Map<String, Object> childUpdates = new HashMap<>();

        childUpdates.put(key, prodValues);

        myRef.updateChildren(childUpdates);


    }


    public void recuperationBD(final RequeteService callback){
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator <HashMap<String,Article>> objectsGTypeInd = new GenericTypeIndicator<HashMap<String, Article>>(){};
                Map<String, Article> objetHashMap = dataSnapshot.getValue(objectsGTypeInd);
                ArrayList<Article> objectArrayList = new ArrayList<>();
                if(objetHashMap!=null){
                    objectArrayList.addAll(objetHashMap.values());
                    int i=0;
                    for(String key : objetHashMap.keySet()){
                        objectArrayList.get(i).setRef(key);
                        i++;
                    }
                }
                callback.onDataChange(objectArrayList);



                /*// Get Post object and use the values to update the UI
                String nom = dataSnapshot.child("nom").toString();
                System.out.println(nom);
                Article article = new Article(nom,true);
                listArticle.add(article);
                // ...*/
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                // ...
            }
        };

        myRef.addValueEventListener(postListener);
    }


}