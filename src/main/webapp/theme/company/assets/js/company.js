var global_list =[];
var global_list_old =[];
$(document).ready(function() {
	(function($){
		$(document.body).on('click','.add-massage',function(){
			$("input[name='search_keyword']").val("");					
			user_message_popup(function() {
				$('#popupaddmassage').modal('show');
			});
		});
	})(jQuery);
	
	(function($){
		$(document.body).on('click','.btn-search-listuser',function(){
			user_message_popup();
		});
	})(jQuery);
	
	$('.btn-policy-create').on("click", function(e) {
		var type = $(this).attr('data-type');
		var data_content = showHTML();
		var version = $('#version').val();
		var selected = $("#typePolicy").val();
		if(data_content == '<p><br></p>' || version.length == 0 ) {					
			if(data_content == '<p><br></p>'){
				alert("입력되지 않은 항목이 있습니다.[내용]");
				return false;
			} else {
				alert("입력되지 않은 항목이 있습니다.[버전]");
				return false;
			}
		} else {
			$.ajax({
				method : "POST",
				url : "/api/v1/public/ajax_policy_create",
				data : {
					"type" : type,
					"data_content" : data_content,
					"version" : version,
					"typePolicy" : selected
				},
				success : function(rs) {
					
				}
			});
		}
	});
	
	function user_message_popup(callback){
		$('#all').prop('checked',false);
		$('.checked').removeClass('checked');
		var url = window.location.protocol +"//" + window.location.host + "/v1/public/ajax_message_get_list_user";
		var keyword = $("input[name='search_keyword']").val();
		$.ajax({
		   type: "GET",
		   url: url,
		   data: {keyword:keyword},
		   success: function(response) {
			     var html = '';
			     $(".list_user").html('');
			     for (var i = 0; i < response.data.length; i++) {
			    	 if(jQuery.inArray(response.data[i].id,global_list) == -1){
			    		 html +='<div class="mrb-20 full check">';
			    	 	 html +='<label>';
			    	 	 html +='<input data-fullname="'+response.data[i].fullname+'" type="checkbox" name="userIdMess" value="'+response.data[i].id+'"/><span>'+response.data[i].fullname+'</span>';
			    	 	 html +='<label>';
			    	 	 html +='</div>';
			    		};
			    	 
			     }
			     $(".list_user").html(html);
			     $('.check input').uniform('refresh');
			    if(callback) {
			    	callback();
			    };
		   	}
		});
		
		$('.btn-add-user-send-message').on("click", function(e){
			$('#add-user-mess').html('');
			$('input[name=userIdMess]:checked').each(function(i, elem) {
				var fullname = $(elem).data('fullname');
				var id = $(elem).val();
				global_list.push(parseInt(id));
				var html = '<div class="data'+id+'"><span class="btn green pull-left" style="margin:0 5px 5px 0;" data-id-user='+id+'>'+
							'<span>'+fullname+'</span><i aria-hidden="true" class="fa fa-close mrl-10"></i>'+
					  		'</span></div>';
				$('#add-user-mess').append(html);
			});
	  		
	  		$('#modal').modal('toggle');
		});
		
	}
});

