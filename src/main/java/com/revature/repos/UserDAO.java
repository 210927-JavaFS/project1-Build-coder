package com.revature.repos;

import com.revature.models.User;
import java.util.List;

public interface UserDAO {
    

    public List<User> findAllUsers();
    public User findById(int id);
    public User findByName(String user_name);
    public boolean addUser(User user);
    public boolean updateUser(User user);
    public boolean deleteUser(User user);
}
