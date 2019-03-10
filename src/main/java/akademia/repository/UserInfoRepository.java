package akademia.repository;

import akademia.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    UserInfo findByUserNameAndEnable(String userName, short enabled);

    UserInfo findByUserName(String userName);



}
