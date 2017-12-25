
$(document).ready(function() {

  if ($('#schedule-content').length) {
    $("body").addClass('schedule-page');
  }

  if ($('#popupEditMenu').length) {
    $(function () {
      // $("#popupEditMenu .md-content ul.v1 li").click(function(e){
      $(document.body).on('click', '#popupEditMenu .md-content ul.v1 li', function(e) {
        e.preventDefault();
        var count = $('#popupEditMenu .md-content ul.v2 li').length;
        if (count < 6) {
          if( $(this).hasClass('activev1') ){
           $(this).removeClass('activev1');
            $('span.icon.v1').removeClass('active-menu');  
          }else{
           $('#popupEditMenu .md-content ul.v1 li').removeClass('activev1');
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
        $('#popupEditMenu .md-content ul.v1 li').removeClass('activev2');
        $('#popupEditMenu span.icon.v2').removeClass('active-menu');
      });

    });
  } 

  // add height
  
  if ($('body.face-talk').length) {
    var heightmain = $(".page-content-wrapper").height();
    // $('.item-img-section').css({"height": widthimg, "line-height": widthimg+'px'});
    $('.sidebar-left').css({"height": heightmain});
    // $('.item-img-section').css({"line-height": widthimg+'px'})
  }
   


  // jquery page mamber

  $('.selectpickermember').selectpicker({
    style: 'btn-info',
    size: 4
  });
  
  $(".ban-acc").on("click", function(event){
      $('#popupBanAcc').modal('show');
  });
  $(".delete-acc").on("click", function(event){
      $('#popupDeleteAcc').modal('show');
  });
  $(".stop-acc").on("click", function(event){
      $('#popupRemoveBanAcc').modal('show');
  });

  // show popup affter login
  // $('#popupfirstlogin').modal('show');

  // show dropdow login
  // $('.dropdown').addClass('open');

  $(".acc-content .submit").on("click", function(event){
      var name = $(".acc-content input[name='username']").val();
      if (name.length) {
        $(".acc-content").removeClass("error");
      }
      else{
        $(".acc-content").addClass("error");
      }
  });

  //schedule
  (function($) {
    function addtitleschedule() {
      // var totalinput = $(".add-textbox").find('input').length;
      // $(".total-input").append(totalinput);

      $("#popupBasic a.title-schedule").click(function(){
        var textinput = $("#popupBasic input").val();
        $("td#idschedule .title-schedule").append(textinput);
        $("td#idschedule").append('<div class="resizable" id="resizable"></div><div class="option-item" style="top: 30px;height: 60px;z-index: 9;"></div>');
        // $(this).closest("td").append('<div class=""><div class="add-title"><span class="close-add-title"></span><h4>Note add title schedule</h4><div class="add-content"><input type="text" name="title" data-required="1" placeholder="Title..." class="form-control"></div><button class="btn green">Add Title</button></div>');
        // $('.total-input').html(function(i, totalinput) { return totalinput*1+1 });
        
      });
    }

    $(window).on('load', function() {
      addtitleschedule();
    });


  })(jQuery);

  $(document.body).on('hover', '#resizable', function() {
    $(".resizable").resizable({
      handles: 's',
      minHeight: 28,
      grid: [10000, 30],
      stop: function(event, ui) {
          $(this).css("width", '');
      }
    });
  });

  // $( "#myPopupDiv" ).popup( "open" )
  $(document.body).on('click', '#resizable', function() {
    $( "#popup-confirm-title" ).popup( "open" )
  });

  

  
  // add inout item

  (function($) {
    function totalinput() {
      var totalinput = $(".add-textbox").find('input').length;
      $(".total-input").append(totalinput);

      $(".add-textbox .add-input").click(function(){
        $(".add-textbox .input-box").append('<input class="form-control" name="link" type="text"></input>');
        $('.total-input').html(function(i, totalinput) { return totalinput*1+1 });
        
      });
    }

    $(window).on('load', function() {
      totalinput();
    });


  })(jQuery);

  // preview
  (function($) {
    function preview() {
      var imgbg = $( ".item4 .preview-image" ).html();
      $(".item2 .preview-image").clone().appendTo(".preview-logo");
      $(".item4 .preview-image").clone().appendTo(".preview-contetnt");
      $(".add-textbox .input-box").clone().appendTo(".preview-ctbox");
      $(".preview .click-review").click(function(){
        $(".preview-logo").empty();
        $(".item2 .preview-image").clone().appendTo(".preview-logo");
        $(".preview-contetnt").empty();
        $(".item4 .preview-image").clone().appendTo(".preview-contetnt");
        $(".preview-ctbox").empty();
        $(".add-textbox .input-box").clone().appendTo(".preview-ctbox");
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
        $(".member-sub").removerClass('show-item1');
    }
    else {
        
    };
  });

  // hover menu page
  $(function () {
      $(document.body).on('hover', '.page-sidebar-menu li.menu1', function(e) {
        $('.page-dropdow-menu .menu1').toggleClass('active');
      });
    });

  (function($) {
      
    function widthbody() {
      //var widthbody = $( window ).height() - 320;

        // $('.item-img-section').css({"height": widthimg, "line-height": widthimg+'px'});
        //$('.face-video').css({"height": widthbody});
        // $('.item-img-section').css({"line-height": widthimg+'px'});
    }

    $(window).on('load', function() {
      widthbody();
    });
  })(jQuery);

  (function($) {
      
    function heightbody() {
      var heightbody = $( window ).height();
        $('.menu-right').css({"height": heightbody});
    }

    $(window).on('load', function() {
      heightbody();
    });
  })(jQuery);

  // fix video position center
  (function($) {
      
    function heighvideo() {
      var heighvideo = $('.position-center').height();
        $('.bg-video.position-center').css({"height": heighvideo});
        $('.face-video').css({"height": heighvideo});
    }

    $(window).on('load', function() {
      heighvideo();
    });
  })(jQuery);

  $(".icon-next a").on("click", function(event){
      $('body.face-talk').toggleClass('face-talk-menu-right');
  });


  $(".close-facetalk").on("click", function(event){
      $('#popupLogout-facetalk').modal('show');
  });
  $(".close-facetalk1").on("click", function(event){
      $('#popupList-facetalk').modal('show');
  });
  $(".close-facetalk2").on("click", function(event){
      $('#popupListv2-facetalk').modal('show');
  });
  $(".open-chatface").on("click", function(event){
      $('#popupList-facechat').modal('show');
  });
  $(".popup-psychlogical1").on("click", function(event){
      $('#popup-psychlogical').modal('show');
  });

  $(".popup-psychlogical2").on("click", function(event){
      $('#popup-psychlogical2').modal('show');
  });

  $(".popup-psychlogical3").on("click", function(event){
      $('#popup-psychlogical3').modal('show');
  });

  $(".sent-mail").on("click", function(event){
      $('#popup-psychlogical3').modal('show');
  });

  $(".massage .openpopup-confim").on("click", function(event){
      $('#popup-massage').modal('show');
  });

  $(".inquiry h4").on("click", function(event){
      $('#popup-inquiry').modal('show');
  });
  $(".popup-confim-schedule").on("click", function(event){
      $('#popup-confim-schedule').modal('show');
  });
  $(".login-contact .popup-faq").on("click", function(event){
      $('#popup-faq').modal('show');
  });

  $('.popup-register').modal('show');

  $(".open-popup-calenda").on("click", function(event){
      $('#popup-choiceDate').modal('show');
      // $( "#datepicker-popup" ).datepicker({
      //     altField: "#datepickerpopup #date"
      // });
  });

  //check all checkbox
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

    $('#massage-all-checkbox').bind('click', function(){
      var status = $(this).is(':checked');
      $('.item-checkbox').attr('checked', status);
      $.uniform.update('.item-checkbox');
    });

  (function($){
    $('#datepickerpopup').datepicker();

  })(jQuery);

  // CHECK TON TAI
  $("#owl-demo").owlCarousel({
      singleItem: true,
      pagination: true,
      autoPlay: false,
      stopOnHover:true,
      navigation: true,
      addClassActive: true
  });

  // CHECK TON TAI
  $("#slide-login .owl-carousel").owlCarousel({
      singleItem: true,
      pagination: true,
      autoPlay: false,
      stopOnHover:true,
      nav:true,
      addClassActive: true
  });

  // slide schedule
  $(".schedule-main-content .owl-carousel").owlCarousel({
      singleItem: true,
      pagination: true,
      autoPlay: false,
      stopOnHover:true,
      nav:true,
      addClassActive: true,
      responsive:{
        0:{
            items:1
        }
    }
  });

  $("#slide-register .owl-carousel").owlCarousel({
      singleItem: true,
      pagination: true,
      autoPlay: false,
      stopOnHover:true,
      nav:true,
      addClassActive: true
  });

  //update jquery checked
  // $('#test-check1').prop('checked',true);
  // $.uniform.update('#test-check1');

  $(function () {
    $(document.body).on('change', '#popup-psychlogical2 .radio-checkboxstyle2 input', function(e) {
      var yes = $('#popup-psychlogical2 .radio-checkboxstyle2 input:checked').length
      $("#total-checkall").html(yes);
    });
  });
  // $('input').change(function(){
  //       var yes = $('.yes:checked').length
  //       var no = $('.no:checked').length 
  //       $('.yes_results').text(yes)
  //       $('.no_results').text(no)                        
  //   }) #total-checkall

});

