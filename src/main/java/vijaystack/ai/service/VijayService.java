package vijaystack.ai.service;

import java.util.Optional;

import vijaystack.ai.entity.VijayEntity;

public interface VijayService {

    // Register new user
    VijayEntity registerUser(VijayEntity user);

    // Login / fetch user
    Optional<VijayEntity> getUserByEmail(String email);

    // Example JPQL usage
    Optional<VijayEntity> getUserUsingJPQL(String email);

    // Example Native query usage
    Optional<VijayEntity> getUserUsingNative(String email);

    // Verify email
    String verifyUserEmail(String email);

    // Fetch using stored procedure
    VijayEntity getUserUsingProcedure(String email);
}