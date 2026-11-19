package br.edu.formulario.viacep;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/viacep")
public class ViaCepController {

    private final ViaCepService viaCepService;

    public ViaCepController(ViaCepService viaCepService) {
        this.viaCepService = viaCepService;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<?> buscarCep(@PathVariable String cep) {

        String cepLimpo = cep.replaceAll("\\D", "");

        if (!cepLimpo.matches("\\d{8}")) {
            return ResponseEntity.badRequest()
                    .body("O CEP deve conter 8 números.");
        }

        try {
            ViaCepEndereco endereco =
                    viaCepService.buscarPorCep(cepLimpo);

            if ("true".equalsIgnoreCase(endereco.erro())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("CEP não encontrado.");
            }

            return ResponseEntity.ok(endereco);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body("Não foi possível consultar o ViaCEP.");
        }
    }
}
