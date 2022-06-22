
console.log("navabr");

const toggleNavbar = () => {

	if ($(".sidebar").is(":visible")) {
		$(".sidebar").css("display", "none");
		$(".sidebar").css("margin-left", "0");
		$(".sidebar").css("transition", "0.9");
	}

	else {
		$(".sidebar").css("display", "block");
		$(".sidebar").css("margin-left", "0");
		$(".sidebar").css("transition", "0.9");
	}
}