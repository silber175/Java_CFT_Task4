package Kruchkov.Task4;

import Kruchkov.Task4.Models.Login;
import Kruchkov.Task4.Models.User;
import Kruchkov.Task4.Repositories.LoginRepository;
import Kruchkov.Task4.Repositories.UserRepository;

import java.util.List;

public class DbRead {

    private UserRepository userRepository;
    private LoginRepository loginRepository;
    private List<User> users;
    private List<Login> logins;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setLoginRepository(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public List<Login> getLogins() {
        return this.logins;
    }
    public void read() {
        users = userRepository.findAll();
        logins = loginRepository.findAll();
    }
}
