package vijaystack.ai.controller;

import static vijaystack.ai.constants.AppConstants.*;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import vijaystack.ai.entity.VijayEntity;
import vijaystack.ai.service.VijayService;

@RestController
@RequestMapping(API_VERSION_V1 + USERS)
@RequiredArgsConstructor
public class VijayController {

    private final VijayService service;

    @PostMapping(REGISTER)
    public ResponseEntity<VijayEntity> registerUser(@RequestBody VijayEntity user) {
        return ResponseEntity.ok(service.registerUser(user));
    }

    @GetMapping(EMAIL)
    public ResponseEntity<Optional<VijayEntity>> getUserByEmail(@RequestParam String email) {
        return ResponseEntity.ok(service.getUserByEmail(email));
    }

    @GetMapping(JPQL)
    public ResponseEntity<Optional<VijayEntity>> getUserUsingJPQL(@RequestParam String email) {
        return ResponseEntity.ok(service.getUserUsingJPQL(email));
    }

    @GetMapping(NATIVE)
    public ResponseEntity<Optional<VijayEntity>> getUserUsingNative(@RequestParam String email) {
        return ResponseEntity.ok(service.getUserUsingNative(email));
    }

    @PutMapping(VERIFY)
    public ResponseEntity<String> verifyEmail(@RequestParam String email) {
        return ResponseEntity.ok(service.verifyUserEmail(email));
    }

    @GetMapping(PROCEDURE)
    public ResponseEntity<VijayEntity> getUserUsingProcedure(@RequestParam String email) {
        return ResponseEntity.ok(service.getUserUsingProcedure(email));
    }
}