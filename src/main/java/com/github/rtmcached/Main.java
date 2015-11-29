package com.github.rtmcached;

import com.github.rtmcached.models.*;

class Article extends Model {

    public String title;

    public Article save() {
        if (this.id == null)
            return Articles.create(this);
        return Articles.update(this);
    }

    public void delete() {
        Articles.delete(this);
    }
}

class Articles {

    private Articles() { }

    protected static ModelStorage<Article> storage() {
        return Storages.getStorage(Article.class);
    }

    public static Article create() {
        return new Article();
    }

    public static Article create(Article instance) {
        return storage().create(instance);
    }

    public static Article update(Article instance) {
        return storage().update(instance);
    }

    public static void delete(Article instance) {
        storage().delete(instance);
    }
}

public class Main {

    public static void main(String[] args) {
        Storages.setStorage(new MemoryStorageProvider());
        Article entry1 = Articles.create();
        entry1.title = "entry1 title";
        entry1.save();
        Article entry2 = new Article();
        entry2.delete();
    }
}