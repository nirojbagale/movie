package niroj.movie.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class UserModel {
    @Id
    private String id;
    private String username;
    private String email;
    private String password;
    private Number phoneNumber;
    private String street;
    private String city;
    private String country;

    public UserModel() {}

    public UserModel(String username, String email, String password, Number phoneNumber, String city, String street, String country) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.street = street;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.country = country;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Number getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(Number phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}
