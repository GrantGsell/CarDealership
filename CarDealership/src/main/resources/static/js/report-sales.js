$(document).ready(() => {
    searchSalesReports();
});

const searchSalesReports = () => {
    $('#search').click(event => {
        event.preventDefault();
        
        // clear result div
        $('#resultContainer').addClass('d-none');
        
        // request with JSON type
        $.ajax({
            url: 'http://localhost:8080/report/sales',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                userId: $('#selectUser').val(),
                fromDate: $('#selectFromDate').val(),
                toDate: $('#selectToDate').val()
            }),
            success: data => displaySearchResult(data),
            error: () => {
                console.log('error');
            }
        });
    });
};

const displaySearchResult = data => {
    $('#resultTable').empty();
    (data.length > 0) & $('#resultContainer').removeClass('d-none');
    
    data.forEach(row => {
        const newElement = 
                `<tr>
                    <td>${row.userName}</td>
                    <td class="text-center">$${row.totalSalesAmount}</td>
                    <td class="text-center">${row.totalCount}</td>
                </tr>`;
        $('#resultTable').append(newElement);
    });
};
