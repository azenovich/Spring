package courses.models.actors;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "guests")
@Inheritance(strategy = InheritanceType.JOINED)
public class Guest {
    @Id
    private int id;

    @Column(nullable = false, length = 30, unique = true)
    private String login;
    @Column(nullable = false,length = 60)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    private Date birthDate;
    @Column(nullable = false)
    private String email;
    private String phone;
    private String education;
    private String aboutYourSelf;
}
