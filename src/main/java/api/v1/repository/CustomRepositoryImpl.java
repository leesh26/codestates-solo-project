package api.v1.repository;

import api.v1.entity.QUser;
import api.v1.entity.User;
import com.querydsl.core.types.dsl.BooleanExpression;

import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomRepositoryImpl implements CustomRepository{
    private final JPAQueryFactory queryFactory;

    QUser user = QUser.user;

    @Override
    public List<User> findUsersByCondition(String location, String type) {
        return queryFactory.selectFrom(user)
                .where(eqLocation(location), eqType(type))
                .orderBy(user.createdAt.desc())
                .fetch();
    }

    private BooleanExpression eqLocation(String location){
        if(StringUtils.isNullOrEmpty(location)){
            return null;
        }
        return user.companyLocation.eq(location);
    }

    private BooleanExpression eqType(String type){
        if(StringUtils.isNullOrEmpty(type)){
            return null;
        }
        return user.companyType.eq(type);
    }
}
