package api.v1;

import api.v1.entity.User;
import api.v1.repository.UserRepository;
import api.v1.service.UserService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class QueryDslServiceTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void 모든회원조회() {
        //given : 회원 생성 (2명) - String name, String password, Sex sex, String companyName, String companyType, String companyLocation
        User userA = User.builder()
                .name("김코딩").password("s4goodbye!").sex(User.Sex.m).companyName("프로젝트스테이츠")
                .companyType("005").companyLocation("001")
                .build();
        userRepository.save(userA);

        User userB = User.builder()
                .name("박코딩").password("s4goodbye!").sex(User.Sex.m).companyName("알고리즘스테이츠")
                .companyType("006").companyLocation("002")
                .build();
        userRepository.save(userB);

        //when : 조회하기
        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));

        //then : 조회한 회원 중 0번째 회원과 1번째 회원 정보가 맞는지 확인
        assertTrue(users.get(0).getName().equals(userA.getName()));
        assertTrue(users.get(1).getName().equals(userB.getName()));
    }
}
