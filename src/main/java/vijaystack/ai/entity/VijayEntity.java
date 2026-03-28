package vijaystack.ai.entity;

import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
// @ToString(exclude = "password")
@Table(name = "users", indexes = { @Index(name = "idx_user_email", columnList = "email") })
public class VijayEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vid;

	@Email
	@NotBlank
	@Column(nullable = false, unique = true, length = 120)
	private String email;

	@NotBlank
	@Size(min = 60, max = 500) // BCrypt hash size
	@Column(nullable = false)
	private String pazwd;

	@Column(nullable = false)
	private boolean emailVerified = false;

	// Field not persisted to the database.
	// When the value is temporary, calculated, or used only in application logic.
	// OTP exists in the Java object but no column is created in the DB.
	@Transient
	private String otp;

	// Used inside an entity to include an @Embeddable object.
	// No separate address table is created.
	@Embedded
	private Address address;

	// ROLE MANAGEMENT
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleEntity> roles;

	// AUDIT FIELDS
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime updatedAt;

	/*
	 * @Transient is used for fields that should not be persisted in the database.
	 * 
	 * @Embeddable defines a reusable value type whose fields are stored in the same
	 * table as the entity.
	 * 
	 * @Embedded is used inside an entity to include an @Embeddable object.
	 * 
	 * @ToString(exclude = "password") Exclude sensitive or heavy fields when
	 * generating toString(). If you use exclude Output will not show password.
	 * Multiple fields exclusion: @ToString(exclude = {"password","roles"})
	 * 
	 * @ToString.Include 
	 * private String email; 
	 * 
	 * Prevent circular references : 
	 * @ToString.Exclude 
	 * private Set<RoleEntity> roles;
	 * 
	 */
}
