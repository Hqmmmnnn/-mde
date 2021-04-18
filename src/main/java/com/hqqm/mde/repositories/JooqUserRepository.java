package com.hqqm.mde.repositories;

import com.hqqm.mde.jooq.tables.Users;
import com.hqqm.mde.models.User;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class JooqUserRepository implements UserRepository {
    private final DSLContext context;
    private final Users u = Users.USERS;

    @Override
    public Optional<User> findByEmail(String email) {
        return context
                .selectFrom(u)
                .where(u.EMAIL.eq(email))
                .fetchOptionalInto(User.class);
    }
}
