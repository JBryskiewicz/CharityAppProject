package pl.coderslab.charity.service.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.domain.Role;
import pl.coderslab.charity.domain.User;
import pl.coderslab.charity.domain.VerificationToken;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.email_sender.EmailSenderService;
import pl.coderslab.charity.service.verification_token.VerificationTokenService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final VerificationTokenService tokenService;
    private final EmailSenderService emailSenderService;
    private final EmailValidator emailValidator;
    @PersistenceContext
    private EntityManager entityManager;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder, VerificationTokenService tokenService, EmailSenderService emailSenderService, EmailValidator emailValidator) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.tokenService = tokenService;
        this.emailSenderService = emailSenderService;
        this.emailValidator = emailValidator;
    }

    private static final String USER_ROLE = "ROLE_USER";
    private String TOKEN = UUID.randomUUID().toString();

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUserName(username);
    }
    @Override
    public void saveUser(User user){

        boolean validEmail = emailValidator.test(user.getEmail());
        if(!validEmail){
            throw new IllegalStateException("Email nie potwierdzony");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(0);
        Role userRole = roleRepository.findByName(USER_ROLE);
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
        tokenService.save(user, TOKEN);

        String link = "http://localhost:8081/signup-confirm/" + TOKEN;
        ConfirmationEmail emailBody = new ConfirmationEmail();
        emailSenderService.sendConfirmationEmail(
                user.getEmail(),
                emailBody.buildEmail(user.getUserName(), link));

    }
    @Override
    public String confirmToken(String token){
        //TODO if condition to check if token's expired
        String userToken = tokenService.findByToken(token).getToken();
        if(!token.equals(userToken)){
            return "Nieprawidłowy klucz aktywacyjny";
        }
        User user = tokenService.findByToken(token).getUser();
        user.setEnabled(1);
        entityManager.merge(user);
        return "email potwierdzony / konto aktywowane";
    }

    @Override
    public void editUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        Role userRole = roleRepository.findByName(USER_ROLE);
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        entityManager.merge(user);
    }
    @Override
    public void softEditUser(User user) {
        entityManager.merge(user);
    }


}
