/**
 * 登录页业务脚本
 */
$(function() {
	//  绑定登录按钮事件
	$("#login_button").bind({click:function(){
		$("#login_form").submit();
	}});
	
	// 绑定记住密码按钮事件
	$("#remember-me").bind({click:function(){
		$("#remember").val(this.checked);
	}});
})