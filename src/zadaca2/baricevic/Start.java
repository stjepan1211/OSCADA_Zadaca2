package zadaca2.baricevic;

import javax.management.InvalidAttributeValueException;
import javax.naming.InvalidNameException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Start {

    /*Create a CMS using console interface. Types used: Author, Category, News. Program enables CRUD on each type,
     list news by categories, authors and dates of news. One news can be in many categories*/
    List<News> newsList = new ArrayList<>();
    News news;
    Scanner unos = new Scanner(System.in);
    ArrayList<Author> authors;
    ArrayList<Category> categories;

    public Start() {
        while (true) {
            news = new News();
            System.out.println("Unesite vijest:");
            while (true){
                try {
                    System.out.println("Unesite ime vijesti:");
                    String imeVijesti = unos.next();
                    if (imeVijesti.equals(""))
                        throw new InvalidNameException();
                    news.setName(imeVijesti);
                    break;
                }
                catch (InvalidNameException ex){
                    System.out.println("Ime vijesti ne može biti prazno");
                }
                catch (Exception ex){
                    System.out.println("Došlo je do greške.");
                    break;
                }
            }

            authors = new ArrayList<>();
            while (true){
                try {
                    System.out.println("Unesite ime autora:");
                    String imeAutora = unos.next();
                    if (imeAutora.equals(""))
                        throw new InvalidNameException();
                    System.out.println("Unesite prezime autora:");
                    String prezimeAutora = unos.next();
                    if (prezimeAutora.equals(""))
                        throw new InvalidNameException();
                    authors.add(new Author(imeAutora, prezimeAutora));
                }
                catch (InvalidNameException ex){
                    System.out.println("Ime ili prezime autora ne može biti prazno");
                }
                catch (Exception ex){
                    System.out.println("Došlo je do greške.");
                    break;
                }
                System.out.println("Unesite 'izlaz' za prestanak unosa autora ili bilo sto za dalje");
                if(unos.next().toLowerCase().equals("izlaz"))
                    break;
            }

            categories = new ArrayList<>();
            while (true){
                try {
                    System.out.println("Unesite kategoriju:");
                    String kategorija = unos.next();
                    if (kategorija.equals(""))
                        throw new InvalidNameException();
                    categories.forEach((x) -> {
                        if(x.getName().equals(kategorija)) {
                            throw new InputMismatchException();
                        }
                    });
                    categories.add(new Category(kategorija));
                }
                catch (InvalidNameException ex){
                    System.out.println("Kategorija ne može biti prazna");
                }
                catch (InputMismatchException ex){
                    System.out.println("Ne možete unijeti istu kategoriju dva puta");
                }
                catch (Exception ex){
                    System.out.println("Došlo je do greške.");
                    break;
                }
                System.out.println("Unesite 'izlaz' za prestanak unosa kategorija ili bilo sto za dalje");
                if(unos.next().toLowerCase().equals("izlaz"))
                    break;
            }

            news.setAuthors(authors);
            news.setCategorys(categories);
            news.setPublisDate(new Date());
            newsList.add(news);

            System.out.println("Unesite 'izlaz' za prestanak unosa vijesti ili bilo sto za dalje");
            if(unos.next().toLowerCase().equals("izlaz"))
                break;
        }
        System.out.println("Unesene vijesti");
        printNewsList(newsList, true);
        System.out.println("------------------------------");
        System.out.println();

        /*while (true){
            System.out.println("Za promjenu vijesti unesite vrijednost indexa vijesti koju želite promijeniti");
            int index =
        }*/

        //CRUD nad svime
        //izlistaj novosti po kategorijim autoru i datumu
    }
    private void printNewsList(List<News> newsList,boolean withIndex){
        if(withIndex)
            newsList.forEach((x) -> {
                int index = newsList.indexOf(x);
                System.out.println("Naziv: " + x.getName() + ", index: " + index);
                x.getAuthors().forEach((a) -> {
                    int indexAuthor = x.getAuthors().indexOf(a);
                    System.out.println("Ime autora: " + a. getFirstName() + ", prezime autora " +
                    a.getLastName() + ", index: " + indexAuthor);
                });
                x.getCategorys().forEach((c) -> {
                    int indexCategory = x.getCategorys().indexOf(c);
                    System.out.println("Ime kategorije: " + c.getName() + ", index: " + indexCategory);
                });
                System.out.println(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(x.getPublisDate()));
            });
        else
            newsList.forEach((x) -> {
                System.out.println("Naziv: " + x.getName());
                x.getAuthors().forEach((a) -> {
                    System.out.println("Ime autora: " + a. getFirstName() + ", prezime autora " +
                            a.getLastName());
                });
                x.getCategorys().forEach((c) -> {
                    System.out.println("Ime kategorije: " + c.getName());
                });
                System.out.println("Datum: " + new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(x.getPublisDate()));
            });
    }


    public static void main(String[] args) {
        new Start();
    }
}
