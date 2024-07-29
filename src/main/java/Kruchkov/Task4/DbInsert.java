package Kruchkov.Task4;

import Kruchkov.Task4.Models.Login;
import Kruchkov.Task4.Models.User;
import Kruchkov.Task4.Repositories.LoginRepository;
import Kruchkov.Task4.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;



import java.util.List;
import java.util.function.Consumer;


public class DbInsert  {

    private UserRepository userRepository;
    private LoginRepository loginRepository;
    private  List<User> users ;
    private  List<Login> logins;
    private User user;

    public DbInsert() {

    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setLoginRepository(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public UserRepository getUserRepository() {
        return this.userRepository;
    }

    public LoginRepository getLoginRepository() {
        return this.loginRepository ;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setLogins(List<Login> logins) {
        this.logins = logins;
    }

    public User insertUser() {

        userRepository.save(user); // .saveAll(users);
        return user;
    }
    public void insertLogin() {
        loginRepository.saveAll(logins);
    }
}
