package com.ylysenkova.movieland.web.dto.response;

import com.ylysenkova.movieland.model.Country;
import com.ylysenkova.movieland.model.Genre;
import com.ylysenkova.movieland.model.Movie;
import com.ylysenkova.movieland.model.Review;
import com.ylysenkova.movieland.web.dto.CountryDto;
import com.ylysenkova.movieland.web.dto.GenreDto;
import com.ylysenkova.movieland.web.dto.ReviewDto;

import java.util.ArrayList;
import java.util.List;

public class MovieWithReviewResponse {

    private int id;
    private String nameRussian;
    private String nameNative;
    private int yearOfRelease;
    private String description;
    private double rating;
    private double price;
    private String picturePath;
    private List<CountryDto> countries = new ArrayList<>();
    private List<GenreDto> genres = new ArrayList<>();
    private List<ReviewDto> reviews = new ArrayList<>();

    public MovieWithReviewResponse() {
    }

    public MovieWithReviewResponse(Movie movie) {
        this.id = movie.getId();
        this.nameRussian = movie.getNameRussian();
        this.nameNative = movie.getNameNative();
        this.yearOfRelease = movie.getYearOfRelease();
        this.description = movie.getDescription();
        this.rating = movie.getRating();
        this.price = movie.getPrice();
        this.picturePath = movie.getPicturePath();
        List<Country> countryList = movie.getCountries();
        if(null != countryList) {
            for (Country country : countryList) {
                this.countries.add(new CountryDto(country));
            }
        }
        List<Genre> genreList = movie.getGenres();
        if(null != genreList) {
            for (Genre genre : genreList) {
                this.genres.add(new GenreDto(genre));
            }
        }
        List<Review> reviewList = movie.getReviews();
        if(null != reviewList) {
            for (Review review : reviewList) {
                this.reviews.add(new ReviewDto(review));
            }
        }
    }

    public int getId() {
         return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameRussian() {
        return nameRussian;
    }

    public void setNameRussian(String nameRussian) {
        this.nameRussian = nameRussian;
    }

    public String getNameNative() {
        return nameNative;
    }

    public void setNameNative(String nameNative) {
        this.nameNative = nameNative;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public List<CountryDto> getCountries() {
        return countries;
    }

    public void setCountries(List<CountryDto> countries) {
        this.countries = countries;
    }

    public List<GenreDto> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreDto> genres) {
        this.genres = genres;
    }

    public List<ReviewDto> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDto> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "MovieWithReviewResponse{" +
                "id=" + id +
                ", nameRussian='" + nameRussian + '\'' +
                ", nameNative='" + nameNative + '\'' +
                ", yearOfRelease=" + yearOfRelease +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", price=" + price +
                ", picturePath='" + picturePath + '\'' +
                ", countries=" + countries +
                ", genres=" + genres +
                ", reviews=" + reviews +
                '}';
    }
}
