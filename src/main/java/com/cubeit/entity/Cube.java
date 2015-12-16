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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cube", catalog = "cubeit")
public class Cube implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Set<CubeContentMapping> cubeContentMappings = new HashSet<CubeContentMapping>();
	private CubeitUser owner;

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

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "cube", cascade = { CascadeType.ALL })
	public Set<CubeContentMapping> getCubeContentMappings() {
		return cubeContentMappings;
	}

	public void setCubeContentMappings(Set<CubeContentMapping> cubeContentMappings) {
		this.cubeContentMappings = cubeContentMappings;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "owner_user_id", nullable = false)
	public CubeitUser getOwner() {
		return owner;
	}

	public void setOwner(CubeitUser owner) {
		this.owner = owner;
	}

	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (!(o instanceof Cube))
			return false;

		Cube otherContent = (Cube) o;
		return this.id == otherContent.id;
	}

	public int hashCode() {
		return (this.id == null) ? 0 : this.id;
	}
}
