function reloadListData() {
    loadData(1);
}

function initPageUiEvent() {
    var tagEles = $("#tagSection div.chip");
    $.each(tagEles,function(idx,tag){
        $(tag).css("cursor","pointer").attr("title","点击查看该分类视频").off('click').click(function(){
            var _tag = $(this);
            var isActive = _tag.hasClass("btn-danger");
            tagEles.removeClass("btn-danger").addClass("btn-primary");
            if (isActive) {
                _tag.removeClass("btn-danger").addClass("btn-primary");
            } else {
                _tag.addClass("btn-danger").removeClass("btn-primary");
            }
            loadData(1);
        });
    });

    var keywordEles = $("#keywordSection div.chip");
    $.each(keywordEles,function(idx,keyword){
        $(keyword).css("cursor","pointer").attr("title","点击查看该关键词视频").off('click').click(function(){
            var _keyword = $(this);
            var isActive = _keyword.hasClass("btn-danger");
            keywordEles.removeClass("btn-danger").addClass("btn-primary");
            if (isActive) {
                _keyword.removeClass("btn-danger").addClass("btn-primary");
            } else {
                _keyword.addClass("btn-danger").removeClass("btn-primary");
            }
            loadData(1);
        });
    });
}

var loadData = function (page) {
    var activeTag = $("#tagSection div.btn-danger");
    var activeKeyword = $("#keywordSection div.btn-danger");
    console.debug('activeTag='+activeTag+",len="+activeTag.length);
    console.debug('activeKeyword='+activeKeyword+",lne="+activeKeyword.length);
    var tagId = activeTag.length > 0 ? activeTag.attr("data-key") : "";
    var kwId = activeKeyword.length > 0 ? activeKeyword.attr("data-key") : "";
    var url = "/videoext/list/" + page + "?&tagId=" + tagId + "&keywordId=" + kwId;
    loadContent("#contentSection",url);
}

$(function () {
    initPageUiEvent();
    reloadListData();
});