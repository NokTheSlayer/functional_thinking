package myPackages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import myPackages.myException.MyException;

public class FilmGenres {
    private String genre;
    private List<FilmList> filmListStorage;

    public FilmGenres(String genre) throws MyException {
        if (genre != null) {
            this.filmListStorage = new ArrayList();
            this.genre = genre;
        } else {
            throw new MyException("Ошибка в жанре фильма ");
        }
    }

    public String getGenre() {
        return this.genre;
    }

    public List<FilmList> getFilmListStorage() {
        return this.filmListStorage;
    }


    public void setFilmListStorage(List<FilmList> filmListStorage) {
        this.filmListStorage = filmListStorage;
    }

    public void addChildToClass(FilmList filmList) {
        this.filmListStorage.add(filmList);
    }

    public double averageValueByMarks() {
        double result = 0.0;

        FilmList filmList;
        for(Iterator var3 = this.filmListStorage.iterator(); var3.hasNext(); result += (double)filmList.getLength()) {
            filmList = (FilmList)var3.next();
        }

        return result / (double)this.filmListStorage.size();
    }

    public List<FilmList> filterFilmListByFilmGenres(String genre) {
        List<FilmList> a = (List)this.getFilmListStorage().stream().filter((p) -> {
            return p.getGenreName().equals(genre);
        }).collect(Collectors.toList());
        return a;
    }



}
