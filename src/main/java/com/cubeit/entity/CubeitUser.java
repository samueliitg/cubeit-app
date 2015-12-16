package com.cubeit.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cubeit_user", catalog = "cubeit")
public class CubeitUser implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String city;
	private Set<Content> ownedContents = new HashSet<Content>();
	private Set<Cube> ownedCubes = new HashSet<Cube>();
	private Set<UserSharedContent> sharedContents = new HashSet<UserSharedContent>();
	private Set<UserSharedCube> sharedCubes = new HashSet<UserSharedCube>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false)
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "city", nullable = false)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "owner", cascade = { CascadeType.ALL })
	public Set<Content> getOwnedContents() {
		return ownedContents;
	}

	public void setOwnedContents(Set<Content> ownedContents) {
		this.ownedContents = ownedContents;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "owner", cascade = { CascadeType.ALL })
	public Set<Cube> getOwnedCubes() {
		return ownedCubes;
	}

	public void setOwnedCubes(Set<Cube> ownedCubes) {
		this.ownedCubes = ownedCubes;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = { CascadeType.ALL })
	public Set<UserSharedContent> getSharedContents() {
		return sharedContents;
	}

	public void setSharedContents(Set<UserSharedContent> sharedContents) {
		this.sharedContents = sharedContents;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = { CascadeType.ALL })
	public Set<UserSharedCube> getSharedCubes() {
		return sharedCubes;
	}

	public void setSharedCubes(Set<UserSharedCube> sharedCubes) {
		this.sharedCubes = sharedCubes;
	}
}
