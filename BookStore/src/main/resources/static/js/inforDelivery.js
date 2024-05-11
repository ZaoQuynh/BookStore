function fetchData(url, dropdownId) {
    fetch(url)
        .then(response => response.json())
        .then(data => {
            populateDropdown(data.results, dropdownId);
        })
        .catch(error => console.error('Error fetching data:', error));
}

function populateDropdown(data, dropdownId) {
    const dropdown = document.getElementById(dropdownId);

    dropdown.innerHTML = '';

    data.forEach(item => {
        const option = document.createElement('option');
        option.id = item.district_id || item.province_id || item.ward_id;
        option.value = item.district_name || item.province_name || item.ward_name;
        option.text = item.district_name || item.province_name || item.ward_name;
        dropdown.appendChild(option);
    });
}

fetchData('https://vapi.vnappmob.com/api/province/', 'province');

document.getElementById('province').addEventListener('change', function () {
    const selectedProvinceId = this.options[this.selectedIndex].id;
    if (selectedProvinceId !== '') {
        const districtUrl = `https://vapi.vnappmob.com/api/province/district/${selectedProvinceId}`;
        fetchData(districtUrl, 'district');
    }
});

document.getElementById('district').addEventListener('change', function () {
    const selectedDistrictId = this.options[this.selectedIndex].id;
    if (selectedDistrictId !== '') {
        const wardUrl = `https://vapi.vnappmob.com/api/province/ward/${selectedDistrictId}`;
        fetchData(wardUrl, 'ward');
    }
});

function togglePopup() {
    var overlay = document.querySelector('.overlay');
    var popup = document.querySelector('.infor_delivery-container');

    if (overlay.style.display === 'block') {
        overlay.style.display = 'none';
        popup.style.display = 'none';
    } else {
        overlay.style.display = 'block';
        popup.style.display = 'block';
    }
}

document.addEventListener('DOMContentLoaded', function () {
    var overlay = document.querySelector('.overlay');
    var popup = document.querySelector('.infor_delivery-container');

    overlay.addEventListener('click', function () {
        togglePopup();
    });

    popup.addEventListener('click', function (event) {
        event.stopPropagation(); // Ngăn chặn sự kiện click lan sang overlay
    });
});


function submitForm(formId) {
    var form = document.getElementById(formId);
    if (form) {
        form.submit();
    } else {
        console.error("Form not found.");
    }
}

var selectedInforDeliveryInput = document.querySelector('input[name="selectedInforDelivery"]');
selectedInforDeliveryInput.value = null;

function setSelectedInforDelivery(label) {
    var radios = document.querySelectorAll('input[name="inforDeliveryId"]');

    radios.forEach(function(radio) {
        radio.checked = false;
    });

    var siblingRadio = label.parentNode.querySelector('input[type="radio"]');
    siblingRadio.checked = true;
    selectedInforDeliveryInput.value = siblingRadio.value;
}