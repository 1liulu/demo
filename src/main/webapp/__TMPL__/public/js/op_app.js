// Load in HTML templates

var viewportTemplate; // the viewport template
loadTemplate("__TMPL__/public/tp/viewport.html", function (element) {
    viewportTemplate = element;
});

var studyViewerTemplate; // the study viewer template
loadTemplate("__TMPL__/public/tp/studyViewer.html", function (element) {
    studyViewerTemplate = element;
});


// Prevent scrolling on iOS
document.body.addEventListener('touchmove', function (e) {
    e.preventDefault();
});
