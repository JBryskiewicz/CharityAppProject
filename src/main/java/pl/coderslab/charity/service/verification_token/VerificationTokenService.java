package pl.coderslab.charity.service.verification_token;

import pl.coderslab.charity.domain.User;
import pl.coderslab.charity.domain.VerificationToken;

public interface VerificationTokenService {
    VerificationToken findByToken(String token);
    void save(User user, String token);
}
