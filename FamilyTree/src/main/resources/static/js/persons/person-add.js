///<reference path="C:\IDE\jquery.d.ts" />
document.addEventListener("DOMContentLoaded", function(){
	$(".search").click(function(e){
		e.preventDefault();
		var link = $(this).attr("data-link");
		var displayTarget = $(this).attr("data-display");
		var outputTarget =$(this).attr("data-output");
		var key = new Date().getTime();
		//console.log(key);
		window.open(link+key);
		window.addEventListener("storage", function(e){
			if (e.key == key && e.newValue !=null) {
				var message = JSON.parse(e.newValue); // chỉ check với key là 'broadcast'
				$(displayTarget).text( message.display);
				$(outputTarget).val(message.output);
			  }
		});
	});
	$(".delete").click(function(e){
		e.preventDefault();
		var displayTarget = $(this).attr("data-display");
		var outputTarget =$(this).attr("data-output");
		//console.log("del #" +displayTarget);
		$(displayTarget).text("");
		$(outputTarget).val("");
	});
});