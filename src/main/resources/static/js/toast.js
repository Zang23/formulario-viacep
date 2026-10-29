document.querySelectorAll(".toast").forEach((toast) => {
    let temporizador;

    function fecharToast() {
        if (toast.classList.contains("saindo")) {
            return;
        }

        clearTimeout(temporizador);
        toast.classList.add("saindo");

        toast.addEventListener("animationend", () => {
            toast.remove();
        }, { once: true });
    }

    toast.querySelector(".toast-fechar")
        .addEventListener("click", fecharToast);

    temporizador = setTimeout(fecharToast, 5000);
});
