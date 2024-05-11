function selectedPaymentMethod(label) {
    var radios = label.parentNode.querySelectorAll('input[type="radio"]');
    radios.forEach(function(radio) {
        radio.checked = false;
    });
    var siblingRadio = label.previousElementSibling;
    if (siblingRadio && siblingRadio.type === 'radio') {
        siblingRadio.checked = true;
    }
}

function submitForm(formId) {
    var form = document.getElementById(formId);
    if (form) {
        form.submit();
    } else {
        console.error("Form not found.");
    }
}