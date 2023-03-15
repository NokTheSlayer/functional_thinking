package myPackages;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

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
                    Genre filmGenres = (Genre)var8.next();
                    if (filmGenres.getName().equals(values[3])) {
                        filmGenres.addMovie(movie);
                        filmExist = true;
                        break;
                    }
                }

                if (!filmExist) {
                    Genre filmGenres = new Genre(values[3]);
                    filmGenres.addMovie(movie);
                    genres.add(filmGenres);
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

    public static void main(String[] args) {
        try {
            String dataPath = "films_data.txt";
            List<Genre> genres = readData(dataPath);
            Scanner sc = new Scanner(System.in);
            label60:
            while(true) {
                System.out.print("Выберите действие, которое хотите выполнить:\n" +
                        "1. Вывести для каждого жанра среднее арифметическое по длительности фильма\n" +
                        "2. Вывести названия фильмов, упорядоченные по году, для каждого жанра\n" +
                        "3. Вывести информацию о фильме и их длительность введенному жанру\n" +
                        "4. Выход\n" +
                        ">>>");
                int action = sc.nextInt();
                Iterator var11;
                Genre genre;
                Stream var10000;
                PrintStream var10001;

                switch (action) {
                    case 1:
                        var11 = genres.iterator();

                        while(var11.hasNext()) {
                            genre = (Genre) var11.next();
                            System.out.println("Жанр фильма: " + genre.getName() + " Ср. арифметическое: " + genre.getAverageValueByYear());
                        }

                        System.out.println("__________________");
                        break;
                    case 2:
                        var11 = genres.iterator();

                        while(var11.hasNext()) {
                            genre = (Genre)var11.next();
                            System.out.println("Жанр: " + genre.getName());
                            var10000 = genre.getMovies().stream().sorted();
                            var10001 = System.out;
                            var10000.forEach(var10001::println);
                            System.out.println();
                        }

                        System.out.println("__________________");
                        break;
                    case 3:
                        while(true) {
                            System.out.print("Вывести название жанра или введите 'назад' чтобы вернуться назад\n>>>\n");
                            String title = sc.next();
                            if (title.toLowerCase().equals("назад")) {
                                System.out.println("__________________");
                            }

                            for(Iterator var6 = genres.iterator(); var6.hasNext(); System.out.println()) {
                                genre = (Genre) var6.next();
                                System.out.println("Жанр: " + genre.getName());
                                List<Movie> filteredList = genre.getMoviesByGenre(title);
                                if (filteredList.isEmpty()) {
                                    System.out.println("Нет такоих фильмов.");
                                } else {
                                    var10000 = filteredList.stream().sorted();
                                    var10001 = System.out;
                                    var10000.forEach(var10001::println);
                                }
                            }
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