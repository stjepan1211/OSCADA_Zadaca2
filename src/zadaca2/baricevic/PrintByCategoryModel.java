package zadaca2.baricevic;

import java.util.ArrayList;

public class PrintByCategoryModel {
     private Category category;
     private ArrayList<Author> authors;
     private News news;

    public PrintByCategoryModel(Category category, ArrayList<Author> authors, News news) {
        this.category = category;
        this.authors = authors;
        this.news = news;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }
}
