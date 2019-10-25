(function () {
    $.validator.addMethod('regexp', function (value, element, param) {
            return this.optional(element) || value.match(param[0]);
        },
        usernamePattern);

    $("#formSignup")
        .validate(
            {
                rules: {
                    username: {
                        required: true,
                        minlength: 8,
                        maxlength: 32,
                        regexp: [/^[a-zA-Z0-9]{8,32}$/, 'username']
                    },
                    email: {
                        required: true,
                        email: true
                    },
                    encrytedPassword: {
                        required: true,
                        minlength: 8,
                        maxlength: 32
                    },
                    firstName: {
                        required: true
                    },
                    lastName: {
                        required: true
                    }
                },
                messages: {
                    username: {
                        required: usernameRequired,
                        minlength: usernameMinlength,
                        maxlength: usernameMaxlength
                    },
                    email: {
                        required: emailRequired,
                        email: emailPattern
                    },
                    password: {
                        required: passwordRequired,
                        minlength: passwordMinLength,
                        maxlength: passwordMaxLength
                    },
                    firstName: {
                        required: firstNameRequired
                    },
                    lastName: {
                        required: lastNameRequired
                    }
                }
            });
})();