$(document).ready(() => {
    editVehicle();
});


const editVehicle = () => {
    // Listen for save button click 
    $("#saveBtn").click(event => {
        event.preventDefault();

       // Request with JSON, AJAX
       $.ajax({
           url: "http://localhost:8080/admin/editvehicle/{vin}",
           type: "POST",
           contentType: "application/json",
           data: JSON.stringify({
               make: $("#inputMake").val(),
               model: $("#inputModel").val(),
               type: $("#inputType").val(),
               bodyStyle: $("#inputBodyStyle").val(),
               year: $("#inputYear").val(),
               transmission: $("#inputTransmission").val(),
               color: $("#inputColor").val(),
               interior: $("#inputInterior").val(),
               mileage: $("#inputMileage").val(),
               vin: $("#inputVIN").val(),
               msrp: $("#inputMSRP").val(),
               salesPrice: $("#inputSalesPrice").val(),
               description: $("#inputDescription").val(),
               picture: $("#inputPicture").val(),
               isFeatured: $("#isFeatured").is(":checked")
           }),
           sucess: () => {
               console.log("success");
           },
           error: () =>{
               console.log("error");
           }
       });
    });
};