function submitForm(element, qtyChange, event) {
    // Tìm form gần nhất với class "product-form"
    var form = element.closest('form.product-form');
    if (!form) {
        console.error("Form không được tìm thấy.");
        return;
    }

    // Tìm các input bên trong form
    var qtyChangeInput = form.querySelector('.qtyChangeInput');
    var eventInput = form.querySelector('.eventInput');
    var idInput = form.querySelector('input[name="id"]');

    if (qtyChangeInput && eventInput && idInput) {
        qtyChangeInput.value = qtyChange;
        eventInput.value = event;
        form.submit();
    } else {
        console.error("Input qtyChange hoặc event hoặc id không được tìm thấy.");
    }
}