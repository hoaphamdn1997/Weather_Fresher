(function() {
	$.validator.addMethod('regexp', function(value, element, param) {
        return this.optional(element) || value.match(param[0]);
    },
	'Username chỉ chứa chữ cái và số');
	
	$("#formSignup")
			.validate(
					{
						rules : {
							username : {
								required : true,
								minlength : 8,
								maxlength : 32,
								regexp: [/^[a-zA-Z0-9]{8,32}$/, 'username']
							},
							email : {
								required : true,
								email : true
							},
							password : {
								required : true,
								minlength : 8,
								maxlength : 32
							},
							firstName : {
								required : true
							},
							lastName : {
								required : true
							}
						},
						messages : {
							username : {
								required : "Không được để trống Username",
								minlength : "Username phải lớn hơn 8 ký tự",
								maxlength : "Username phải nhỏ hơn 32 ký tự",
							},
							email : {
								required : "Không được để trống Email",
								email : "Vui lòng nhập đúng định dạng email"
							},
							password : {
								required : "Không được để trống Password",
								minlength : "Password phải lớn hơn 8 ký tự",
								maxlength : "Password phải nhỏ hơn 32 ký tự",
							},
							firstName : {
								required : "Không được để trống First Name",
							},
							lastName : {
								required : "Không được để trống Last Name",
							}
						}
					});
})();