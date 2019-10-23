$(function () {
    $("#username").blur(function () {
        $('#info-username').html('');
    });

    $("#password").blur(function () {
        $('#info-password').html('');
    });

    $("#btnLogin").click(function () {
        var username = $("#username").val();
        var password = $("#password").val();

        $.ajax({
            type: 'POST',
            url: '/accounts/validate',
            data: JSON.stringify({
                username: username,
                password: password,
            }),
            contentType: 'application/json; charset=utf-8',
            cache: false,
            success: function (data) {
                $("#fromLogin").submit();
            },
            error: function (error) {
                $('#info-password').html('');
                $('#info-username').html('');

                if (error.responseJSON.usernameError) {
                    $('#info-username').html(error.responseJSON.usernameError);
                }

                if (error.responseJSON.passwordError) {
                    $('#info-password').html(error.responseJSON.passwordError);
                }
            }
        });
    });
});