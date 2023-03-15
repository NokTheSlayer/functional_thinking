package myPackages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
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

        Movie movie;
        for(Iterator var3 = this.movies.iterator(); var3.hasNext(); result += (double) movie.getLength()) {
            movie = (Movie)var3.next();
        }

        return result / (double)this.movies.size();
    }

    public List<Movie> getMoviesByGenre(String genre) {
        List<Movie> a = (List)this.getMovies().stream().filter((p) -> {
            return p.getGenreName().equals(genre);
        }).collect(Collectors.toList());
        return a;
    }



}
