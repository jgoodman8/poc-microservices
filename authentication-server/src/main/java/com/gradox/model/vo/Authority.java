package com.gradox.model.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;
//import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "authorities", schema = "public")
public class Authority {

	public Authority() {
	}

//	public Authority(User user, String authority) {
//		this.user = user;
//		this.authority = authority;
//	}

	@Id
//	@SequenceGenerator(name = "authorities_id_seq", sequenceName = "authorities_id_seq", allocationSize = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authorities_id_seq")
	@Column(name = "id")
	private Integer id;
	
//	@OneToOne	// (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinColumn(name = "username")
////	@Column(name = "username")
//	private User user;

	@Column(name = "authority")
	private String authority;

//	public User getUser() {
//		return user;
//	}
//
//	public void setUsername(User user) {
//		this.user = user;
//	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
