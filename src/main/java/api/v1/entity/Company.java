package api.v1.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

// 현재 요구되는 table 정보(user테이블에 company 정보가 모두 포함됨)에 맞추기 위해 임베디드 타입으로 구현했으나
// 실제로 회원:기업 = 다대일로 구현하는 것이 맞을 것 같음

@Embeddable
@Getter @Setter
public class Company {
    @Column(name = "company_name")
    private String name;

    @Column(name = "company_location")
    private String location;

    @Column(name = "company_type")
    private String type;

    public Company() {}

    public Company(String name, String location, String type) {
        this.name = name;
        this.location = location;
        this.type = type;
    }
}
