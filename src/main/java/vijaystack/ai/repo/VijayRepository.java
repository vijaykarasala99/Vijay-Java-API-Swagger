package vijaystack.ai.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vijaystack.ai.entity.VijayEntity;

@Repository
public interface VijayRepository extends JpaRepository<VijayEntity, Long> {

	// Find by email (used for login)
	Optional<VijayEntity> findByEmail(String email);

	boolean existsByEmail(String email);

	// =========================
	// JPQL QUERY
	// =========================
	@Query("SELECT v FROM VijayEntity v WHERE v.email = :email")
	Optional<VijayEntity> findUserByEmail(@Param("email") String email);

	// =========================
	// NATIVE QUERY (MySQL)
	// =========================
	@Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
	Optional<VijayEntity> findUserByNativeQuery(@Param("email") String email);

	// =========================
	// UPDATE QUERY
	// =========================
	@Modifying
	@Transactional
	@Query(value = "UPDATE users SET email_verified = true WHERE email = :email", nativeQuery = true)
	int verifyUserEmail(@Param("email") String email);

	// =========================
	// STORED PROCEDURE CALL
	// =========================
	@Procedure(procedureName = "get_user_by_email")
	VijayEntity getUserByEmailProcedure(@Param("email_param") String email);

	/*
	 * @Procedure(name="...") references a NamedStoredProcedureQuery defined in the
	 * entity, while @Procedure(procedureName="...") directly calls the database
	 * stored procedure. If the entity mapping is missing, using name causes a
	 * runtime failure.
	 */

}
