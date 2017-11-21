$(document).ready(function(){
	var url = window.location.search;
	var params  = url.substr(1).split('&');
	var act = '';
	var op  = '';
	for(var j=0; j < params.length; j++)
	{
		var param = params[j];
		var arr   = param.split('=');
		if(arr[0] == 'act')
		{
			act = arr[1];
		}
		if(arr[0] == 'op')
		{
			sort = arr[1];
		}
	}
	//����Ҫ�޸ĵ�λ������޸���Ϊ
	$('span[nc_type="inline_edit"]').click(function(){
		var s_value  = $(this).text();
		var s_name   = $(this).attr('fieldname');
		var s_id     = $(this).attr('fieldid');
		var req      = $(this).attr('required');
		var type     = $(this).attr('datatype');
		var max      = $(this).attr('maxvalue');
		var ajax_branch      = $(this).attr('ajax_branch');
		$('<input type="text">')
			.attr({value:s_value})
			.insertAfter($(this))
			.focus()
			.select()
			.keyup(function(event){
				if(event.keyCode == 13)
				{
					if(req)
					{
						if(!required($(this).attr('value'),s_value,$(this)))
						{
							return;
						}
					}
					if(type)
					{
						if(!check_type(type,$(this).attr('value'),s_value,$(this)))
						{
							return;
						}
					}
					if(max)
					{
						if(!check_max($(this).attr('value'),s_value,max,$(this)))
						{
							return;
						}
					}
					$(this).prev('span').show().text($(this).attr("value"));
					//branch ajax ��֧
					//id �޸�����������ʶ
					//column �޸��ֶ���
					//value �޸�����
					$.get('index.php?act='+act+'&op=ajax',{branch:ajax_branch,id:s_id,column:s_name,value:$(this).attr('value')},function(data){
						if(data === 'false')
						{
							alert('�����Ѿ����ڣ�������һ��');
							$('span[fieldname="'+s_name+'"][fieldid="'+s_id+'"]').text(s_value);
							return;
						}
					});
					$(this).remove();
				}
			})
			.blur(function(){
				if(req)
				{
					if(!required($(this).attr('value'),s_value,$(this)))
					{
						return;
					}
				}
				if(type)
				{
					if(!check_type(type,$(this).attr('value'),s_value,$(this)))
					{
						return;
					}
				}
				if(max)
				{
					if(!check_max($(this).attr('value'),s_value,max,$(this)))
					{
						return;
					}
				}
				$(this).prev('span').show().text($(this).attr('value'));
				$.get('index.php?act='+act+'&op=ajax',{branch:ajax_branch,id:s_id,column:s_name,value:$(this).attr('value')},function(data){
					if(data === 'false')
					{
						alert('�����Ѿ����ڣ�������һ��');
						$('span[fieldname="'+s_name+'"][fieldid="'+s_id+'"]').text(s_value);
						return;
					}
				});
				$(this).remove();
			});
		$(this).hide();
	});


	$('span[nc_type="inline_edit_textarea"]').click(function(){
		var s_value  = $(this).text();
		var s_name   = $(this).attr('fieldname');
		var s_id     = $(this).attr('fieldid');
		var req      = $(this).attr('required');
		var type     = $(this).attr('datatype');
		var max      = $(this).attr('maxvalue');
		var ajax_branch      = $(this).attr('ajax_branch_textarea');
		$('<textarea>')
			.attr({value:s_value})
			.appendTo($(this).parent())
			.focus()
			.select()
			.keyup(function(event){
				if(event.keyCode == 13)
				{
					if(req)
					{
						if(!required($(this).attr('value'),s_value,$(this)))
						{
							return;
						}
					}
					if(type)
					{
						if(!check_type(type,$(this).attr('value'),s_value,$(this)))
						{
							return;
						}
					}
					if(max)
					{
						if(!check_max($(this).attr('value'),s_value,max,$(this)))
						{
							return;
						}
					}
					$(this).prev('span').show().text($(this).attr("value"));
					//branch ajax ��֧
					//id �޸�����������ʶ
					//column �޸��ֶ���
					//value �޸�����
					$.get('index.php?act='+act+'&op=ajax',{branch:ajax_branch,id:s_id,column:s_name,value:$(this).attr('value')},function(data){
						if(data === 'false')
						{
							alert('�����Ѿ����ڣ�������һ��');
							$('span[fieldname="'+s_name+'"][fieldid="'+s_id+'"]').text(s_value);
							return;
						}
					});
					$(this).remove();
				}
			})
			.blur(function(){
				if(req)
				{
					if(!required($(this).attr('value'),s_value,$(this)))
					{
						return;
					}
				}
				if(type)
				{
					if(!check_type(type,$(this).attr('value'),s_value,$(this)))
					{
						return;
					}
				}
				if(max)
				{
					if(!check_max($(this).attr('value'),s_value,max,$(this)))
					{
						return;
					}
				}
				$(this).prev('span').show().text($(this).attr('value'));
				$.get('index.php?act='+act+'&op=ajax',{branch:ajax_branch,id:s_id,column:s_name,value:$(this).attr('value')},function(data){
					if(data === 'false')
					{
						alert('�����Ѿ����ڣ�������һ��');
						$('span[fieldname="'+s_name+'"][fieldid="'+s_id+'"]').text(s_value);
						return;
					}
				});
				$(this).remove();
			});
		$(this).hide();
	});

	//����Ҫ�޸ĵ�ͼƬ����첽�޸���Ϊ
	$('img[nc_type="inline_edit"]').click(function(){
		var i_id    = $(this).attr('fieldid');
		var i_name  = $(this).attr('fieldname');
		var i_src   = $(this).attr('src');
		var i_val   = ($(this).attr('fieldvalue'))== 0 ? 1 : 0;
		var ajax_branch      = $(this).attr('ajax_branch');

		$.get('index.php?act='+act+'&op=ajax',{branch:ajax_branch,id:i_id,column:i_name,value:i_val},function(data){
			if(data == 'true')
			{
				if(i_val == 0)
				{
					$('img[fieldid="'+i_id+'"][fieldname="'+i_name+'"]').attr({'src':i_src.replace('enabled','disabled'),'fieldvalue':i_val});
				}
				else
				{
					$('img[fieldid="'+i_id+'"][fieldname="'+i_name+'"]').attr({'src':i_src.replace('disabled','enabled'),'fieldvalue':i_val});
				}
			}
		});
	});
	$('a[nc_type="inline_edit"]').click(function(){
		var i_id    = $(this).attr('fieldid');
		var i_name  = $(this).attr('fieldname');
		var i_src   = $(this).attr('src');
		var i_val   = ($(this).attr('fieldvalue'))== 0 ? 1 : 0;
		var ajax_branch      = $(this).attr('ajax_branch');

		$.get('index.php?act='+act+'&op=ajax',{branch:ajax_branch,id:i_id,column:i_name,value:i_val},function(data){
			if(data == 'true')
			{
				if(i_val == 0){
					$('a[fieldid="'+i_id+'"][fieldname="'+i_name+'"]').attr({'class':('enabled','disabled'),'title':('����','�ر�'),'fieldvalue':i_val});
				}else{
					$('a[fieldid="'+i_id+'"][fieldname="'+i_name+'"]').attr({'class':('disabled','enabled'),'title':('�ر�','����'),'fieldvalue':i_val});
				}
			}else{
				alert('��Ӧʧ��');
			}
		});
	});
	//��ÿ���ɱ༭��СͼƬ�ĸ�Ԫ����ӿɱ༭���� $('img[nc_type="inline_edit"]').parent().attr('title','�ɱ༭');

	//���б���������Ϊ��������������Ч��
	$('span[nc_type="order_by"]').hover(function(){$(this).css({cursor:'pointer'});},function(){});

});
//����ύ���ݵı�����
function required(str,s_value,jqobj)
{
	if(str == '')
	{
		jqobj.prev('span').show().text(s_value);
		jqobj.remove();
		alert('�����Ϊ��');
		return 0;
	}
	return 1;
}
//����ύ���ݵ������Ƿ�Ϸ�
function check_type(type, value, s_value, jqobj)
{
	if(type == 'number')
	{
		if(isNaN(value))
		{
			jqobj.prev('span').show().text(s_value);
			jqobj.remove();
			alert('�������Ϊ����');
			return 0;
		}
	}
	if(type == 'int')
	{
		var regu = /^-{0,1}[0-9]{1,}$/;
		if(!regu.test(value))
		{
			jqobj.prev('span').show().text(s_value);
			jqobj.remove();
			alert('�������Ϊ����');
			return 0;
		}
	}
	if(type == 'pint')
	{
		var regu = /^[0-9]+$/;
		if(!regu.test(value))
		{
			jqobj.prev('span').show().text(s_value);
			jqobj.remove();
			alert('�������Ϊ������');
			return 0;
		}
	}
	if(type == 'zint')
	{
		var regu = /^[1-9]\d*$/;
		if(!regu.test(value))
		{
			jqobj.prev('span').show().text(s_value);
			jqobj.remove();
			alert('�������Ϊ������');
			return 0;
		}
	}
	if(type == 'discount')
	{
		var regu = /[1-9]|0\.[1-9]|[1-9]\.[0-9]/;
		if(!regu.test(value))
		{
			jqobj.prev('span').show().text(s_value);
			jqobj.remove();
			alert('ֻ����0.1-9.9֮�������');
			return 0;
		}
	}
	return 1;
}
//�������������ֵ
function check_max(str,s_value,max,jqobj)
{
	if(parseInt(str) > parseInt(max))
	{
		jqobj.prev('span').show().text(s_value);
		jqobj.remove();
		alert('����ӦС�ڵ���'+max);
		return 0;
	}
	return 1;
}


