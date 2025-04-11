package niroj.movie.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import niroj.movie.models.UserModel;
import java.util.Optional;

public interface UserRepository extends MongoRepository<UserModel, String> {
    Optional<UserModel> findByEmail(String email);
}
