package com.example.victor.applicourse;

import com.google.firebase.database.DataSnapshot;

import java.util.HashMap;

/**
 * Created by Victor on 08/11/2017.
 */

public class Article {

    private String nom;
    private boolean bool;
    private String ref;
   // private DataSnapshot dataSnapshot;

    public Article (String nom, boolean bool){
        this.nom=nom;
        this.bool=bool;
        //dataSnapshot.getValue(Article.class);
    }

    public Article(){};

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public HashMap<String, Object> recuperer(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("nom", nom);
        result.put("valide",bool);
        return result;
    }
}
