$(document).ready(() => {
    searchSalesVehicles();
});

const searchSalesVehicles = () => {
    $('#search').click(event => {
        event.preventDefault();
        
        // request with JSON type
        $.ajax({
            url: 'http://localhost:8080/sales/search',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                keyword: $('#keyword').val(),
                minPrice: $('#minPrice').val(),
                maxPrice: $('#maxPrice').val(),
                minYear: $('#minYear').val(),
                maxYear: $('#maxYear').val()
            }),
            success: data => displaySearchResult(data),
            error: () => {
                console.log('error');
            }
        });
    });
};

const displaySearchResult = data => {
    console.log(data);
};
