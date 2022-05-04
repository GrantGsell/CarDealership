$(document).ready(() => {
    searchNewVehicles();
});

const searchNewVehicles = () => {
    $('#search').click(event => {
        event.preventDefault();
        
        // clear result div
        $('#resultContainer').addClass('d-none');
        
        // request with JSON type
        $.ajax({
            url: 'http://localhost:8080/inventory/search',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                type: $('#type').val(),
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
    $('#resultList').empty();
    (data.length > 0) & $('#resultContainer').removeClass('d-none');
    
    data.forEach(vehicle => {
        const newElement = createResultCardDiv(vehicle);
        $('#resultList').append(newElement);
    });
};

const createResultCardDiv = vehicle => 
    `<div class="card mb-3">
        <h5 class="mx-5 mt-2">${vehicle.carYear} ${vehicle.make.nameMake} ${vehicle.model.nameModel}</h5>
        <div class="row g-0">
            <div class="col-md-3 text-center">
                  <img src="${vehicle.pictureUrl}" class="img-fluid rounded-start" style="width: 160px"></img>
            </div>
            <div class="col-md-9">
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-2" style="text-align: right"><strong>Body Style :</strong></div>
                        <div class="col-md-2">${vehicle.style.nameStyle}</div>
                        <div class="col-md-2" style="text-align: right"><strong>Interior :</strong></div>
                        <div class="col-md-2">${vehicle.interior.nameColor}</div>
                        <div class="col-md-2" style="text-align: right"><strong>Sales Price :</strong></div>
                        <div class="col-md-2">$${vehicle.salePrice}</div>
                    </div>
                    <div class="row">
                        <div class="col-md-2" style="text-align: right"><strong>Trans :</strong></div>
                        <div class="col-md-2">${vehicle.transmission.transmissionName}</div>
                        <div class="col-md-2" style="text-align: right"><strong>Mileage :</strong></div>
                        <div class="col-md-2">${vehicle.mileage}</div>
                        <div class="col-md-2" style="text-align: right"><strong>MSRP :</strong></div>
                        <div class="col-md-2">$${vehicle.msrp}</div>
                    </div>
                    <div class="row">
                        <div class="col-md-2" style="text-align: right"><strong>Color :</strong></div>
                        <div class="col-md-2">${vehicle.color.nameColor}</div>
                        <div class="col-md-2" style="text-align: right"><strong>VIN # :</strong></div>
                        <div class="col-md-6">${vehicle.vin}</div>
                    </div>
                    <div class="row justify-content-end mt-3">
                        <div class="col-md-auto">
                            <a href="/inventory/details/${vehicle.vin}" type="button" class="btn btn-outline-dark px-5">Details</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>`;