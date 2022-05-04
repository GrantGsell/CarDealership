$(document).ready(function () {
    $('.yearpicker').datepicker({
        minViewMode: 2,
        format: 'yyyy'
    });
    
    $('.datepicker').datepicker({
        dateFormat: 'yy-mm-dd',
        changeMonth: true,
        changeYear: true
    });    
});
