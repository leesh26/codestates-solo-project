package api.v1.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Sex sex;
    private String companyName;
    private String companyType;
    private String companyLocation;

    @Builder
    public User(String name, String password, Sex sex, String companyName, String companyType, String companyLocation) {
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.companyName = companyName;
        this.companyType = companyType;
        this.companyLocation = companyLocation;
    }

    public enum Sex{
        m("남성"),
        w("여성");

        private String kor;

        Sex(String kor) {
            this.kor = kor;
        }
    }
}
