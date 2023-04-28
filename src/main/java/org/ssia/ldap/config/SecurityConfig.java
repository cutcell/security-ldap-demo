package org.ssia.ldap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.ldap.EmbeddedLdapServerContextSourceFactoryBean;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.web.SecurityFilterChain;
import org.ssia.ldap.security.ExternalAuthoritiesPopulator;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest()
                        .fullyAuthenticated())
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    /**
     * Embedded LDAP server config
     */
    @Bean
    public EmbeddedLdapServerContextSourceFactoryBean contextSourceFactoryBean() {
        EmbeddedLdapServerContextSourceFactoryBean contextSourceFactoryBean =
                EmbeddedLdapServerContextSourceFactoryBean.fromEmbeddedLdapServer();
        contextSourceFactoryBean.setPort(33389);
        contextSourceFactoryBean.setLdif("classpath:test-users.ldif");
        return contextSourceFactoryBean;
    }

    /**
     * Setup LDAP authentication provider
     */
    @Bean
    public AuthenticationManager ldapAuthenticationManager(BaseLdapPathContextSource contextSource) {
        LdapBindAuthenticationManagerFactory factory = new LdapBindAuthenticationManagerFactory(contextSource);
        factory.setUserDnPatterns("uid={0},ou=people");
        factory.setLdapAuthoritiesPopulator(new ExternalAuthoritiesPopulator());
        return factory.createAuthenticationManager();
    }

}
