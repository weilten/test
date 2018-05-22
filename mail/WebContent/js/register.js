$(function() {
	$("[type='submit']").bind({click:function(){
		if(this.id='0'){// 邮箱注册
			document.forms[0].submit();
		}else{// 手机注册
			document.forms[1].submit();
		}
	}});
})