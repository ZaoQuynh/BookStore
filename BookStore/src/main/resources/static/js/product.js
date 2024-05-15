
    // JavaScript code for filtering products
    document.addEventListener('DOMContentLoaded', function () {
        // Get all radio buttons for category and price
        var categoryRadios = document.querySelectorAll('input[name="category"]');
        var priceRadios = document.querySelectorAll('input[name="price"]');

        categoryRadios.forEach(function (radio) {
            radio.addEventListener('change', function () {
                console.log("Selected category: " + this.value);
            });
        });

        priceRadios.forEach(function (radio) {
            radio.addEventListener('change', function () {
                console.log("Selected price: " + this.value);
            });
        });

        // Attach event listener for category radios
        categoryRadios.forEach(function (radio) {
            radio.addEventListener('change', function () {
                filterProducts();
            });
        });

        // Attach event listener for price radios
        priceRadios.forEach(function (radio) {
            radio.addEventListener('change', function () {
                filterProducts();
            });
        });



        function filterProducts() {
            var selectedCategory = document.querySelector('input[name="category"]:checked').value;
            var selectedPrice = document.querySelector('input[name="price"]:checked').value;
            // Loop through all products and hide/show based on selected category and price
            var products = document.querySelectorAll('.col-md-2');



            products.forEach(function (product) {

                var category = product.getAttribute('data-product-genre');
                var price = parseFloat(product.getAttribute('data-product-price'));

                var categoryMatch = selectedCategory === 'all' || category === selectedCategory;
                var priceMatch = selectedPrice === 'all' || (selectedPrice === '0-100' && price <= 100) || (selectedPrice === '100-150' && price > 100 && price <= 150) || (selectedPrice === '150-300' && price > 1500 && price <= 300) || (selectedPrice === '300-500' && price > 300 && price <= 500) || (selectedPrice === '500-700' && price > 500 && price <= 700) || (selectedPrice === '700-plus' && price > 700);

                if (categoryMatch && priceMatch) {
                    product.style.display = 'block';
                } else {
                    product.style.display = 'none';
                }

            });
        }

    });
