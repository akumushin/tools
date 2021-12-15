///<reference path="C:\IDE\jquery.d.ts" />
document.addEventListener("DOMContentLoaded", function(){
	$(".select").click(function(e){
                    e.preventDefault();
                    var data = {"output":$(this).attr("data-output"), "display":$(this).attr("data-display")};
                    localStorage.setItem($("#result").attr("data-key"), JSON.stringify(data));
                    localStorage.removeItem($("#result").attr("data-key"));
                    window.close();
                });
	$("#searchForm").submit(function(e){
		
		e.preventDefault();
		var $form = $(this);
        var $button = $form.find('button');
		$.ajax({
            url: $form.attr('action'),
            type: $form.attr('method'),
            data: $form.serialize(),  // （デモ用に入力値をちょいと操作します）
            timeout: 10000,  // 単位はミリ秒
            
            // 送信前
            beforeSend: function(xhr, settings) {
                // ボタンを無効化し、二重送信を防止
                $button.attr('disabled', true);
            },
            // 応答後
            complete: function(xhr, textStatus) {
                // ボタンを有効化し、再送信を許可
                $button.attr('disabled', false);
            },
            
            // 通信成功時の処理
            success: function(result, textStatus, xhr) {
                
				$('#result').text("");
                result.forEach(p => {
                    console.log(p);
                    var tr= '<tr>'
                    +'<td><button data-output="'+p.id+'" data-display="'+p.name+'" class ="select">Chọn</button></td>'
                    +'<td>'+p.id+'</td>'
                    +'<td>'+p.name+'</td>'
                    +'<td>'+p.sex+'</td>'
                    +'<td>'+p.birthday+'</td>'
                    +'</tr>';
                    $('#result').append(tr);
                });
                $(".select").click(function(e){
                    e.preventDefault();
                    var data = {"output":$(this).attr("data-output"), "display":$(this).attr("data-display")};
                    localStorage.setItem($("#result").attr("data-key"), JSON.stringify(data));
                    localStorage.removeItem($("#result").attr("data-key"));
                    window.close();
                });
            },
            
            // 通信失敗時の処理
            error: function(xhr, textStatus, error) {}
        });
		
	});
}
);