(function($) {

	"use strict";


  // Form
	var contactForm = function() {
		if ($('#contactForm').length > 0 ) {
			$( "#contactForm" ).validate( {
				rules: {
					name: "required",
					subject: "required",
					email: {
						required: true,
						email: true
					},
					message: {
						required: true,
						minlength: 5
					}
				},
				messages: {
					name: "이름을 입력하여주세요",
					subject: "Please enter your ID",
					email: "이메일을 입력하여주세요",
					message: "Please enter a message"
				},
				/* submit via ajax */

			});
		}
	};
	contactForm();

})(jQuery);
