function submitForm(formId) {
    var form = document.getElementById(formId);
    if (form) {
        form.submit();
    } else {
        console.error("Form not found.");
    }
}