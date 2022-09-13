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
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private static final String USER_ROLE = "ROLE_USER";
    private static final String LINK = "http://localhost:8081/signup-confirm/";
    private static final String CONFIRM_ERROR = "Email nie potwierdzony";
    private static final String TOKEN_ERROR = "Nieprawidłowy klucz aktywacyjny";
    private static final String TOKEN_CONFIRMED = "email potwierdzony / konto aktywowane";
    private static final String TOKEN_EXPIRED = "Ten klucz aktywacyjny jest przeterminowany";
    private static final int DISABLED = 0;
    private static final int ENABLED = 1;
    private final String TOKEN = UUID.randomUUID().toString();

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final VerificationTokenService tokenService;
    private final EmailSenderService emailSenderService;
    private final EmailValidator emailValidator;
    @PersistenceContext
    private EntityManager entityManager;

    //AllArgsConstructor creates problem here (requires a bean type of lang.String), solve it to replace constructor.
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder, VerificationTokenService tokenService,
                           EmailSenderService emailSenderService, EmailValidator emailValidator) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.emailSenderService = emailSenderService;
        this.emailValidator = emailValidator;
    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public void saveUser(User user) {

        boolean validEmail = emailValidator.test(user.getEmail());
        if (!validEmail) {
            throw new IllegalStateException(CONFIRM_ERROR);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(DISABLED);
        Role userRole = roleRepository.findByName(USER_ROLE);
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
        tokenService.save(user, TOKEN);

        String link = LINK + TOKEN;
        ConfirmationEmail emailBody = new ConfirmationEmail();
        emailSenderService.sendConfirmationEmail(
                user.getEmail(),
                emailBody.buildEmail(user.getUserName(), link));
    }

    @Override
    public String confirmToken(String token) {
        try{
            VerificationToken verificationToken = tokenService.findByToken(token);
            LocalDateTime now = LocalDateTime.now();
            if(now.isAfter(verificationToken.getExpiryDate())){
                return TOKEN_EXPIRED;
            }
            if(!token.equals(verificationToken.getToken())){
                return TOKEN_ERROR;
            }
            User user = tokenService.findByToken(token).getUser();
            user.setEnabled(ENABLED);
            entityManager.merge(user);
            return TOKEN_CONFIRMED;
        }catch (NullPointerException e){
            return TOKEN_ERROR;
        }
    }

    @Override
    public void editUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(ENABLED);
        Role userRole = roleRepository.findByName(USER_ROLE);
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        entityManager.merge(user);
    }

    @Override
    public void softEditUser(User user) {
        entityManager.merge(user);
    }
}
