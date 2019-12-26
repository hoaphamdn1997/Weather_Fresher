$().ready(function () {
    /**
     * check user name in DB
     * @param userName
     * @returns
     */
    $('.username').on('keyup', function () {
        //Check UserName existed when user type input
        if (this.value.length > 0) {
            var userName = this.value;
            //Check in DB system
            $.ajax({
                url: "/checkUserName",
                type: "POST",
                data: {
                    userName: userName
                },
                success: function (value) {
                    // If result = true, UserName exsts
                    if (value == "true") {
                        // Message when UserName exists
                        $("#kqCheckName").text("User name exists in system !");
                        // Disable input in form sign up
                        var inputs = document.getElementsByClassName("disinput");
                        for (i = 0; i < inputs.length; i++) {
                            inputs[i].disabled = true;
                        }
                        // If result = false
                    } else {
                        $("#kqCheckName").text("")
                        var inputs = document.getElementsByClassName("disinput");
                        for (i = 0; i < inputs.length; i++) {
                            inputs[i].disabled = false;
                        }
                    }
                }
            })
        } else {
            $("#kqCheckName").text("");
        }
    });

    /**
     * check email in DB
     *
     * @param email
     * @returns
     */
    $('.emailuser').on('keyup', function () {
        ////Check Email existed when user type input
        if (this.value.length > 0) {
            var email = this.value;
            //Check in DB system
            $.ajax({
                url: "/checkEmail",
                type: "POST",
                data: {
                    email: email
                },
                success: function (value) {
                    // If result = true, Email exsts
                    if (value == "true") {
                        // Message when Email exists
                        $("#kqCheckMail").text("Email exists in system !");
                        //$("userName-error").text("Email exists in system !");
                        // Disable input in form sign up
                        var inputs = document.getElementsByClassName("dinput");
                        for (i = 0; i < inputs.length; i++) {
                            inputs[i].disabled = true;
                        }
                        // If result = false
                    } else {
                        $("#kqCheckMail").text("");
                        var inputs = document.getElementsByClassName("dinput");
                        for (i = 0; i < inputs.length; i++) {
                            inputs[i].disabled = false;
                        }
                    }
                }
            })
        } else {
            $("#kqCheckMail").text("");
        }
    });

    //
    $.validator.addMethod("validateUserName", function (value, element) {
        return this.optional(element) || /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,32}$/i.test(value);
    }, "Include uppercase, lowercase letters and least one digit, Length least 8 , up to 32 .");

    //Validate Form
    $("#form-signup").validate({
        rules: {
            "userName": {
                required: true,
                validateUserName: true,
            },
            "email": {
                required: true,
                email: true
            },
            "encrytedPassword": {
                required: true,
                minlength: 8,
                maxlength: 32
            },
            "confirmPassword": {
                equalTo: "#password",
                minlength: 8
            },
            "firstName": {
                required: true
            },
            "lastName": {
                required: true
            }
        },
        messages: {
            "userName": {
                required: "UserName is required .",
            },
            "email": {
                required: "Email is required ."
            },
            "encrytedPassword": {
                required: "Password is required .",
                minlength: "Please enter at least 8 characters .",
                maxlength: "Please enter up to 32 characters ."
            },
            "confirmPassword": {
                equalTo: "The two passwords must be the same .",
                minlength: "Please enter at least 8 characters .",
            },
            "firstName": {
                required: "FirstName is required ."
            },
            "lastName": {
                required: "LastName is required ."
            }
        }
    });
});