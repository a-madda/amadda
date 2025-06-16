package com.seungse.amadda.accountservice.adapter.out.persistence;

import com.seungse.amadda.accountservice.application.port.out.AccountValidationOutPortV1;
import com.seungse.amadda.accountservice.infrastructor.annotations.OutPortAdapter;
import com.seungse.amadda.accountservice.infrastructor.config.KeyCloakProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

@Slf4j
@OutPortAdapter
@RequiredArgsConstructor
public class AccountValidationOutPortV1Adapter implements AccountValidationOutPortV1 {

    private final Keycloak keycloak;
    private final KeyCloakProperties keyCloakProperties;

    /**
     * 계정 존재여부 확인
     *
     * @param email 이메일
     * @return boolean 존재여부 결과
     */
    @Override
    public boolean isExistAccount(String email) {
        List<UserRepresentation> search = keycloak.realm(keyCloakProperties.getRealm()).users().searchByEmail(email, true);

        return !search.isEmpty();
    }

    @Override
    public boolean isNotExistAccount(String email) {
        List<UserRepresentation> search = keycloak.realm(keyCloakProperties.getRealm()).users().searchByEmail(email, true);
        List<UserRepresentation> userRepresentations = keycloak.realm(keyCloakProperties.getRealm()).users().searchByUsername(email, true);
        return search.isEmpty() && userRepresentations.isEmpty();
    }
}
