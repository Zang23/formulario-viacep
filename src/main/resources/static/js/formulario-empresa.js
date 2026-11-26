
document.addEventListener("DOMContentLoaded", () => {

    // Navegação entre etapas
    const etapas = document.querySelectorAll(".etapa");
    let etapaAtual = 0;

    function mostrarEtapa() {
        etapas.forEach((etapa, index) => {
            etapa.style.display = index === etapaAtual ? "flex" : "none";
        });
    }

    document.querySelectorAll(".proximo").forEach(botao => {
        botao.addEventListener("click", () => {
            if (etapaAtual < etapas.length - 1) {
                etapaAtual++;
                mostrarEtapa();
            }
        });
    });

    document.querySelectorAll(".voltar").forEach(botao => {
        botao.addEventListener("click", () => {
            if (etapaAtual > 0) {
                etapaAtual--;
                mostrarEtapa();
            }
        });
    });

    mostrarEtapa();

    // Consulta de CEP
    const botaoBuscarCep = document.getElementById("buscarCep");
    const campoCep = document.getElementById("cep");

    if (botaoBuscarCep && campoCep) {

        botaoBuscarCep.addEventListener("click", async () => {

            const cep = campoCep.value.replace(/\D/g, "");

            if (cep.length !== 8) {
                alert("Informe um CEP válido com 8 dígitos.");
                return;
            }

            try {
                const resposta = await fetch(
                    `https://viacep.com.br/ws/${cep}/json/`
                );

                if (!resposta.ok) {
                    throw new Error("Não foi possível consultar o CEP.");
                }

                const dados = await resposta.json();

                if (dados.erro) {
                    alert("CEP não encontrado.");
                    return;
                }

                document.getElementById("localidade").value =
                    dados.localidade || "";

                document.getElementById("logradouro").value =
                    dados.logradouro || "";

                document.getElementById("bairro").value =
                    dados.bairro || "";

                document.getElementById("uf").value =
                    dados.uf || "";

            } catch (erro) {
                console.error("Erro ao consultar ViaCEP:", erro);
                alert("Ocorreu um erro ao consultar o CEP.");
            }
        });
    }
});
