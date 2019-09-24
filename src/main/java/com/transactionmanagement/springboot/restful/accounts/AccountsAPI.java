package  com.transactionmanagement.springboot.restful.accounts;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/accounts")
@Slf4j
@RequiredArgsConstructor
public class AccountsAPI {
    private final AccountsService accountsService;

    @GetMapping
    public ResponseEntity<List<Accounts>> findAll() {
        return ResponseEntity.ok(accountsService.findAll());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Accounts accounts) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountsService.save(accounts));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Accounts> findById(@PathVariable Long id) {
        Optional<Accounts> details = accountsService.findById(id);
        if (!details.isPresent()) {
            log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(details.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Accounts> update(@PathVariable Long id, @Valid @RequestBody Accounts account) {
        if (!accountsService.findById(id).isPresent()) {
            log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.accepted().body(accountsService.save(account));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!accountsService.findById(id).isPresent()) {
            log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        accountsService.deleteById(id);

        return ResponseEntity.accepted().build();
    }
}