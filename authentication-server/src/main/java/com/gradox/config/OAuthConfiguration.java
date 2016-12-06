package com.gradox.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

//import com.netflix.governator.annotations.binding.Primary;

/**
 * The Class OAuth2Config defines the authorization server that would
 * authenticate the user and define the client that seeks authorization on the
 * resource owner's behalf.
 */
@Configuration
@EnableAuthorizationServer
public class OAuthConfiguration extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private DataSource dataSource;

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	/**
	 * The OAuth2 tokens are defined in the datasource defined in the
	 * <code>auth-server.yml</code> file stored in the Spring Cloud config
	 * github repository.
	 * 
	 * Devuelve una instancia de almacenamiento de tokens guardados en base de
	 * datos
	 * 
	 * @return
	 */
	// @Bean
	// public JdbcTokenStore tokenStore() {
	// return new JdbcTokenStore(dataSource);
	// }

	// Modificación para usar JWT
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	// Añadido para usar JWT
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey("123");
		return converter;
	}

	/**
	 * Services for issuing and storing authorization codes.
	 * 
	 * @return
	 */
	@Bean
	protected AuthorizationCodeServices authorizationCodeServices() {
		return new JdbcAuthorizationCodeServices(dataSource);
	}

	/**
	 * Asigna el BCryptPasswordEncoder como password encoder en la config de la
	 * autenticación
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.passwordEncoder(passwordEncoder);
	}

	/**
	 * We set our authorization storage feature specifying that we would use the
	 * JDBC store for token and authorization code storage.<br>
	 * <br>
	 * 
	 * We also attach the {@link AuthenticationManager} so that password grants
	 * can be processed.
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authorizationCodeServices(authorizationCodeServices()).authenticationManager(authenticationManager)
				.tokenStore(tokenStore()).approvalStoreDisabled();
		// endpoints.tokenStore(tokenStore())
		// .accessTokenConverter(accessTokenConverter())
		// .authenticationManager(authenticationManager)
	}

	/*
	 * @Bean
	 * 
	 * @Primary public DefaultTokenServices tokenServices() {
	 * DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
	 * defaultTokenServices.setTokenStore(tokenStore());
	 * defaultTokenServices.setSupportRefreshToken(true); return
	 * defaultTokenServices; }
	 */

	/**
	 * Setup the client application which attempts to get access to user's
	 * account after user permission.
	 * 
	 * endpoints /oauth/(token authorize)
	 * 
	 * ????
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		// Filtra por cliend_id con el que se realice la llamada
		// secret para aceptar la petición
		clients.jdbc(dataSource).passwordEncoder(passwordEncoder).withClient("client")
				.authorizedGrantTypes("authorization_code", "client_credentials", "refresh_token", "password",
						"implicit") // tipos de autenticación permitidos
				.authorities("ROLE_CLIENT").resourceIds("apis").scopes("read").secret("secret")
				.accessTokenValiditySeconds(300);
	}

	/**
	 * Configure the {@link AuthenticationManagerBuilder} with initial
	 * configuration to setup users.
	 * 
	 * @author anilallewar
	 *
	 */
	@Configuration
	@Order(Ordered.LOWEST_PRECEDENCE - 20)
	protected static class AuthenticationManagerConfiguration extends GlobalAuthenticationConfigurerAdapter {

		@Autowired
		private DataSource dataSource;

		/**
		 * Setup 2 users with different roles
		 */
		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			// @formatter:off
//			auth.jdbcAuthentication().dataSource(dataSource).withUser("dave").password("secret").roles("USER");
//			auth.jdbcAuthentication().dataSource(dataSource).withUser("anil").password("password").roles("ADMIN");
			// @formatter:on
		}

		@Autowired
		public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
			// @formatter:off
			// auth.inMemoryAuthentication().withUser("john").password("123").roles("USER").and().withUser("tom")
			// .password("111").roles("ADMIN");
			auth.jdbcAuthentication().dataSource(dataSource)
					.usersByUsernameQuery("select username, password, enabled from users where username=?")
					.authoritiesByUsernameQuery("select username, authority from users where username=?");
			// @formatter:on
		}
	}

}