
package br.edu.formulario.viacep;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ViaCepService {

    private final RestClient restClient;

    public ViaCepService() {
        this.restClient = RestClient.builder()
                .baseUrl("https://viacep.com.br")
                .build();
    }

    public ViaCepEndereco buscarPorCep(String cep) {
        return restClient.get()
                .uri("/ws/{cep}/json/", cep)
                .retrieve()
                .body(ViaCepEndereco.class);
    }
}
