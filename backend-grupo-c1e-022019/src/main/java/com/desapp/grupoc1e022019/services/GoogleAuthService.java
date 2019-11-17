package com.desapp.grupoc1e022019.services;

import com.desapp.grupoc1e022019.model.GoogleToken;
import com.desapp.grupoc1e022019.persistence.GoogleTokenDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Scope(value = "session")
@Component(value = "googleAuthService")
public class GoogleAuthService {

    @Autowired
    private GoogleTokenDAO googleTokenDAO = new GoogleTokenDAO();


    @Transactional
    public void saveOrUpdateGoogleToken(GoogleToken googleAuth) {
        googleTokenDAO.saveOrUpdate(googleAuth);
    }

    public boolean existGoogleId(String googleId) {
        return googleTokenDAO.existsGoogleId(googleId);
    }

    public boolean checkExistAuthToken(GoogleToken googleToken) {

        return googleTokenDAO.checkExistGoogleIdAndAuthToken(googleToken.getGoogleId(),googleToken.getAccessToken());
    }

    @Transactional
    public void logoutGoogleToken(GoogleToken googleToken) {
        googleTokenDAO.deleteAuthTokenByGoogleId(googleToken.getGoogleId());
    }
}
