package jdbcDao;

import java.util.List;

public interface UserDetailsDao {
    public List<UserDetails> getAllUserDetails();
    public UserDetails getUserDetails(Long userId);
}
