///<reference path="C:\IDE\jquery.d.ts" />
document.addEventListener("DOMContentLoaded", function(){
	$(".select").click(function(e){
                    e.preventDefault();
                    var data = {"output":$(this).attr("data-output"), "display":$(this).attr("data-display")};
                    localStorage.setItem($("#result").attr("data-key"), JSON.stringify(data));
                    localStorage.removeItem($("#result").attr("data-key"));
                    window.close();
                });
}
);