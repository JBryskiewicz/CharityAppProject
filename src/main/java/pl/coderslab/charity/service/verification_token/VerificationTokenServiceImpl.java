package pl.coderslab.charity.service.verification_token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.domain.User;
import pl.coderslab.charity.domain.VerificationToken;
import pl.coderslab.charity.repository.VerificationTokenRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

@Service
@Transactional
@AllArgsConstructor
public class VerificationTokenServiceImpl implements VerificationTokenService{
    private final VerificationTokenRepository tokenRepository;

    private static final int EXPIRATION = 24 * 60;

    @Override
    public VerificationToken findByToken(String token) {
        return tokenRepository.findByToken(token);
    }
    @Override
    public void save(User user, String token){
        VerificationToken verificationToken = new VerificationToken(user, token);
        verificationToken.setExpiryDate(calculateExpiryDate(EXPIRATION));
        tokenRepository.save(verificationToken);
    }

    //Token Expiration Date
    private LocalDateTime calculateExpiryDate(int expiryDateInMinutes){
        LocalDateTime time = LocalDateTime.now().plusMinutes(expiryDateInMinutes);
        return time;
    }
}