//�µ�inline_edit���÷���
//javacript
//$('span[nc_type="class_sort"]').inline_edit({act: 'microshop',op: 'update_class_sort'});
//html
//<span nc_type="class_sort" column_id="<?php echo $val['class_id'];?>" title="<?php echo $lang['nc_editable'];?>" class="editable tooltip"><?php echo $val['class_sort'];?></span>
//php
//$result = array();
//$result['result'] = FALSE;/TURE
//$result['message'] = '����';
//echo json_encode($result);

(function($) {
	$.fn.inline_edit= function(options) {
		var settings = $.extend({}, {open: false}, options);
		return this.each(function() {
			$(this).click(onClick);
		});

		function onClick() {
			var span = $(this);
			var old_value = $(this).html();
			var column_id = $(this).attr("column_id");
			$('<input type="text">')
				.insertAfter($(this))
				.focus()
				.select()
				.val(old_value)
				.blur(function(){
					var new_value = $(this).attr("value");
					if(new_value != '') {
						$.get('index.php?act='+settings.act+'&op='+settings.op+'&branch=ajax',{id:column_id,value:new_value},function(data){
							data = $.parseJSON(data);
							if(data.result) {
								span.show().text(new_value);
							} else {
								span.show().text(old_value);
								alert(data.message);
							}
						});
					} else {
						span.show().text(old_value);
					}
					$(this).remove();
				})
			$(this).hide();
		}
	}
})(jQuery);

