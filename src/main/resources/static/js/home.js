$(document).ready(function() {
    let scrollPos = 0;
    const $mainNav = $('#mainNav');
    const headerHeight = $mainNav.height(); 
    $(window).scroll(function() {
        const currentTop = $('body').offset().top * -1;  
        if (currentTop < scrollPos) {
            if (currentTop > 0 && $mainNav.hasClass('is-fixed')) {
                $mainNav.addClass('is-visible');
            } else {
                $mainNav.removeClass('is-visible is-fixed');
            }
        } else {
            $mainNav.removeClass('is-visible');           
            if (currentTop > headerHeight && !$mainNav.hasClass('is-fixed')) {
                $mainNav.addClass('is-fixed');
            }
        }     
        scrollPos = currentTop;
    });
});


function goBack() {
	window.history.back();
}
