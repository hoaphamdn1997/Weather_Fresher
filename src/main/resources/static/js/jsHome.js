// ẩn đi showmore
$(".card-body-click").click(function () {
    var cityName = $(this).closest(".card").attr('data-id');
    window.location = "/home-weather/detailts/" + cityName;
});

var request = $.ajax({
    url: "/process-size",
    type: "GET",
    dataType: "json"
});

request.done(function (msg) {

    $('.card').filter(function () {
        var thiss = $(this);
        $.each(msg, function (key, value) {
            if (thiss.data('id') == value.city) {
                if (value.size == 1) {
                    thiss.find(".head-showmore").addClass("d-none")
                }
            }
        });

    });

});

request.fail(function (jqXHR, textStatus) {
    console.log("Request failed: " + textStatus);
});
//Showmore and showLess
$('.collapse-toggle').click(function () {
    var str = "#collapse";
    var value = $(this).attr('data-target').substring(str.length, parseInt($(this).attr('data-target').length));
    var className = '.collapse-toggle-' + value;
    if ($(className).text().trim().indexOf('more') != -1) {
        $(".collapse-toggle").text("Show more");
        $(className).text("Show less");
    } else if ($(className).text().trim().indexOf('less') != -1) {
        $(className).text("Show more");
    }
});