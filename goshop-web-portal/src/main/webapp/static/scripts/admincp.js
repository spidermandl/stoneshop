// JavaScript Document


//�Զ���radio��ʽ
$(document).ready( function(){
	$(".cb-enable").click(function(){
		var parent = $(this).parents('.onoff');
		$('.cb-disable',parent).removeClass('selected');
		$(this).addClass('selected');
		$('.checkbox',parent).attr('checked', true);
	});
	$(".cb-disable").click(function(){
		var parent = $(this).parents('.onoff');
		$('.cb-enable',parent).removeClass('selected');
		$(this).addClass('selected');
		$('.checkbox',parent).attr('checked', false);
	});
});


//ͼƬ�������ſ���
function DrawImage(ImgD, FitWidth, FitHeight) {
	var image = new Image();
	image.src = ImgD.src;
	if (image.width > 0 && image.height > 0) {
		if (image.width / image.height >= FitWidth / FitHeight) {
			if (image.width > FitWidth) {
				ImgD.width = FitWidth;
				ImgD.height = (image.height * FitWidth) / image.width;
			} else {
				ImgD.width = image.width;
				ImgD.height = image.height;
			}
		} else {
			if (image.height > FitHeight) {
				ImgD.height = FitHeight;
				ImgD.width = (image.width * FitHeight) / image.height;
			} else {
				ImgD.width = image.width;
				ImgD.height = image.height;
			}
		}
	}
}


$(function(){
	// ��ʾ����Ԥ��ͼ start
	$('.show_image').hover(
		function(){
			$(this).next().css('display','block');
		},
		function(){
			$(this).next().css('display','none');
		}
	);

	// ȫѡ start
	$('.checkall').click(function(){
		$('.checkall').attr('checked',$(this).attr('checked') == 'checked');
		$('.checkitem').each(function(){
			$(this).attr('checked',$('.checkall').attr('checked') == 'checked');
		});
	});

	// ��������ͣ��ɫ start
	$("tbody tr").hover(
		function(){
			$(this).css({background:"#FBFBFB"} );
		},
		function(){
			$(this).css({background:"#FFF"} );
		});

	// �ɱ༭�У�input����ɫ
	$('.editable').hover(
		function(){
			$(this).removeClass('editable').addClass('editable2');
		},
		function(){
			$(this).removeClass('editable2').addClass('editable');
		}
	);

	// ��ʾ���� չ��������
	$("#prompt tr:odd").addClass("odd");
	$("#prompt tr:not(.odd)").hide();
	$("#prompt tr:first-child").show();

	$("#prompt tr.odd").click(function(){
		$(this).next("tr").toggle();
		$(this).find(".title").toggleClass("ac");
		$(this).find(".arrow").toggleClass("up");

	});

	// �ɱ༭�У�area����ɫ
	$('.editable-tarea').hover(
		function(){
			$(this).removeClass('editable-tarea').addClass('editable-tarea2');
		},
		function(){
			$(this).removeClass('editable-tarea2').addClass('editable-tarea');
		}
	);

});

/* �����ȡ����ȫ·�� */
function getFullPath(obj)
{
	if(obj)
	{
		// ie
		if (window.navigator.userAgent.indexOf("MSIE")>=1)
		{
			obj.select();
			if(window.navigator.userAgent.indexOf("MSIE") == 25){
				obj.blur();
			}
			return document.selection.createRange().text;
		}
		// firefox
		else if(window.navigator.userAgent.indexOf("Firefox")>=1)
		{
			if(obj.files)
			{
				//return obj.files.item(0).getAsDataURL();
				return window.URL.createObjectURL(obj.files.item(0));
			}
			return obj.value;
		}
		return obj.value;
	}
}


