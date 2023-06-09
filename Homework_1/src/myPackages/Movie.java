package myPackages;

import myPackages.myException.MyException;

public class Movie implements Comparable<Movie>{
    private String fullTitle;
    private String genreName;

    private int year;
    private float length;

    public Movie(String fullTitle, int year, String genreName, float length) throws MyException {
        if (length >= 0) {
            this.fullTitle = fullTitle;
            this.year = year;
            this.genreName = genreName;
            this.length = length;
        } else {
            throw new MyException("Ошибка в длительности. ");
        }
    }

    public String getFullTitle() {
        return this.fullTitle;
    }

    public String getGenreName() {
        return this.genreName;
    }

    public float getLength() {
        return this.length;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setFullTitle(String fullTitle) {
        this.fullTitle = fullTitle;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public void setLength(int length) {
        this.length = length;
    }


    @Override
    public int compareTo(Movie movie) {
        return this.year - movie.getYear();
    }

    @Override
    public String toString() {
        return "Название: " + this.getFullTitle() + ", Жанр: " + this.getGenreName() + ", Длительность: " + this.getLength() + ", Год: " + this.getYear();
    }

}
