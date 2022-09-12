package pl.coderslab.charity.service.verification_token;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.domain.User;
import pl.coderslab.charity.domain.VerificationToken;
import pl.coderslab.charity.repository.VerificationTokenRepository;

import java.sql.Timestamp;
import java.util.Calendar;

@Service
@Transactional
public class VerificationTokenServiceImpl implements VerificationTokenService{
    private final VerificationTokenRepository tokenRepository;

    public VerificationTokenServiceImpl(VerificationTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

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
    private Timestamp calculateExpiryDate(int expiryDateInMinutes){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, expiryDateInMinutes);
        return new Timestamp(cal.getTime().getTime());
    }
}
