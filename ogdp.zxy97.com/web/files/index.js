webpackJsonp([0], [function(t, i, e) {
        function n() {
            o(document).on("click", "[xl-xla-type]", function() {
                var t = o(this), i = t.attr("xl-xla-category"), e = t.attr("xl-xla-action"), n = t.attr("xl-xla-extdata");
                window.xla.push({type: "event", category: i, action: e, extdata: n});
            });
        }
        function a() {
            var t = o("#nav_box"), i = t.find("li a"), e = t.find(".ic_line");
            t.on("mouseleave", function() {
                e.hide();
            }), i.on("mouseenter", function() {
                for (var t = i.index(o(this)), n = i.eq(t).width(), a = 0, s = 0; s < t; s++)
                    a += i.eq(s).outerWidth() + 20;
                a += 16, e.show(), e.css({left: a, width: n});
            });
            var n, a = o("#wrap"), s = function() {
                var t = document.createElement("div").style;
                for (var i in t)
                    if (i.toLowerCase().indexOf("animation") >= 0)
                        return!0;
                return!1;
            }(), h = o("#page_wp").find(".page"), r = o("#star_wp"), c = o("#btn_control").find("a"), p = [5e3, 5e3, 5e3, 1e4];
            if (s)
                a.addClass("css3");
            else {
                for (var d = h.find(".img_box"), f = 1; f < 5; f++)
                    d.eq(f - 1).find("img")[0].src = "img/0" + f + ".jpg";
                window.onresize = function() {
                    var t = o(window).width(), i = o(window).height();
                    t / i < 1920 / 1080 ? d.height(i).css({width: "auto", margin: -.5 * i + "px 0 0 " + -960 / 1080 * i + "px"}) : d.width(t).css({height: "auto", margin: -.5 * t * 1080 / 1920 + "px 0 0 " + -.5 * t + "px"});
                };
            }
            var l = {one: function() {
                    h.removeClass("show").eq(0).addClass("show"), s ? (r.removeClass("show"), cvsEffect.one()) : h.stop().eq(0).css({opacity: 0}).animate({opacity: 1}, 500);
                }, two: function() {
                    s ? (h.eq(0).hasClass("show") && h.eq(0).addClass("hide"), r.removeClass("show"), cvsEffect.two(), setTimeout(function() {
                        h.eq(0).removeClass("hide");
                    }, 400)) : h.stop().eq(1).css({opacity: 0}).animate({opacity: 1}, 500), h.removeClass("show").eq(1).addClass("show");
                }, three: function() {
                    h.removeClass("show").eq(2).addClass("show"), s ? (cvsEffect.three(), r.addClass("show")) : h.stop().eq(2).css({opacity: 0}).animate({opacity: 1}, 500);
                }, four: function() {
                    h.removeClass("show").eq(3).addClass("show"), s ? (cvsEffect.four(), r.addClass("show")) : h.stop().eq(3).css({opacity: 0}).animate({opacity: 1}, 500);
                }}, u = {next: function() {
                    var t = h.index(o("#page_wp").find(".page.show"));
                    t = (t + 1) % h.length, u.changeByIdx(t);
                }, prev: function() {
                    var t = h.index(o("#page_wp").find(".page.show"));
                    t = (t + h.length - 1) % h.length, u.changeByIdx(t);
                }, changeByIdx: function(t) {
                    switch (clearTimeout(n), t) {
                        case 0:
                            l.one();
                            break;
                        case 1:
                            l.two();
                            break;
                        case 2:
                            l.three();
                            break;
                        case 3:
                            l.four();
                    }
                    c.removeClass("cur").eq(t).addClass("cur"), u.changeInterval();
                }, changeInterval: function() {
                    var t = h.index(o("#page_wp").find(".page.show"));
                    n = setTimeout(u.next, p[t]);
                }};
            c.click(function() {
                var t = c.index(o(this));
                u.changeByIdx(t);
            }), o(document).on("mousewheel DOMMouseScroll", function(t) {
                var i = t.originalEvent.wheelDelta || t.originalEvent.detail * -1;
                i > 0 ? u.prev() : u.next();
            }), s && cvsEffect.start(), u.changeByIdx(0);
        }
        var o = (e(1), e(2));
        e(3), function() {
            a(), s();
            var t = "© 2017-" + (new Date).getFullYear() + " zxy97.com 版权所有 | 陕ICP备18005813号";
            o(".foot > p").text(t), n();
        }();
    }, function(t, i) {
    }, , function(t, i) {
        !function() {
            function t(i, e) {
                this.point = {x: 0, y: 0}, this.point.x = i.x, this.point.y = i.y, this.len = e || 1, this.lWidth = 1, this.speed = u.iSpeed, this.opc = .5, this.isEnd = !1, "function" !== typeof this.nextFps && (t.prototype.nextFps = function() {
                    var t = this.point.y / Math.abs(this.point.y) * this.speed;
                    this.point.x = this.point.x / this.point.y * (this.point.y + t), this.point.y += t, this.speed += u.dSpeed, this.len += .7, Math.abs(this.point.y) > r / 2 * .7 ? this.lWidth = 5 : Math.abs(this.point.y) > r / 2 * .5 ? this.lWidth = 4 : Math.abs(this.point.y) > r / 2 * .3 ? this.lWidth = 3 : Math.abs(this.point.y) > r / 2 * .2 && (this.lWidth = 2), this.opc = this.opc < .1 ? .1 : this.opc - .02, (Math.abs(this.point.x) > h / 2 || Math.abs(this.point.y) > r / 2) && (this.isEnd = !0);
                }), "function" !== typeof this.draw && (t.prototype.draw = function(t) {
                    t.save(), t.globalAlpha = this.opc, t.translate(h / 2, r / 2), t.lineWidth = this.lWidth, t.strokeStyle = "#fff", t.lineCap = "round";
                    var i = u.newPoint(this.point, this.len);
                    t.beginPath(), t.moveTo(this.point.x, this.point.y), t.lineTo(i.x, i.y), t.stroke(), t.restore();
                });
            }
            function i(t) {
                this.point = t || {x: 0, y: 0}, this.rad = Math.random(), Math.random() > .9 && (this.rad = 2), this.speed = 1 * Math.random() + .1, this.opc = Math.random() / 2 + .3, this.R = Math.sqrt(this.point.x * this.point.x + this.point.y * this.point.y), "function" !== typeof this.nextFps && (i.prototype.nextFps = function() {
                    if (1 === w.way) {
                        if (this.point.x === (Math.abs(this.point.x) + this.speed * (Math.abs(this.point.x) / (Math.abs(this.point.x) + Math.abs(this.point.y)))) * (this.point.x / Math.abs(this.point.x)), this.point.y === (Math.abs(this.point.y) + this.speed * (Math.abs(this.point.y) / (Math.abs(this.point.x) + Math.abs(this.point.y)))) * (this.point.y / Math.abs(this.point.y)), this.point.x < h * -.5 || this.point.x > .5 * h || this.point.y < r * -.5 || this.point.y > .5 * r) {
                            var t = Math.min(Math.abs(this.point.x), Math.abs(this.point.y)) / 40;
                            this.point.x /= t, this.point.y /= t;
                        }
                    } else if (0 === w.way) {
                        var i = (this.R - Math.abs(this.point.x)) / this.R * (this.speed + 1 + 3 * Math.random());
                        i = i <= .05 ? .05 : i, i = i >= 1 ? 1 : i, this.point.y > 0 ? this.point.x + i <= this.R ? (this.point.x += i, this.point.y = Math.sqrt(this.R * this.R - this.point.x * this.point.x)) : (this.point.x = this.R, this.point.y = 0) : this.point.x - i >= this.R * -1 ? (this.point.x -= i, this.point.y = Math.sqrt(this.R * this.R - this.point.x * this.point.x) * -1) : this.point.y *= -1;
                    }
                }), "function" !== typeof this.draw && (i.prototype.draw = function(t) {
                    t.save(), t.globalAlpha = this.opc, t.translate(h / 2, r / 2), t.fillStyle = "#fff", t.beginPath(), t.arc(this.point.x, this.point.y, this.rad, 0, 2 * Math.PI), t.fill(), t.restore();
                });
            }
            function e(t) {
                this.point = t || {x: 0, y: 0}, this.rad = Math.random(), Math.random() > .9 && (this.rad = 5), this.speed = 2, this.opc = Math.random() / 2 + .3, this.isEnd = !1, "function" !== typeof this.nextFps && (e.prototype.nextFps = function() {
                    this.speed += 2, this.rad += .3, this.point.x = (Math.abs(this.point.x) + this.speed * (Math.abs(this.point.x) / (Math.abs(this.point.x) + Math.abs(this.point.y)))) * (this.point.x / Math.abs(this.point.x)), this.point.y = (Math.abs(this.point.y) + this.speed * (Math.abs(this.point.y) / (Math.abs(this.point.x) + Math.abs(this.point.y)))) * (this.point.y / Math.abs(this.point.y)), (this.point.x < h * -.5 || this.point.x > .5 * h || this.point.y < r * -.5 || this.point.y > .5 * r) && (this.isEnd = !0);
                }), "function" !== typeof this.draw && (e.prototype.draw = function(t) {
                    t.save(), t.globalAlpha = this.opc, t.translate(h / 2, r / 2), t.fillStyle = "#fff", t.beginPath(), t.arc(this.point.x, this.point.y, this.rad, 0, 2 * Math.PI), t.fill(), t.restore();
                });
            }
            function n() {
                this.step = .05, this.isEnd = !1, this.opc = 1, "function" !== typeof this.nextFps && (n.prototype.nextFps = function() {
                    this.step < .5 ? this.step += .01 : this.step < 1 && (this.step += .006, this.opc > .01 && (this.opc -= .01)), this.step >= 1 && (this.isEnd = !0);
                }), "function" !== typeof this.draw && (n.prototype.draw = function(t) {
                    t.save();
                    var i = t.createRadialGradient(h / 2, r / 2, 0, h / 2, r / 2, Math.max(h, r));
                    i.addColorStop(0, "rgba(11,45,106,0)"), i.addColorStop(this.step, "rgba(11,45,106,.4)"), i.addColorStop(1, "rgba(11,45,106,.1)"), t.globalAlpha = this.opc, t.fillStyle = i, t.rect(0, 0, h, r), t.fill(), t.restore();
                });
            }
            window.requestAnimFrame = function() {
                return function(t) {
                    window.setTimeout(t, 40);
                };
            }();
            var a = function() {
                try {
                    return document.createElement("canvas").getContext("2d"), !0;
                } catch (t) {
                    return!1;
                }
            }();
            if (a) {
                var s = document.getElementById("canvas"), o = s.getContext("2d"), h = document.body.offsetWidth, r = document.body.offsetHeight;
                s.setAttribute("width", h), s.setAttribute("height", r);
                var c = document.createElement("canvas"), p = c.getContext("2d");
                c.width = h, c.height = r;
                var d, f = {createSign: function() {
                        return Math.random() > .5 ? -1 : 1;
                    }, createPoints: function(t, i, e, n, a) {
                        for (var s = [], o = 0; o < a; o++)
                            s.push({x: t + Math.random() * (e - t), y: i + Math.random() * (n - i)});
                        return s;
                    }}, l = [], u = {avgMax: 2, iSpeed: 1.5, dSpeed: .8, newPoint: function(t, i) {
                        var e = t.x * t.x + t.y * t.y + i * i + 2 * i * Math.sqrt(t.x * t.x + t.y * t.y);
                        return{x: Math.sqrt(e / (1 + t.y * t.y / (t.x * t.x))) * (t.x / Math.abs(t.x)), y: Math.sqrt(e / (1 + t.x * t.x / (t.y * t.y))) * (t.y / Math.abs(t.y))};
                    }, createRay: function(i) {
                        for (var e, n = {x: 0, y: 0}, a = 15, s = 0; s < i; s++)
                            n.x = Math.random() * a * f.createSign(), n.y = Math.sqrt(a * a - n.x * n.x) * f.createSign(), e = Math.ceil(3 * Math.random()), l.push(new t(n, e));
                    }}, x = [], y = [], w = {way: 0, createParticle: function() {
                        for (var t = [], e = 0; e < 10; e++)
                            for (var n = 0; n < 5; n++) {
                                t = f.createPoints(-.5 * h + .1 * e * h, -.5 * r + .2 * n * r, -.5 * h + .1 * (e + 1) * h, -.5 * r + .2 * (n + 1) * r, 6);
                                for (var a = 0; a < t.length; a++)
                                    0 !== t[a].x && 0 !== t[a].y && x.push(new i(t[a]));
                            }
                    }, createParticle2: function() {
                        for (var t = [], i = 0; i < 5; i++)
                            for (var n = 0; n < 2; n++) {
                                t = f.createPoints(-.5 * h + .2 * i * h, -.5 * r + .5 * n * r, -.5 * h + .2 * (i + 1) * h, -.5 * r + .5 * (n + 1) * r, 1);
                                for (var a = 0; a < t.length; a++)
                                    0 !== t[a].x && 0 !== t[a].y && y.push(new e(t[a]));
                            }
                    }}, v = new n, g = {drawParticle: function() {
                        for (var t = 0; t < x.length; t++)
                            x[t].nextFps(), x[t].draw(p);
                    }, drawParticle2: function() {
                        for (var t = 0; t < y.length; t++)
                            y[t].nextFps(), y[t].isEnd ? (y.splice(t, 1), t--) : y[t].draw(p);
                    }, drawRay: function() {
                        for (var t = 0; t < l.length; t++)
                            l[t].nextFps(), l[t].isEnd ? (l.splice(t, 1), t--) : l[t].draw(p);
                    }}, m = function() {
                    switch (p.clearRect(0, 0, h, r), d) {
                        case 1:
                            g.drawParticle();
                            break;
                        case 2:
                            g.drawParticle(), g.drawParticle2(), u.createRay(Math.ceil(Math.random() * u.avgMax)), g.drawRay(), v.nextFps(), v.isEnd || v.draw(p);
                            break;
                        case 3:
                            g.drawParticle(), g.drawRay();
                            break;
                        case 4:
                            g.drawParticle();
                    }
                    o.clearRect(0, 0, h, r), o.drawImage(c, 0, 0), requestAnimFrame(m);
                };
                window.onresize = function() {
                    h = document.body.offsetWidth, r = document.body.offsetHeight, s.setAttribute("width", h), s.setAttribute("height", r), c.width = h, c.height = r, x = [], w.createParticle();
                }, window.cvsEffect = function(t, i) {
                }, cvsEffect.start = function() {
                    requestAnimFrame(m);
                }, cvsEffect.one = function() {
                    d = 1, w.way = 0, x = [], w.createParticle();
                }, cvsEffect.two = function() {
                    d = 2, w.way = 1, x = [], w.createParticle(), y = [], w.createParticle2(), v.isEnd = !0, setTimeout(function() {
                        v.step = .05, v.opc = 1, v.isEnd = !1;
                    }, 50);
                }, cvsEffect.three = function() {
                    d = 3, 0 === x.length && w.createParticle(), w.way = 1;
                }, cvsEffect.four = function() {
                    0 === x.length && w.createParticle(), w.way = 1, d = 4;
                };
            }
        }();
    }]);