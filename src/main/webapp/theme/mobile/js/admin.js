$(document).ready(function() {


  if ($('.selectpicker').length) {
    $('.selectpicker').selectpicker({
        style: 'btn-info',
        size: 4
      });
  } 
  

  // menu
  // $(function() {
  //   $('nav#menu').mmenu();
  // });


    $('input.addclass-checkbox:checkbox').change(function () {
      if ($(this).is(":checked")) {
          $(this).closest("div.checkbox-login").addClass('active');
      }
      else {
          $(this).closest("div.checkbox-login").removeClass('active');
      };
    });

    (function($) {
    if (!$("[data-tr-placeholder]").length) return;

    $("[data-tr-placeholder]").each(function() {
      var placeholderContent = $(this).attr("data-tr-placeholder");
      $(this).attr("placeholder", placeholderContent);

      $(this).on("focus", function() {
        $(this).attr("placeholder", "");
      });

      $(this).on("blur", function() {
        $(this).attr("placeholder", placeholderContent);
      });
    });
  })(jQuery);

  $.validator.addClassRules({
    vali:{
      required: true
    }

  });

  $(".login-form .sumbit-validate").click(function() {
    $(".login-form").validate({
      rules: {
        username: "required",
        password: "required"
      },
      messages: {
        username: "아이디를 입력해주세요.",
        password: "비밀번호를 입력해주세요."
      }

    });
  });

  


  var height = $( window ).height();
  var width = $( window ).width();
  if (height > width) {
      $('body').css({"height": height});
  }
  else {
      $('body').addClass('vertical');
  };
  

  var callbacks_list = $('.demo-callbacks ul');
  $('#input-2').on('ifCreated ifClicked ifChanged ifChecked ifUnchecked ifDisabled ifEnabled ifDestroyed', function(event){
    callbacks_list.prepend('<li><span>#' + this.id + '</span> is ' + event.type.replace('if', '').toLowerCase() + '</li>');
  }).iCheck({
    checkboxClass: 'icheckbox_square-blue',
    radioClass: 'iradio_square-blue',
    increaseArea: '20%'
  });

  $('#input-1').on('ifCreated ifClicked ifChanged ifChecked ifUnchecked ifDisabled ifEnabled ifDestroyed', function(event){
    callbacks_list.prepend('<li><span>#' + this.id + '</span> is ' + event.type.replace('if', '').toLowerCase() + '</li>');
  }).iCheck({
    checkboxClass: 'icheckbox_square-blue',
    radioClass: 'iradio_square-blue',
    increaseArea: '20%'
  });

  $('#input-3').on('ifCreated ifClicked ifChanged ifChecked ifUnchecked ifDisabled ifEnabled ifDestroyed', function(event){
    callbacks_list.prepend('<li><span>#' + this.id + '</span> is ' + event.type.replace('if', '').toLowerCase() + '</li>');
  }).iCheck({
    checkboxClass: 'icheckbox_square-blue',
    radioClass: 'iradio_square-blue',
    increaseArea: '20%'
  });

  $('#input-4').on('ifCreated ifClicked ifChanged ifChecked ifUnchecked ifDisabled ifEnabled ifDestroyed', function(event){
    callbacks_list.prepend('<li><span>#' + this.id + '</span> is ' + event.type.replace('if', '').toLowerCase() + '</li>');
  }).iCheck({
    checkboxClass: 'icheckbox_square-blue',
    radioClass: 'iradio_square-blue',
    increaseArea: '20%'
  });

  $('#input-5').on('ifCreated ifClicked ifChanged ifChecked ifUnchecked ifDisabled ifEnabled ifDestroyed', function(event){
    callbacks_list.prepend('<li><span>#' + this.id + '</span> is ' + event.type.replace('if', '').toLowerCase() + '</li>');
  }).iCheck({
    checkboxClass: 'icheckbox_square-blue',
    radioClass: 'iradio_square-blue',
    increaseArea: '20%'
  });

  $('#input-6').on('ifCreated ifClicked ifChanged ifChecked ifUnchecked ifDisabled ifEnabled ifDestroyed', function(event){
    callbacks_list.prepend('<li><span>#' + this.id + '</span> is ' + event.type.replace('if', '').toLowerCase() + '</li>');
  }).iCheck({
    checkboxClass: 'icheckbox_square-blue',
    radioClass: 'iradio_square-blue',
    increaseArea: '20%'
  });
    
});