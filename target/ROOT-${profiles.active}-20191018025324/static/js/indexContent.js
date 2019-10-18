

$(function () {
    //底部走马灯
    $('.slider').slider();

    $('#start-button').off('click').click(function () {
            setTimeout(function () {
                loadLoginForm();
            },300);
        }
    );
});