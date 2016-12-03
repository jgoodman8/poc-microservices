package com.gradox.model.vo;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "public")
public class User {

	public User(Integer idUser) {
		this.id = idUser;
	}

	public User(Integer idUser, String username, String password, boolean enabled) {
		this.id = idUser;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
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

	@Column(name = "enabled", insertable = false)
	private boolean enabled;

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
}
