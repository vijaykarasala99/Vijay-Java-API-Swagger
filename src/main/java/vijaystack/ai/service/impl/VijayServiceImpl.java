package vijaystack.ai.service.impl;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import vijaystack.ai.entity.VijayEntity;
import vijaystack.ai.exception.UserAlreadyExistsException;
import vijaystack.ai.repo.VijayRepository;
import vijaystack.ai.service.VijayService;

@Service
@RequiredArgsConstructor
@Transactional
public class VijayServiceImpl implements VijayService {

	private final VijayRepository repo;
	private final BCryptPasswordEncoder encoder;

	@Override
	public VijayEntity registerUser(VijayEntity user) {

		if (repo.existsByEmail(user.getEmail())) {
			throw new UserAlreadyExistsException("User already exists with this email");
		}

		// hash password
		user.setPazwd(encoder.encode(user.getPazwd()));

		return repo.save(user);
	}

	@Override
	public Optional<VijayEntity> getUserByEmail(String email) {
		return repo.findByEmail(email);
	}

	@Override
	public Optional<VijayEntity> getUserUsingJPQL(String email) {
		return repo.findUserByEmail(email);
	}

	@Override
	public Optional<VijayEntity> getUserUsingNative(String email) {
		return repo.findUserByNativeQuery(email);
	}

	@Override
	@Transactional
	public String verifyUserEmail(String email) {

		Optional<VijayEntity> optionalUser = repo.findByEmail(email);

		if (optionalUser.isEmpty()) {
			return "User not found";
		}

		VijayEntity user = optionalUser.get();

		if (user.isEmailVerified()) {
			return "Email already verified";
		}

		int updated = repo.verifyUserEmail(email);

		if (updated > 0) {
			return "Email verified successfully";
		}

		return "Verification failed";
	}

	@Override
	public VijayEntity getUserUsingProcedure(String email) {
		return repo.getUserByEmailProcedure(email);
	}
}