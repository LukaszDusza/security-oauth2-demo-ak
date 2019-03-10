package akademia.service;


import akademia.model.UserInfo;
import akademia.repository.UserInfoRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserInfoService implements UserDetailsService {


    private UserInfoRepository userInfoRepository;

    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public List<UserInfo> getAllUsers() {
        return userInfoRepository.findAll();
    }

    public UserInfo getUserInfoByUserName(String username) {
        return userInfoRepository.findByUserName(username);
    }

    public UserInfo getByUserNameAndEnabled(String username, short enabled) {
        return userInfoRepository.findByUserNameAndEnable(username, enabled);
    }

    public UserInfo addNewUserInfo(UserInfo userInfo) {
        userInfo.setPassword(new BCryptPasswordEncoder()
                .encode(userInfo.getPassword()));
        return userInfoRepository.save(userInfo);
    }

    public void deleteUserInfoById(Integer id) {
        userInfoRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        UserInfo userInfo = getUserInfoByUserName(username);
        GrantedAuthority authority = new SimpleGrantedAuthority(userInfo.getRole());

        return new User(
                userInfo.getUserName(),
                userInfo.getPassword(),
                Arrays.asList(authority));
    }


}
