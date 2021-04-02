package com.bold.sing.music.songmusic.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class BaseEntity implements Serializable {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(name = "id", updatable = false)
	protected UUID id;

	@Column(name = "created_date", updatable = false)
	private Instant createdDate;

	@Column(name = "modified_date")
	private Instant modifiedDate;

	@Version
	@Column(name = "version")
	private long version;

	@PrePersist
	public void setCreationDate() {
		this.createdDate = Instant.now();
		this.modifiedDate = Instant.now();
	}

	@PreUpdate
	public void setChangeDate() {
		this.modifiedDate = Instant.now();
	}

}
