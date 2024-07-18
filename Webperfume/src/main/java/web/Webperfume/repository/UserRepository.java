package web.Webperfume.repository;

import web.Webperfume.model.User;

public interface UserRepository {
    User findByUsername(String username);
}


