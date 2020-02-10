package springBootApp.repos;

import springBootApp.domain.MessageEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessagesRepo extends CrudRepository<MessageEntity, Integer> {

    List<MessageEntity> findByTag(String tag);

}
