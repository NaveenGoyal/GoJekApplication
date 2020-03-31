package com.demo.gojekapplication.datatest;

import com.demo.gojekapplication.data.model.Repository;

import java.util.ArrayList;
import java.util.List;

public class MockRepositoryModel {

    public static List<Repository> mockTrendingList() {
        List<Repository> repositoryList = new ArrayList<>();

        Repository r1 = new Repository();
        r1.setAuthor("a1");
        r1.setName("n1");
        r1.setDescription("Description 1");
        r1.setLanguage("java");
        r1.setStars(5);
        r1.setForks(21);
        repositoryList.add(r1);
        Repository r2 = new Repository();
        r2.setAuthor("a2");
        r2.setName("n2");
        r2.setDescription("Description 2");
        r2.setLanguage("C");
        r2.setStars(51);
        r2.setForks(200);
        repositoryList.add(r2);

        return repositoryList;
    }
}
