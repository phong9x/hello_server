
$(document).ready(function() {
	 $.sidebarMenu($('.sidebar-menu'))

  if ($('#popupEditMenu').length) {
    $(function () {
      // $("#popupEditMenu .md-content ul.v1 li").click(function(e){
      $(document.body).on('click', '#popupEditMenu .md-content ul.v1 li.select ', function(e) {
        e.preventDefault();
        var count = $('#popupEditMenu .md-content ul.v2 li').length;
        if (count < 6) {
          if( $(this).hasClass('activev1') ){
           $(this).removeClass('activev1');
            $('span.icon.v1').removeClass('active-menu');  
          }else{
           $('#popupEditMenu .md-content ul.v1 li.select').removeClass('activev1');
            $(this).addClass('activev1');
            $('span.icon.left').addClass('v1');
            $('span.icon.v1').addClass('active-menu');
          }
        }
          
      });

      // $("#popupEditMenu .md-content ul.v2 li").click(function(e){
      $(document.body).on('click', '#popupEditMenu .md-content ul.v2 li', function(e) {
          e.preventDefault();
          if( $(this).hasClass('activev2') ){
           $(this).removeClass('activev2');
            $('span.icon.v2').removeClass('active-menu');  
          }else{
           $('#popupEditMenu .md-content ul.v2 li').removeClass('activev2');
            $(this).addClass('activev2');
            $('span.icon.v2').addClass('active-menu');
          }
          
      });

      // $("#popupEditMenu span.icon.v1").click(function(e){
      $(document.body).on('click', '#popupEditMenu span.icon.v1.active-menu', function() {
        var count = $('#popupEditMenu .md-content ul.v2 li').length + 1;
        $('#popupEditMenu .md-content ul.v1 li.activev1').appendTo("#popupEditMenu .md-content ul.v2");
        $('#popupEditMenu .md-content ul.v2 li').removeClass('activev1');
        $('#popupEditMenu span.icon.v1').removeClass('active-menu');
        if (count > 5) {
            $('span.icon.v1').removeClass('v1');
        }
      });

      $(document.body).on('click', '#popupEditMenu span.icon.v2', function() {
        $('#popupEditMenu .md-content ul.v2 li.activev2').appendTo("#popupEditMenu .md-content ul.v1");
        $('#popupEditMenu .md-content ul.v1 li.select').removeClass('activev2');
        $('#popupEditMenu span.icon.v2').removeClass('active-menu');
      });

    });
  } 

  // add height
   var heightmain = $(".page-content-wrapper").height();
   var ichatheight = $(".ichat-height").height() - 75;
   $('.ichat-height2 .slide-map').css({"height": ichatheight});
   $('.ichat-height2 .slide-map .item-map').css({"height": ichatheight - 15});
  // $('.item-img-section').css({"height": widthimg, "line-height": widthimg+'px'});
  $('.sidebar-left').css({"height": heightmain});
  // $('.item-img-section').css({"line-height": widthimg+'px'})


  // jquery page mamber
  if ($('.selectpickermember').length) {
    $('.selectpickermember').selectpicker({
      style: 'btn-info',
      size: 4
    });
  }

  //intropage
  var imgintro = $(".intro-pageform .preview-image").width();
  $('.intro-pageform .preview-image').css({"height": imgintro*1.5+'px'});


  //mind check
  // $('div[class="testimonial"]').each(function(index,item){
  //   if(parseInt($(item).data('index'))>2){
  //       $(item).html('Testimonial1 '+(index+1)+' by each loop');
  //   }
  // });
  $(function() {
    $('#mind-list').on('change', function(){
      var minditem = $('#mind-list').selectpicker('val');
      var testimonials= $('.content-mind');
     // $('.member-main-top').html('');
        for (var i = 0; i < 30; i++) {
          // Using $() to re-wrap the element.
          // $(testimonials[i]).html('Testimonial by each loop');.html('Testimonial '+(index+1)+' by each loop');
          if(i< minditem){
          $(testimonials[i]).html('<div class="main-item-mind"><div class="number"><span>'+(i+1)+'</span></div><input type="text" name="name" placeholder="질의 입력" data-required="1" class="form-control"/><div class="item-option"><select name="selecttext" class="form-control selectpickermember pull-right"><option value="0">Yes</option><option value="1">No</option></select></td><td><select name="selecttext" class="form-control selectpickermember pull-right"><option value="0">심리적 증상</option><option value="1">심리적 증상</option></select></div><div class="item-in-ct"><input type="text" name="name" placeholder="답변 입력" data-required="1" class="form-control v1"/><input type="text" name="name" placeholder="점수 할당" data-required="1" class="form-control v2"/></div><div class="item-in-ct"><input type="text" name="name" placeholder="답변 입력" data-required="1" class="form-control v1"/><input type="text" name="name" placeholder="점수 할당" data-required="1" class="form-control v2"/></div></div>');
          $('.selectpickermember').selectpicker('refresh');
        }else{
           $(testimonials[i]).html('');
        }
        }
  
    });
  // var busiyear1 = $('#busiyear1').selectpicker('val');
  // alert(busiyear1);
  });
  

  $(function() {
    $('#busiyear1').on('change', function(){
      var busiyear1 = $('#busiyear1').selectpicker('val');
      var busiweek1 = $('#busiweek1').selectpicker('val');
      var busiday1 = $('#busiday1').selectpicker('val');
      var busiyear2 = $('#busiyear2').selectpicker('val');
      var busiweek2 = $('#busiweek2').selectpicker('val');
      var busiday2 = $('#busiday2').selectpicker('val');
      var alldate1 = busiyear1 + busiweek1 + busiday1;
      var alldate2 = busiyear2 + busiweek2 + busiday2;
      if(alldate1 >= alldate2){
        alert('계약 종료일은 계약 시작일 이후로 선택해야 합니다.');
        $('.submit-alldate').addClass('no-submit');
      }
      else{
        $('.submit-alldate').removeClass('no-submit');
      }
    });
    $('#busiyear2').on('change', function(){
      var busiyear1 = $('#busiyear1').selectpicker('val');
      var busiweek1 = $('#busiweek1').selectpicker('val');
      var busiday1 = $('#busiday1').selectpicker('val');
      var busiyear2 = $('#busiyear2').selectpicker('val');
      var busiweek2 = $('#busiweek2').selectpicker('val');
      var busiday2 = $('#busiday2').selectpicker('val');
      var alldate1 = busiyear1 + busiweek1 + busiday1;
      var alldate2 = busiyear2 + busiweek2 + busiday2;
      if(alldate1 >= alldate2){
        alert('계약 종료일은 계약 시작일 이후로 선택해야 합니다.');
        $('.submit-alldate').addClass('no-submit');
      }
      else{
        $('.submit-alldate').removeClass('no-submit');
      }
    });
    $('#busiweek1').on('change', function(){
      var busiyear1 = $('#busiyear1').selectpicker('val');
      var busiweek1 = $('#busiweek1').selectpicker('val');
      var busiday1 = $('#busiday1').selectpicker('val');
      var busiyear2 = $('#busiyear2').selectpicker('val');
      var busiweek2 = $('#busiweek2').selectpicker('val');
      var busiday2 = $('#busiday2').selectpicker('val');
      var alldate1 = busiyear1 + busiweek1 + busiday1;
      var alldate2 = busiyear2 + busiweek2 + busiday2;
      if(alldate1 >= alldate2){
        alert('계약 종료일은 계약 시작일 이후로 선택해야 합니다.');
        $('.submit-alldate').addClass('no-submit');
      }
      else{
        $('.submit-alldate').removeClass('no-submit');
      }
    });
    $('#busiweek2').on('change', function(){
      var busiyear1 = $('#busiyear1').selectpicker('val');
      var busiweek1 = $('#busiweek1').selectpicker('val');
      var busiday1 = $('#busiday1').selectpicker('val');
      var busiyear2 = $('#busiyear2').selectpicker('val');
      var busiweek2 = $('#busiweek2').selectpicker('val');
      var busiday2 = $('#busiday2').selectpicker('val');
      var alldate1 = busiyear1 + busiweek1 + busiday1;
      var alldate2 = busiyear2 + busiweek2 + busiday2;
      if(alldate1 >= alldate2){
        alert('계약 종료일은 계약 시작일 이후로 선택해야 합니다.');
        $('.submit-alldate').addClass('no-submit');
      }
      else{
        $('.submit-alldate').removeClass('no-submit');
      }
    });
    $('#busiday1').on('change', function(){
      var busiyear1 = $('#busiyear1').selectpicker('val');
      var busiweek1 = $('#busiweek1').selectpicker('val');
      var busiday1 = $('#busiday1').selectpicker('val');
      var busiyear2 = $('#busiyear2').selectpicker('val');
      var busiweek2 = $('#busiweek2').selectpicker('val');
      var busiday2 = $('#busiday2').selectpicker('val');
      var alldate1 = busiyear1 + busiweek1 + busiday1;
      var alldate2 = busiyear2 + busiweek2 + busiday2;
      if(alldate1 >= alldate2){
        alert('계약 종료일은 계약 시작일 이후로 선택해야 합니다.');
        $('.submit-alldate').addClass('no-submit');
      }
      else{
        $('.submit-alldate').removeClass('no-submit');
      }
    });
    $('#busiday2').on('change', function(){
      var busiyear1 = $('#busiyear1').selectpicker('val');
      var busiweek1 = $('#busiweek1').selectpicker('val');
      var busiday1 = $('#busiday1').selectpicker('val');
      var busiyear2 = $('#busiyear2').selectpicker('val');
      var busiweek2 = $('#busiweek2').selectpicker('val');
      var busiday2 = $('#busiday2').selectpicker('val');
      var alldate1 = busiyear1 + busiweek1 + busiday1;
      var alldate2 = busiyear2 + busiweek2 + busiday2;
      if(alldate1 >= alldate2){
        alert('계약 종료일은 계약 시작일 이후로 선택해야 합니다.');
        $('.submit-alldate').addClass('no-submit');
      }
      else{
        $('.submit-alldate').removeClass('no-submit');
      }
    });
    
    // $('#busiday1').on('change', function(){
    //   var busiweek1 = $('#busiday1').selectpicker('val');
    //   var alldate1 = busiyear1 + busiweek1 + busiday1;
    //   if(alldate1 >= alldate2){
    //     alert('note ghi vao day');
    //   }
    // });
    // $('#busiday1').on('change', function(){
    //   var busiweek1 = $('#busiday1').selectpicker('val');
    //   var alldate1 = busiyear1 + busiweek1 + busiday1;
    //   if(alldate1 >= alldate2){
    //     alert('note ghi vao day');
    //   }
    // });
    
  });
  // content upload file

  $(".ban-acc").on("click", function(event){
      $('#popupBanAcc').modal('show');
  });
  $(".delete-acc").on("click", function(event){
      $('#popupDeleteAcc').modal('show');
  });
  $(".stop-acc").on("click", function(event){
      $('#popupRemoveBanAcc').modal('show');
  });

  // popup coin
  $(".open-popupcoin").on("click", function(event){
      $('#popupPopupCoin').modal('show');
  });
  $(".head-voucher").on("click", function(event){
      $('#popupheadvoucher').modal('show');
  });

  // massage
  $(".add-massage").on("click", function(event){
      $('#popupaddmassage').modal('show');
  });

  $('.search input').on('keyup',function() {
    if($(this).val() != '') {
        $(".search").addClass('show-button');
    }else{
        $(".search").removeClass('show-button');
    }
});

  // add inout item
    function totalinput() {
      var totalinput = ($(".add-textbox").find('input').length)/2;
      if(totalinput >= 10){
      	$('.add-input').attr('disabled', true);
      }
      $(".total-input").append(totalinput);
      $(".add-textbox .add-input").click(function(){
        $(".add-textbox .input-box").append('<input class="form-control" id="title'+(totalinput+1)+'" name="title" type="text" placeholder="문구를 입력하세요"></input><input class="form-control" id="link'+(totalinput+1)+'" name="link" type="text" placeholder="링크를 입력하세요"></input>');
        $('.total-input').html(function(i, totalinput) { return totalinput*1+1 });
        var total_input = ($(".add-textbox").find('input').length)/2;
        if(total_input >= 10){
        	$('.add-input').attr('disabled', true);
        }
      });
    }

  $(window).on('load', function() {
      totalinput();
    });
  
  // preview
  // preview
  (function($) {
    function preview() {
      var imgbg = $( ".item4 .preview-image" ).html();
      $(".item2 .preview-image").clone().appendTo(".preview-logo");
      $(".item4 .preview-image").clone().appendTo(".preview-contetnt");
      var totalinput = ($(".add-textbox").find('input').length)/2;
      var html='';
      for(var i=1; i<= totalinput; i++){
       var title = $('#title'+i).val();
       var link = $('#link'+i).val();
       if(link === ""){
        html=html+'<div class="pro-view"><p>'+title+'</p></div>';
       }else{
        html=html+'<div class="pro-view"><p>'+title+'</p><a href="'+link+'">자세히 보기</a></div>';
       }
      }
      $(".preview-ctbox").empty();
      $(".preview-ctbox").html(html);
      $(".preview .click-review").click(function(){
        $(".preview-logo").empty();
        $(".item2 .preview-image").clone().appendTo(".preview-logo");
        $(".preview-contetnt").empty();
        $(".item4 .preview-image").clone().appendTo(".preview-contetnt");
        
        var totalinput = ($(".add-textbox").find('input').length)/2;
        var html='';
        for(var i=1; i<= totalinput; i++){
         var title = $('#title'+i).val();
         var link = $('#link'+i).val();
         if(link === ""){
          html=html+'<div class="pro-view"><p>'+title+'</p></div>';
         }else{
          html=html+'<div class="pro-view"><p>'+title+'</p><a href="'+link+'">자세히 보기</a></div>';
         }
        }
        $(".preview-ctbox").empty();
        $(".preview-ctbox").html(html);
        // $(".preview-logo").clone().appendTo(".preview-image");
        // $(".add-textbox .input-box").append('<input class="form-control" type="text"></input>');
        // $('.total-input').html(function(i, totalinput) { return totalinput*1+1 });
        
      });
    }

    $(window).on('load', function() {
      preview();
    });


  })(jQuery);

  // input radio check
  $('.member-sub input.radio1:radio').change(function () {
    if ($(this).is(":checked")) {
        $(".member-sub").addClass('show-item1');
        $(".member-sub").removeClass('show-item2');
    }
    else {
        
    };
  });
  $('.member-sub input.radio2:radio').change(function () {
    if ($(this).is(":checked")) {
        $(".member-sub").addClass('show-item2');
        $(".member-sub").removeClass('show-item1');
    }
    else {
        
    };
  });

  // input radio bussiness sub detail
  $('.check-itemselect input.radio1:radio').change(function () {
    if ($(this).is(":checked")) {
        $(".show-radiobusiness-detail").addClass('show-item1');
        $(".show-radiobusiness-detail").removeClass('show-item2');
    }
    else {
        
    };
  });
  $('.check-itemselect input.radio2:radio').change(function () {
    if ($(this).is(":checked")) {
        $(".show-radiobusiness-detail").addClass('show-item2');
        $(".show-radiobusiness-detail").removeClass('show-item1');
    }
    else {
        
    };
  });

  // input radio request list
  $('.radio-check-request-list input.radio1:radio').change(function () {
    if ($(this).is(":checked")) {
     $("#buttonSubmitRequestCounselor").removeClass("disabled")
        $(".request-list-radio1").addClass('show-radio1');
        $(".request-list-radio2").removeClass('show-radio2');
    }
    else {
        
    };
  });
  $('.radio-check-request-list input.radio2:radio').change(function () {
    if ($(this).is(":checked")) {
     $("#buttonSubmitRequestCounselor").removeClass("disabled")
        $(".request-list-radio1").removeClass('show-radio1');
        $(".request-list-radio2").addClass('show-radio2');
    }
    else {
        
    };
  });

  $(document.body).on('click', '.approval-request-detail .form-footer .v1', function(e) {
      $(".form-footer1.style1").addClass('show');
      $(".approval-request-detail .form-footer .v1").addClass('show-before red');
      $(".form-footer1.style2").removeClass('show');
      $(".approval-request-detail .form-footer .v2").removeClass('show-before red');
  });
  $(document.body).on('click', '.approval-request-detail .form-footer .v2', function(e) {
      $(".form-footer1.style2").addClass('show');
      $(".approval-request-detail .form-footer .v2").addClass('show-before red');
      $(".form-footer1.style1").removeClass('show');
      $(".approval-request-detail .form-footer .v1").removeClass('show-before red');
  });

  (function($) {
    function inputuploadimg() {
        if (!$('.tr-file-upload.image-upload').length) return;

        $('.tr-file-upload.image-upload').each(function() {
          var fileUpload = $(this);
          var previewImage = fileUpload.find('.preview-image > img');
          var previewImageBg = fileUpload.find('.preview-image > .image-bg');

          previewImage.attr('src') == '' ? previewImage.closest('.preview-image').addClass('no-image') : previewImage.closest('.preview-image').removeClass('no-image');
          previewImageBg.attr('style') == undefined ? previewImageBg.closest('.preview-image').addClass('no-image') : previewImageBg.closest('.preview-image').removeClass('no-image');
          
          var readURL = function(input) {
            if (input.files && input.files[0]) {
              var reader = new FileReader();

              reader.onload = function(e) {
                previewImage.attr('src', e.target.result);
                previewImageBg.attr('style', 'background-image: url("' + e.target.result + '");');
              }

              reader.readAsDataURL(input.files[0]);
            }
          };

          fileUpload.find('.input-file').on('change', function() {
            readURL(this);
          });
        });
    }
    function addcolum() {
      
      $(".add-field").click(function(){
        // $(this).closest(".program-detail div.md-ct .item-img-section").empty();
        $(".coun-field .link-dowload").addClass('show-button');
        $(".coun-field .add-item-field").append('<tr class="add-select"><td><input type="text" name="name" data-required="1" class="form-control"/></td><td><div class="inputfile busine-detail"><div class="tr-file-upload image-upload"><label class="btn green btn-select-file">찾아보기<input type="file" class="hidden input-file"></label><div class="preview-image"><img src="../../assets/images/demo_single_2.jpg" alt="thumbnail"></div></div></div></td></td><td><div class="inputfile busine-detail"><div class="tr-file-upload image-upload"><label class="btn green btn-select-file">찾아보기<input type="file" class="hidden input-file"></label><div class="preview-image"><img src="../../assets/images/demo_single_2.jpg" alt="thumbnail"></div></div></div></td><td><select name="selecttext" class="form-control selectpickermember selectpickeraddclass pull-right addclas-on"><option value="0">Yes</option><option value="1">No</option></select></td><td class="show-text1"><span class="text1">보험사</span><select name="selecttext" class="form-control selectpickermember pull-right show-select1"><option value="0">심리적 증상</option><option value="1">심리적 증상</option></select></td><td><select name="selecttext" class="form-control selectpickermember pull-right"><option value="0">Yes</option><option value="1">No</option></select></td><td><select name="selecttext" class="form-control selectpickermember pull-right"><option value="0">심리적 증상</option><option value="1">심리적 증상</option></select></td></tr>');
        $('.selectpickermember').selectpicker('refresh');
         inputuploadimg(); 
      });
    }

    $(window).on('load', function() {
      addcolum();
    });

  })(jQuery);


  
  (function($) {
    $(document.body).on('change', '.selectpickeraddclass', function() {
      var valoption = $('.selectpickeraddclass').selectpicker('val');
      alert(valoption);
      if(valoption = 1){
        $(".show-text1").addClass('show-select');
        // alert(valoption);
      }
      else{
        $("show-text1").removeClass('show-select');
        // alert(valoption);
      }
    });
   })(jQuery);

   $(".checkall label input").click(function () {
    if ($(this).is(":checked")){
      var isAllChecked = 0;
      $(".checkall label input").each(function(){
        if(!this.checked)
           isAllChecked = 1;
      })              
      if(isAllChecked == 0){ 
        $(".checkall input#all").prop("checked", true); 
        $.uniform.update('.checkall input#all');
      }     
    }else {
      $(".checkall input#all").prop("checked", false);
      $.uniform.update('.checkall input#all');
    }
  });


   var status = $('.checkall input#all').is(':checked');
   if(status == true){
    $('.checkall label input').attr('checked', status);
    $.uniform.update('.checkall label input');
   }
   else{
    
   }
   
   $('.checkall input#all').bind('click', function(){
      var status = $(this).is(':checked');
      $('.checkall label input').attr('checked', status);
      $.uniform.update('.checkall label input');
    });

   $('#popupGiveRetrieveHelloCoin').bind('click', function(){
      var status = $(this).is(':checked');
      $('.checkall label input').attr('checked', status);
      $.uniform.update('.checkall label input');
    });

   //tooltip
   $(function () {
      $('[data-toggle="tooltip"]').tooltip({html:true})
    })


   // slide 
    // $('.slide-map .item-map').first().addClass('active');
    // $('.slide-map .item-map').hide();    
    // $('.slide-map .item-map.active').show();

    // $('.prenext button.next').click(function(){

    // $('.slide-map .item-map.active').removeClass('active').addClass('oldActive');    
    //    if ( $('.oldActive').is(':last-child')) {
    //       $('.slide-map .item-map').first().addClass('active');
    //     }
    //     else{
    //       $('.oldActive').next().addClass('active');
    //     }
    // $('.oldActive').removeClass('oldActive');
    // $('.slide-map .item-map').fadeOut();
    // $('.slide-map .item-map.active').fadeIn();
        
        
    // });
    
    //  $('.prenext button.pre').click(function(){
    //   $('.slide-map .item-map.active').removeClass('active').addClass('oldActive');    
    //          if ( $('.oldActive').is(':first-child')) {
    //       $('.slide-map .item-map').last().addClass('active');
    //       }
    //          else{
    //   $('.oldActive').prev().addClass('active');
    //          }
    //   $('.oldActive').removeClass('oldActive');
    //   $('.slide-map .item-map').fadeOut();
    //   $('.slide-map .item-map.active').fadeIn();
    // });

  // push sending page
  $("#slide-push .owl-carousel").owlCarousel({
      singleItem: true,
      pagination: true,
      autoPlay: false,
      stopOnHover:false,
      addClassActive: true,
      touchDrag       :   false,
      mouseDrag       :   false,
      nav             :   false,
  });

  $('#checked-push input').change(function () {
    if ($(this).is(":checked")) {
        $("#checked-push").addClass('active');
        $("#tab-none").addClass('active');
    }
    else {
        $("#checked-push").removeClass('active');
        $("#tab-none").removeClass('active');
    };
  });
  // faq

  
  (function($) {
    function addcolumfaq() {
      $(".add-faq").click(function(){
        $(".faq-content ul.add-colum-faq").prepend('<li><div class="col-md-1"><label class="check"><input type="checkbox" value="1" name="service"/></label></div><div class="col-md-9 text-center borderfull"><div class="title"><input type="text" name="name" data-required="1" class="form-control"/></div><div class="content"><textarea name="editor2" rows="4" class="form-control"></textarea></div></div><div class="col-md-2 text-center"><div class="radio-list"><label><input type="radio" name="membership" value="1"/> ON</label><label><input type="radio" name="membership" value="2" checked /> OFF</label></div></div></li>');
         // $.uniform.update('.radio-list label input');
         $('.radio-list label input').uniform('refresh');
         $('.check input').uniform('refresh');
      });
    }
    $(window).on('load', function() {
      addcolumfaq();
    });
   })(jQuery);

        

    
    // form date
    $(document.body).on('click', '.form-date .btn.btn-block1', function(e) {
      if( $('.form-date .block1').hasClass('active-block') & $(this).hasClass('green')){
        $(".btn-block1 input").prop("checked", true);
        $('.form-date .block2').removeClass('active-block');
        $('.form-date .block3').removeClass('active-block');
        $('.btn-block2').removeClass('green');
        $('.btn-block3').removeClass('green');
        $.uniform.update('.form-date label input');
      }else{
        $(this).addClass('green');
        $(".btn-block1 input").prop("checked", true);
        $('.form-date .block1').addClass('active-block');
        $('.form-date .block2').removeClass('active-block');
        $('.form-date .block3').removeClass('active-block');
        $('.btn-block2').removeClass('green');
        $('.btn-block3').removeClass('green');
        $.uniform.update('.form-date label input');
      }
        
    });
    //block2
    $(document.body).on('click', '.form-date .btn.btn-block2', function(e) {
      if( $('.form-date .block2').hasClass('active-block') & $(this).hasClass('green')){
        $(".btn-block2 input").prop("checked", true);
        $('.form-date .block1').removeClass('active-block');
        $('.form-date .block3').removeClass('active-block');
        $('.btn-block1').removeClass('green');
        $('.btn-block3').removeClass('green');
        $.uniform.update('.form-date label input');
      }else{
        $(this).addClass('green');
        $(".btn-block2 input").prop("checked", true);
        $('.form-date .block2').addClass('active-block');
        $('.form-date .block1').removeClass('active-block');
        $('.form-date .block3').removeClass('active-block');
        $('.btn-block1').removeClass('green');
        $('.btn-block3').removeClass('green');
        $.uniform.update('.form-date label input');
      }
        
    });
    //block3
    $(document.body).on('click', '.form-date .btn.btn-block3', function(e) {
      if( $('.form-date .block3').hasClass('active-block') & $(this).hasClass('green')){
        $(".btn-block3 input").prop("checked", true);
        $('.form-date .block2').removeClass('active-block');
        $('.form-date .block1').removeClass('active-block');
        $('.btn-block2').removeClass('green');
        $('.btn-block1').removeClass('green');
        $.uniform.update('.form-date label input');
      }else{
        $(this).addClass('green');
        $(".btn-block3 input").prop("checked", true);
        $('.form-date .block3').addClass('active-block');
        $('.form-date .block2').removeClass('active-block');
        $('.form-date .block1').removeClass('active-block');
        $('.btn-block2').removeClass('green');
        $('.btn-block1').removeClass('green');
        $.uniform.update('.form-date label input');
      }
        
    });

    // check validate file upload avatar #popupcontent2file
    // $(document.body).on('click', '#popupcontent2file .btn-confirm', function(e) {
    //   if ($('#popupcontent2file .vali-file1').html().length > 0 && $('#popupcontent2file .vali-file2').html().length > 0 ) {
    //     if( $('#popupcontent2file .bs-example').hasClass('hidden')){
    //     }
    //     else{
    //       $('#popupcontent2file .bs-example').addClass('hidden');
    //     }
    //   }else{
    //     $('#popupcontent2file .bs-example').removeClass('hidden');
    //   }
        
    // });


});



