package com.cubeit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "content", catalog = "cubeit")
public class Content implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String url;
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

	@Column(name = "url", nullable = false)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
		if (!(o instanceof Content))
			return false;

		Content otherContent = (Content) o;
		return this.id == otherContent.id;
	}

	public int hashCode() {
		return (this.id == null) ? 0 : this.id;
	}
}
