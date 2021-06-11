package com.hqqm.mde.repositories.impl;

import com.hqqm.mde.jooq.tables.Users;
import com.hqqm.mde.models.UpdateUserDTO;
import com.hqqm.mde.models.User;
import com.hqqm.mde.models.UserDTO;
import com.hqqm.mde.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class JooqUserRepository implements UserRepository {
    private final DSLContext context;
    private final Users u = Users.USERS;

    @Override
    public List<UserDTO> getAll() {
        return context
                .select(u.USER_ID.as("id"), u.EMAIL, u.FIRST_NAME, u.LAST_NAME, u.ROLE)
                .from(u)
                .fetchInto(UserDTO.class);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return context
                .selectFrom(u)
                .where(u.EMAIL.eq(email))
                .fetchOptionalInto(User.class);
    }

    @Override
    public void save(User user) {
        context.insertInto(u)
                .set(u.EMAIL, user.getEmail())
                .set(u.PASSWORD, user.getPassword())
                .set(u.FIRST_NAME, user.getFirstName())
                .set(u.LAST_NAME, user.getLastName())
                .set(u.ROLE, user.getRole().name())
                .execute();
    }

    @Override
    public void update(UpdateUserDTO user) {
        context
                .update(u)
                .set(u.ROLE, user.getRole())
                .where(u.USER_ID.eq(user.getId()))
                .execute();
    }
}
