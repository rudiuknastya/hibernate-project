package hibernateDao;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;
    private String email;
    @Column(name = "pasword")
    private String password;
    @OneToOne // cascade type all
    @JoinColumn(name="user_details")
    private UserDetails userDetails;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "shopping_cart",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") }
    )
    private Set<Product> products = new HashSet<>();

    public User(Long userId, String email, String password, UserDetails userDetails) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.userDetails = userDetails;
    }
    public User() {}
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public Long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }
}
