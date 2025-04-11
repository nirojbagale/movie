package niroj.movie.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "movies")
public class MovieModel {

    @Id
    private String id;
    private String title;
    private Double price;
    private String synopsis;

    @JsonProperty("isTvShow")
    private boolean isTvShow;

    private String poster;
    private double rentPrice;
    private double purchasePrice;

    @JsonProperty("isFeatured")
    private boolean isFeatured;

    private String genre;
    private int releaseYear;
    private int seasons;
    private int duration;
    private double rating;

    // Constructors
    public MovieModel() {}

    public MovieModel(String title, Double price, String synopsis, boolean isTvShow, String poster,
                 double rentPrice, double purchasePrice, boolean isFeatured,
                 String genre, int releaseYear, int seasons, int duration, double rating) {
        this.title = title;
        this.price = price;
        this.synopsis = synopsis;
        this.isTvShow = isTvShow;
        this.poster = poster;
        this.rentPrice = rentPrice;
        this.purchasePrice = purchasePrice;
        this.isFeatured = isFeatured;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.seasons = seasons;
        this.duration = duration;
        this.rating = rating;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public String getSynopsis() { return synopsis; }
    public void setSynopsis(String synopsis) { this.synopsis = synopsis; }

    @JsonProperty("isTvShow")
    public boolean isTvShow() { return isTvShow; }
    public void setIsTvShow(boolean isTvShow) { this.isTvShow = isTvShow; }

    public String getPoster() { return poster; }
    public void setPoster(String poster) { this.poster = poster; }
    public double getRentPrice() { return rentPrice; }
    public void setRentPrice(double rentPrice) { this.rentPrice = rentPrice; }
    public double getPurchasePrice() { return purchasePrice; }
    public void setPurchasePrice(double purchasePrice) { this.purchasePrice = purchasePrice; }

    @JsonProperty("isFeatured")
    public boolean isFeatured() { return isFeatured; }
    public void setIsFeatured(boolean isFeatured) { this.isFeatured = isFeatured; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public int getReleaseYear() { return releaseYear; }
    public void setReleaseYear(int releaseYear) { this.releaseYear = releaseYear; }
    public int getSeasons() { return seasons; }
    public void setSeasons(int seasons) { this.seasons = seasons; }
    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
}
