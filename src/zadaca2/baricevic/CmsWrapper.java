package zadaca2.baricevic;

public class CmsWrapper {
    private Author author;
    private Category category;
    private News news;

    public CmsWrapper() {
    }

    public CmsWrapper(Author author, Category category, News news) {
        this.author = author;
        this.category = category;
        this.news = news;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    @Override
    public String toString() {
        return "CmsWrapper{" +
                "author=" + author +
                ", category=" + category +
                ", news=" + news +
                '}';
    }
}
