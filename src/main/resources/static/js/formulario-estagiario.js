const etapas = document.querySelectorAll(".etapa");

// Procura um campo inválido e identifica em qual etapa ele está.
const primeiroCampoInvalido = document.querySelector(".campo-invalido-formularios");

let etapaAtual = 0;

if (primeiroCampoInvalido) {
    const etapaComErro = primeiroCampoInvalido.closest(".etapa");

    if (etapaComErro) {
        etapaAtual = Array.from(etapas).indexOf(etapaComErro);
    }
}

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
document.getElementById("buscarCep")
    .addEventListener("click", async function () {

        const cep = document.getElementById("cep").value
            .replace(/\D/g, "");

        if (cep.length !== 8) {
            alert("Digite um CEP válido com 8 números.");
            return;
        }

        try {
            const resposta = await fetch(`/viacep/${cep}`);

            if (!resposta.ok) {
                const mensagem = await resposta.text();
                alert(mensagem);
                return;
            }

            const endereco = await resposta.json();

            document.getElementById("localidade").value =
                endereco.localidade ?? "";

            document.getElementById("logradouro").value =
                endereco.logradouro ?? "";

            document.getElementById("bairro").value =
                endereco.bairro ?? "";

            document.getElementById("uf").value =
                endereco.uf ?? "";

        } catch (erro) {
            alert("Não foi possível consultar o CEP.");
            console.error(erro);
        }
    });
