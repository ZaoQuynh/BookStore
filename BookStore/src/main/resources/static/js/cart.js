function submitForm(formId, qtyChange, event) {
    var form = document.getElementById(formId);
    var qtyChangeInput = document.getElementById('qtyChangeInput');
    var eventInput = document.getElementById('event');

    if (form && qtyChangeInput) {
        qtyChangeInput.value = qtyChange;
        eventInput.value = event;
        form.submit();
    } else {
        console.error("Form or qtyChange input not found.");
    }
}
