$(document).ready(function(){
	//�б�����
	$('img[nc_type="flex"]').click(function(){
		var status = $(this).attr('status');
		if(status == 'open'){
			var pr = $(this).parent('td').parent('tr');
			var id = $(this).attr('fieldid');
			var obj = $(this);
			$(this).attr('status','none');
			//ajax
			$.ajax({
				url: 'index.php?act=store_class&op=store_class&ajax=1&sc_parent_id='+id,
				dataType: 'json',
				success: function(data){
					var src='';
					for(var i = 0; i < data.length; i++){
						var tmp_vertline = "<img class='preimg' src='templates/images/vertline.gif'/>";
						src += "<tr class='"+pr.attr('class')+" row"+id+"'>";
						src += "<td class='w36'><input type='checkbox' name='check_ac_id[]' value='"+data[i].ac_id+"' class='checkitem' />";
						if(data[i].have_child == 1){
							src += "<img fieldid='"+data[i].sc_id+"' status='open' nc_type='flex' src='"+ADMIN_TEMPLATES_URL+"/images/tv-expandable.gif' />";
						}else{
							src += "<img fieldid='"+data[i].sc_id+"' status='none' nc_type='flex' src='"+ADMIN_TEMPLATES_URL+"/images/tv-item.gif' />";
						}
						//ͼƬ
						src += "</td><td class='w48 sort'>";
						//����
						src += "<span title='�ɱ༭' ajax_branch='store_class_sort' datatype='number' fieldid='"+data[i].sc_id+"' fieldname='sc_sort' nc_type='inline_edit' class='editable'>"+data[i].sc_sort+"</span>";
						//����
						src += "<td class='name'>";
						for(var tmp_i=1; tmp_i < (data[i].deep-1); tmp_i++){
							src += tmp_vertline;
						}
						if(data[i].have_child == 1){
							src += " <img fieldid='"+data[i].sc_id+"' status='open' nc_type='flex' src='"+ADMIN_TEMPLATES_URL+"/images/tv-item1.gif' />";
						}else{
							src += " <img fieldid='"+data[i].sc_id+"' status='none' nc_type='flex' src='"+ADMIN_TEMPLATES_URL+"/images/tv-expandable1.gif' />";
						}
						src += "<span title='�ɱ༭' required='1' fieldid='"+data[i].sc_id+"' ajax_branch='store_class_name' fieldname='sc_name' nc_type='inline_edit' class='node_name editable'>"+data[i].sc_name+"</span>";
						//�����¼�
						if(data[i].deep < 2){
							src += "<a  class='btn-add-nofloat marginleft' href='index.php?act=store_class&op=store_class_add&sc_parent_id="+data[i].sc_id+"'><span>�����¼�<span></a></span>";
						}
						src += "</td>";

						//����
						src += "<td class='w84'>";
						src += "<span><a href='index.php?act=store_class&op=store_class_edit&sc_id="+data[i].sc_id+"'>�༭</a>";
						src += " | <a href=\"javascript:if(confirm('ɾ���÷��ཫ��ͬʱɾ���÷���������¼����࣬��ȷ��Ҫɾ����'))window.location = 'index.php?act=store_class&op=store_class_del&sc_id="+data[i].sc_id+"';\">ɾ��</a>";
						src += "</td>";
						src += "</tr>";
					}
					//����
					pr.after(src);
					obj.attr('status','close');
					obj.attr('src',obj.attr('src').replace("tv-expandable","tv-collapsable"));
					$('img[nc_type="flex"]').unbind('click');
					$('span[nc_type="inline_edit"]').unbind('click');
					//���ֳ�ʼ��ҳ��
					$.getScript(RESOURCE_SITE_URL+"/js/jquery.edit.js");
					$.getScript(RESOURCE_SITE_URL+"/js/jquery.store_class.js");
					$.getScript(RESOURCE_SITE_URL+"/js/admincp.js");
				},
				error: function(){
					alert('��ȡ��Ϣʧ��');
				}
			});
		}
		if(status == 'close'){
			$(".row"+$(this).attr('fieldid')).remove();
			$(this).attr('src',$(this).attr('src').replace("tv-collapsable","tv-expandable"));
			$(this).attr('status','open');
		}
	})
});