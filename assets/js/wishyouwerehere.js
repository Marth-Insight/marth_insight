$(function () {
    $('#btn-trivia').click(function () {
        var displayed = $('div#pop-up').css('display');
        if(displayed == 'block') {
            $('div#pop-up').fadeOut('slow');
        } else {
            $('div#pop-up').fadeIn('slow');
        }
    });
    
   $('img#animated-mars').ready(function () { 
        $('img#animated-mars').animate({
            /*height: '700px',
            width: '700px',
            marginLeft: '-192px',*/
            marginTop: '350px'
        }, 4000, 'swing', function () {
            $('h1#title').fadeIn('slow');
            $('h4#sub-title').fadeIn('slow');
        });
    });
});