$(document).ready(function () {

    $('.nBtn, .table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text(); //return New or Edit

        if (text === 'Edit') {
            $.get(href, function (climate, status) {
                $('.myForm #id').val(climate.id);
                $('.myForm #stationName').val(climate.stationName);
                $('.myForm #province').val(climate.province);
            });
            $('.myForm #exampleModal').modal();
        } else {
            $('.myForm #id').val('');
            $('.myForm #name').val('');
            $('.myForm #capital').val('');
            $('.myForm #exampleModal').modal();
        }
    });

    $('.table .delBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#myModal #delRef').attr('href', href);
        $('#myModal').modal();
    });
});