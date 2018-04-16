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

        createNews();

        while (true){
            System.out.println("Ako ne želite ništa mijenjati upišite 'izlaz' ili bilo što za dalje");
            if(unos.next().toLowerCase().equals("izlaz"))
                break;
            System.out.println("Unesite broj indexa vijesti koju želite mijenjati.");
            try {
                int newsIndex = unos.nextInt();
                News selectedNews = newsList.get(newsIndex);
                System.out.println(selectedNews.toString());
                printOperations();
                int operationNumber = unos.nextInt();
                switch (operationNumber) {
                    case 0:
                        createNews();
                        break;
                    case 1:
                        readNews(selectedNews);
                        break;
                    case 2:
                        updateNews(selectedNews);
                        break;
                    case 3:
                        deleteNews(selectedNews);
                        break;
                    default:
                        System.out.println("Neispravan broj operacije.");
                        break;
                }
            }
            catch (NumberFormatException ex){
                System.out.println("Potrebno unjeti broj");
            }
            catch (IndexOutOfBoundsException ex){
                System.out.println("Potrebno unjeti ispravan index");
            }
            catch (Exception ex){
                System.out.println("Došlo je do greške");
            }
            finally {
                System.out.println("Unesite 'izlaz' za prestanak promjena vijesti ili bilo sto za dalje");
                if(unos.next().toLowerCase().equals("izlaz"))
                    break;
            }
        }

        printByCategory();
        printByAuthors();
        printByDate();
    }

    private void printByCategory(){
        System.out.println();
        System.out.println("Po kategoriji:");
        ArrayList<PrintByCategoryModel> printByCategoryModels = new ArrayList<>();
        newsList.forEach((n) -> {
            n.getCategorys().forEach((c) -> {
                printByCategoryModels.add(new PrintByCategoryModel(new Category(c.getName()),
                        n.getAuthors(),new News(n.getName(),n.getPublisDate())));
            });
        });
        Collections.sort(printByCategoryModels,
                (o1, o2) -> o1.getCategory().getName().compareTo(o2.getCategory().getName()));
        printByCategoryModels.forEach((x) -> {
            ArrayList<Author> authors = x.getAuthors();
            System.out.println("Kategorija: " + x.getCategory().getName() + " Vijest: " + x.getNews().getName());
            authors.forEach((k) -> System.out.println("Autor: " + k.getFirstName() + " " + k.getLastName() ));
        });
    }

    private void printByDate() {
        System.out.println();
        System.out.println("Po datumu:");
        Collections.sort(newsList,
                (o1, o2) -> o1.getPublisDate().compareTo(o2.getPublisDate()));
        printNewsList(newsList, false);
    }

    private void printByAuthors(){
        System.out.println();
        System.out.println("Po autoru:");
        ArrayList<PrintByAuthorModel> printByAuthorModels = new ArrayList<>();
        newsList.forEach((n) -> {
            n.getAuthors().forEach((c) -> {
                ArrayList categories = n.getCategorys();
                printByAuthorModels.add(new PrintByAuthorModel(c,n,categories));
            });
        });
        Collections.sort(printByAuthorModels,
                (o1, o2) -> o1.getAuthor().getLastName().compareTo(o2.getAuthor().getLastName()));
        printByAuthorModels.forEach((x) -> {
            ArrayList<Category> categories = x.getCategories();
            System.out.println("Autor: " + x.getAuthor().getFirstName() + " " + x.getAuthor().getLastName()
                    + " Vijest: " + x.getNews().getName());
            categories.forEach((k) -> System.out.println("Kategorija: " + k.getName()));
        });
    }

    private void createNews(){
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
    }

    private void readNews(News selectedNews){
        printNewsList(new ArrayList<>() {{
            add(selectedNews);
        }}, false);
    }

    private void updateNews(News selectedNews){
        while (true){
            System.out.println("Unesite što želite ažurirati.");
            System.out.println("'I' za ime vijesti.");
            System.out.println("'A' za autora vijesti.");
            System.out.println("'K' za kategoriju vijesti.");
            String selectedUpdate = unos.next();
            switch (selectedUpdate.toLowerCase()){
                case "i":
                    System.out.println("Unesite novo ime za odabranu vijest.");
                    String newName = unos.next();
                    selectedNews.setName(newName);
                    printNewsList(new ArrayList<>() {{
                        add(selectedNews);
                    }}, false);
                    break;
                case "a":
                    updateAuthors(selectedNews);
                    break;
                case "k":
                    updateCategories(selectedNews);
                    break;
                 default:
                     System.out.println("Unjeli ste neispravno ažuriranje.");
                     break;
            }

            System.out.println("Unesite 'izlaz' za prestanak azuriranja vijesti ili bilo sto za dalje");
            if(unos.next().toLowerCase().equals("izlaz"))
                break;
        }
    }

    private void updateAuthors(News selectedNews){
        System.out.println("Detalji vijest:");
        printNewsList(new ArrayList<>() {{
            add(selectedNews);
        }}, true);
        while (true){
            System.out.println("Unesite index autora kojeg želite ažurirati");
            try {
                int selectedIndex = unos.nextInt();
                Author selectedAuthor = selectedNews.getAuthors().get(selectedIndex);
                System.out.println("Unesite novo ime autora.");
                String authorName = unos.next();
                System.out.println("Unesite novo prezime autora.");
                String authorLastName = unos.next();
                selectedAuthor.setFirstName(authorName);
                selectedAuthor.setLastName(authorLastName);
            }
            catch (NumberFormatException ex){
                System.out.println("Unjeli ste neisprava format indexa.");
            }
            catch (Exception ex){
                System.out.println("Došlo je do greške.");
            }
            System.out.println("Unesite 'izlaz' za prestanak azuriranja autora ili bilo sto za dalje");
            if(unos.next().toLowerCase().equals("izlaz"))
                break;
        }
    }

    private void updateCategories(News selectedNews){
        System.out.println("Detalji vijest:");
        printNewsList(new ArrayList<>() {{
            add(selectedNews);
        }}, true);
        while (true){
            System.out.println("Unesite index kategorije koju želite ažurirati");
            try {
                int selectedIndex = unos.nextInt();
                Category selectedCategory = selectedNews.getCategorys().get(selectedIndex);
                System.out.println("Unesite novo ime kategorije.");
                String categoryName = unos.next();
                selectedCategory.setName(categoryName);
            }
            catch (NumberFormatException ex){
                System.out.println("Unjeli ste neispravan format indexa.");
            }
            catch (Exception ex){
                System.out.println("Došlo je do greške.");
            }

            System.out.println("Unesite 'izlaz' za prestanak azuriranja kategorija ili bilo sto za dalje");
            if(unos.next().toLowerCase().equals("izlaz"))
                break;
        }
    }

    private void deleteNews(News selectedNews){
        while (true){
            System.out.println("Unesite što želite obrisati.");
            System.out.println("'I' za vijesti.");
            System.out.println("'A' za autora vijesti.");
            System.out.println("'K' za kategoriju vijesti.");
            String selectedUpdate = unos.next();
            System.out.println("Stanje:");
            printNewsList(new ArrayList<>() {{
                add(selectedNews);
            }}, true);
            switch (selectedUpdate.toLowerCase()) {
                case "i":
                    newsList.remove(selectedNews);
                    System.out.println("Novo stanje vijesti:");
                    printNewsList(newsList, false);
                    break;
                case "a":
                    try {
                        int index = Integer.parseInt(unos.next());
                        Author selectedAuthor = selectedNews.getAuthors().get(index);
                        selectedNews.getAuthors().remove(selectedAuthor);
                    } catch (NumberFormatException ex) {
                        System.out.println("Unjeli ste krivi format indexa.");
                    } catch (Exception ex) {
                        System.out.println("Došlo je do graške.");
                    }
                    break;
                case "k":
                    try {
                        int index = Integer.parseInt(unos.next());
                        Category selectedCategory = selectedNews.getCategorys().get(index);
                        selectedNews.getCategorys().remove(selectedCategory);
                    } catch (NumberFormatException ex) {
                        System.out.println("Unjeli ste krivi format indexa.");
                    } catch (Exception ex) {
                        System.out.println("Došlo je do graške.");
                    }
                    break;
                default:
                    System.out.println("Unjeli ste neispravno brisanje.");
                    break;
            }

            System.out.println("Unesite 'izlaz' za prestanak azuriranja vijesti ili bilo sto za dalje");
            if(unos.next().toLowerCase().equals("izlaz"))
                break;
        }
    }

    private void printOperations() {
        System.out.println("Unesite operaciju koju želite izvesti.");
        System.out.println("0 za kreiranje novih vijesti");
        System.out.println("1 za ispis vijesti");
        System.out.println("2 za azuriranje vijesti");
        System.out.println("3 za brisanje vijesti");

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
