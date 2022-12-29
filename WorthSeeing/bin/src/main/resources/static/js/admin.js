function changeCheckBox() {
	$(".blackYnCheckBox").change(
			function() {

				const is_checked = $(this).is(
						':checked');
				console.log(is_checked);

				if (is_checked) {
					$(this).next().val("yes");
				} else {
					$(this).next().val("no");
				}
			});
}
$(document).ready(function() {
	changeCheckBox();
});
