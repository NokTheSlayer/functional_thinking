package myPackages;

import java.util.ArrayList;
import java.util.List;
import myPackages.myException.MyException;

public class Genre {
    private String name;
    private List<Movie> movies;

    public Genre(String name) throws MyException {
        if (name != null) {
            this.movies = new ArrayList();
            this.name = name;
        } else {
            throw new MyException("Ошибка в жанре фильма ");
        }
    }

    public String getName() {
        return this.name;
    }

    public List<Movie> getMovies() {
        return this.movies;
    }


    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public void addMovie(Movie movie) {
        this.movies.add(movie);
    }

    public double getAverageValueByYear() {
        double result = 0.0;
        result = movies.stream().mapToDouble(Movie::getLength).sum();
        return result / movies.size();
    }
}
