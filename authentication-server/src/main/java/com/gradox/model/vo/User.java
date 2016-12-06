package com.gradox.model.vo;

//import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "public")
public class User {

	public User() {
	}

	public User(Integer idUser) {
		this.id = idUser;
	}

	public User(Integer idUser, String username, String password, boolean enabled) {
		this.id = idUser;
//		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

//	public User(Integer id, String username, String password, boolean enabled, Authority role) {
//		this.id = id;
////		this.username = username;
//		this.password = password;
//		this.enabled = enabled;
//		this.authority = role;
//		}
	
	public User(Integer id, String username, String password, boolean enabled, String authority) {
	this.id = id;
	this.username = username;
	this.password = password;
	this.enabled = enabled;
	this.authority = authority;
}	

	@Id
	@SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
	@Column(name = "id", updatable = false)
	private Integer id;

	@Basic(optional = false)
	@Column(name = "username")
	private String username;

	@Basic(optional = false)
	@Column(name = "password")
	private String password;

	@Basic(optional = true)
	@Column(name = "enabled")
	private boolean enabled;

//	@OneToOne(mappedBy="authorities", cascade=CascadeType.ALL, fetch=FetchType.LAZY) //(cascade=CascadeType.ALL, mappedBy="username")
//	@JoinTable(name="authorities")
	@Column(name="authority")
	private String authority;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", authority=" + authority + "]";
	}	
}
