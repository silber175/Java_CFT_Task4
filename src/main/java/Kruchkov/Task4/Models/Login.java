package Kruchkov.Task4.Models;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Logins")

public class Login {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    @Column(name="access_date")
    private LocalDate accessDate;


    @ManyToOne
    @JoinColumn(name="userId", referencedColumnName = "id")

    private User userId;

    public void setUserId(User userId) {
        this.userId = userId;
    }
    public User getUserId() {
        return this.userId;
    }
    @Column(name="application")
    private String application;

     public void setAccessDate(LocalDate accessDate) {
        this.accessDate = accessDate;
    }

      public void setApplication(String application) {
        this.application = application;
    }

    public String getApplication() {
       return  this.application;
    }
}
