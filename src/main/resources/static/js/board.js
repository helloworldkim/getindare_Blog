let index = {
	init:function(){
	$('#btn-save').on("click",()=>{
		this.save();
		});
	},
	
	save:function(){
        //alert("user의 save함수!");
        let data = {
            title:$("#title").val(),
            content:$("#content").val()
        }

        $.ajax({
            type:"POST",
            url:"/api/board",
            data:JSON.stringify(data),//http body 데이터
            contentType:"application/json; charset=utf-8" //body 데이터가 어떤 타입인지(MINE)
            // dataType:"json" ajax default 값이 json이라 생략가능
        }).done(function(resp){
            console.log(resp);
            alert("글쓰기가 완료되었습니다");
            location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
	}
}
index.init();