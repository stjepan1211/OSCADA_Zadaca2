package zadaca2.baricevic;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class News {

    private String name;
    private ArrayList<Author> authors;
    private ArrayList<Category> categorys;
    private Date publisDate;

    public News() {
    }

    public News(String name, Date publisDate) {
        this.name = name;
        this.publisDate = publisDate;
    }

    public News(String name, ArrayList<Author> authors, ArrayList<Category> categorys, Date publisDate) {
        this.name = name;
        this.authors = authors;
        this.categorys = categorys;
        this.publisDate = publisDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }

    public ArrayList<Category> getCategorys() {
        return categorys;
    }

    public void setCategorys(ArrayList<Category> categorys) {
        this.categorys = categorys;
    }

    public Date getPublisDate() {
        return publisDate;
    }

    public void setPublisDate(Date publisDate) {
        this.publisDate = publisDate;
    }

    @Override
    public String toString() {
        return "News{" +
                "name='" + name + '\'' +
                ", authors=" + authors +
                ", categorys=" + categorys +
                ", publisDate=" + publisDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(name, news.name) &&
                Objects.equals(authors, news.authors) &&
                Objects.equals(categorys, news.categorys) &&
                Objects.equals(publisDate, news.publisDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, authors, categorys, publisDate);
    }
}
