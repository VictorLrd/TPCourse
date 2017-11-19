package com.example.victor.applicourse.request;

import com.example.victor.applicourse.Article;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 08/11/2017.
 */

public interface RequeteService {

    public void onDataChange(List<Article> listArticles);
}
