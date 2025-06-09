package com.seungse.amadda.accountservice.adapter.out.persistence;

import com.seungse.amadda.accountservice.application.port.out.AccountRegisterOutPortV1;
import com.seungse.amadda.accountservice.domain.Account;
import com.seungse.amadda.accountservice.infrastructor.annotations.OutPortAdapter;
import com.seungse.amadda.accountservice.infrastructor.config.KeyCloakProperties;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * AccountRegisterOutPortV1Adapter
 * <p>
 * 계정 등록 아웃포트 어댑터
 * </p>
 * <p>
 * 계정 등록을 위한 아웃포트 어댑터입니다.
 * </p>
 *
 * @author seungse
 */
@OutPortAdapter
@RequiredArgsConstructor
public class AccountRegisterOutPortV1Adapter implements AccountRegisterOutPortV1 {

    private final Keycloak keycloak;
    private final KeyCloakProperties keyCloakProperties;

    /**
     * keycloak 계정 생성
     *
     * @param account  계정정보
     * @param password 비밀번호
     * @return
     */
    @Override
    public Optional<Account> createAccount(Account account, String password) {
        AccountEntity accountEntity = AccountEntity.newEntityFrom(account);

        RealmResource realm = keycloak.realm(keyCloakProperties.getRealm());
        UsersResource usersResource = realm.users();

        try (Response result = createUser(accountEntity, usersResource)) {
            if (result.getStatusInfo().toEnum() == Response.Status.CREATED) {
                CredentialRepresentation credential = new CredentialRepresentation();
                credential.setType(CredentialRepresentation.PASSWORD);
                credential.setValue(password);

                String userId = CreatedResponseUtil.getCreatedId(result);
                UserResource userResource = usersResource.get(userId);
                userResource.resetPassword(credential);
            }
        }


        return Optional.of(accountEntity.toDomain());
    }

    private Response createUser(AccountEntity accountEntity, UsersResource usersResource) {
        UserRepresentation user = new UserRepresentation();
        user.setEmail(accountEntity.getEmail());
        user.setEnabled(true);

        Map<String, List<String>> attributes = new HashMap<>();
        attributes.put("phone", List.of(accountEntity.getPhone()));
        user.setAttributes(attributes);

        return usersResource.create(user);
    }

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

}
