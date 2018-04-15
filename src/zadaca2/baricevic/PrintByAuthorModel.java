package zadaca2.baricevic;

import java.util.ArrayList;

public class PrintByAuthorModel {
    private Author author;
    private News news;
    private ArrayList<Category> categories;

    public PrintByAuthorModel(Author author, News news, ArrayList<Category> categories) {
        this.author = author;
        this.news = news;
        this.categories = categories;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }
}
