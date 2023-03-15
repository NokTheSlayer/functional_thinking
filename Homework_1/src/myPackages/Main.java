package myPackages;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import myPackages.myException.MyException;

public class Main {
    public Main() {
    }

    public static List<Genre> readData(String dataPath) throws IOException, MyException {
        List<Genre> genres = new ArrayList();
        BufferedReader br = new BufferedReader(new FileReader(dataPath));
        int iteration = 0;

        String line;
        while((line = br.readLine()) != null) {
            try {
                boolean filmExist = false;
                ++iteration;
                String[] values = line.split("\\s+");
                Movie movie = new Movie(values[0] + " " + values[1], Integer.parseInt(values[2]), values[3], Float.parseFloat(values[4]));
                Iterator var8 = genres.iterator();

                while(var8.hasNext()) {
                    Genre genre = (Genre)var8.next();
                    if (genre.getName().equals(values[3])) {
                        genre.addMovie(movie);
                        filmExist = true;
                        break;
                    }
                }

                if (!filmExist) {
                    Genre genre = new Genre(values[3]);
                    genre.addMovie(movie);
                    genres.add(genre);
                }
            } catch (NumberFormatException var10) {
                System.out.println("Ошибка в формате данных файла \"" + dataPath + "\". Запись " + (iteration - 1));
            } catch (NullPointerException | IndexOutOfBoundsException var11) {
                System.out.println("Недостаточно данных в файле \"" + dataPath + "\". Запись " + (iteration - 1));
            }
        }

        br.close();
        if (genres.isEmpty()) {
            throw new MyException("В файле \"" + dataPath + "\" не найдено информации об фильмах");
        } else {
            return genres;
        }
    }

    public static void getAllGenresAverage(List<Genre> genres){
        genres.forEach(genre ->
            System.out.println("Жанр фильма: " + genre.getName() + " Ср. арифметическое: " + genre.getAverageValueByYear())
        );
    }

    public static void getSortedMoviesByYearByEachGenre(List<Genre> genres){
        for (Genre genre : genres){
            System.out.println("Жанр: " + genre.getName());
            genre.getMovies().stream().sorted().forEach(System.out::println);
        }
    }

    public static void getInfoMoviesByGenre(List<Genre> genres, String name){
        genres.stream().filter(genre -> genre.getName().equals(name)).forEach(genre ->
                genre.getMovies().forEach(System.out::println)
        );
    }

    public static void main(String[] args) {
        try {
            String dataPath = "films_data.txt";
            List<Genre> genres = readData(dataPath);
            Scanner sc = new Scanner(System.in);
            while(true) {
                System.out.print("Выберите действие, которое хотите выполнить:\n" +
                        "1. Вывести для каждого жанра среднее арифметическое по длительности фильма\n" +
                        "2. Вывести названия фильмов, упорядоченные по году, для каждого жанра\n" +
                        "3. Вывести информацию о фильме и их длительность введенному жанру\n" +
                        "4. Выход\n" +
                        ">>>");
                int action = sc.nextInt();
                switch (action) {
                    case 1:
                        getAllGenresAverage(genres);
                        break;
                    case 2:
                        getSortedMoviesByYearByEachGenre(genres);
                        break;
                    case 3:
                        while(true) {
                            System.out.print("Вывести название жанра или введите 'назад' чтобы выйти\n>>>");
                            String genreName = sc.next();
                            if (genreName.toLowerCase().equals("назад")) {
                                System.out.println("__________________");
                                break;
                            }
                            getInfoMoviesByGenre(genres, genreName);
                        }
                    case 4:
                        return;
                }
            }
        } catch (FileNotFoundException | MyException var9) {
            System.out.println(var9.getMessage() + ". Завершение работы.");
        } catch (IOException var10) {
            System.out.println(var10.getMessage());
        }

    }
}