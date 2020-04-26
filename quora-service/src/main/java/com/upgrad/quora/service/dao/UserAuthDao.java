package com.upgrad.quora.service.dao;

import com.upgrad.quora.service.entity.UserAuthEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class UserAuthDao {

    @PersistenceContext
    private EntityManager entityManager;


    public UserAuthEntity getUserAuthByToken(final String accessToken) {
        try {
            return entityManager.createNamedQuery("userAccessByToken", UserAuthEntity.class).setParameter("accessToken", accessToken).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public UserAuthEntity createAuthToken(final UserAuthEntity userAuthEntity) {
        entityManager.persist(userAuthEntity);
        return userAuthEntity;
    }

    public void updateUserAuth(final UserAuthEntity updatedUserAuthEntity) {
        entityManager.merge(updatedUserAuthEntity);
    }
}
