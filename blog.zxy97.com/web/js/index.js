var clickOrTouch = function(touchType) {
    touchType = touchType || "touchend";
    var eventType = "click";

    try {
        document.createEvent("TouchEvent");
        eventType = touchType;
    } catch (e) {
    }

    return eventType;
};

$(function() {

    $("a[href*=#]").bind(clickOrTouch(), function() {
        if (location.pathname.replace(/^\//, "") === this.pathname.replace(/^\//, "") && location.hostname === this.hostname)
        {
            var hash = this.hash;
            var target = $(hash);
            target = target.length && target || $("[name=" + hash.slice(1) + "]");

            if (target.length)
            {
                $("html, body").animate({
                    scrollTop: target.offset().top
                }, 800);

                return false;
            }
        }
    });
});