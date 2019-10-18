function intPagerData(){
}

function viewFileDetail() {
    loadMainContent("/file/play/detail/"+viewFilePage.id);
}
function downloadFile() {
    window.open(viewFilePage.ossPath);
}

$(function () {
    intPagerData();
});
