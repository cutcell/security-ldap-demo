package org.ssia.ldap.security;

import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class ExternalAuthoritiesPopulator implements LdapAuthoritiesPopulator {

    // Emulates external service data
    private static final Map<String, String> USERS_ROLES = new HashMap<>();
    static {
        USERS_ROLES.put("admin", "read,write,all");
        USERS_ROLES.put("user", "read,write");
    }

    @Override
    public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations userData, String username) {
        String roles = USERS_ROLES.getOrDefault(username, "");
        if (roles.isEmpty()) {
            return Collections.emptyList();
        }

        return Stream.of(roles.split(","))
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

}