(function($) {
	$.fn.inline_edit_confirm = function(options) {
		var settings = $.extend({}, {open: false}, options);
		return this.each(function() {
			$(this).click(onClick);
		});

		function onClick() {
			var $span = $(this);
			var old_value = $(this).text();
			var column_id = $(this).attr("column_id");
			var $input = $('<input type="text">');
			var $btn_submit = $('<a class="inline-edit-submit" href="JavaScript:;">ȷ��</a>');
			var $btn_cancel = $('<a class="inline-edit-cancel" href="JavaScript:;">ȡ��</a>');

			$input.insertAfter($span).focus().select().val(old_value);
			$btn_submit.insertAfter($input);
			$btn_cancel.insertAfter($btn_submit);
			$span.hide();

			$btn_submit.click(function(){
				var new_value = $input.attr("value");
				if(new_value !== '' && new_value !== old_value) {
					$.post('index.php?act=' + settings.act + '&op=' + settings.op, {id:column_id, value:new_value}, function(data) {
						data = $.parseJSON(data);
						if(data.result) {
							$span.text(new_value);
						} else {
							alert(data.message);
						}
					});
				}
				show();
			});

			$btn_cancel.click(function() {
				show();
			});

			function show() {
				$span.show();
				$input.remove();
				$btn_submit.remove();
				$btn_cancel.remove();
			}
		}
	};
})(jQuery);


