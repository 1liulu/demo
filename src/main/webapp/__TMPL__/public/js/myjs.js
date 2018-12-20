!(function(){
   $('.top_main .img').on('click',function(){
      history.back();
   })

   $('.mywarp .bottom').on('click',function(){
    var d=$('.mywarp .btn').css('display');
    if(d=='none'){
       $('.mywarp .btn').fadeIn(100);
       $('.mywarp .btn .btn_m').addClass('active');
    }else{
       $('.mywarp .btn .btn_m').removeClass('active');
       $('.mywarp .btn').fadeOut(300);
    }
   })

   $('.mywarp .btn').on('click',function(e){
    if(!$('.mywarp .bottom').is(e.target) && $('.check_authorization .bottom').has(e.target).length === 0){
        $('.mywarp .btn .btn_m').removeClass('active');
        $('.mywarp .btn').fadeOut(300);
    }
   })
   
   

})()
