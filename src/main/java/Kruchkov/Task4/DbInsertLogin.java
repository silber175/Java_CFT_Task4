package Kruchkov.Task4;


import Kruchkov.Task4.Models.Login;
import Kruchkov.Task4.Repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DbInsertLogin {
        private LoginRepository loginRepository;

        public DbInsertLogin() {

        }

        @Autowired
        public void setLoginRepository(LoginRepository loginRepository) {
            this.loginRepository = loginRepository;
        }

        public void accept(List<Login> logins) {
            loginRepository.saveAll(logins);
        }
    }

