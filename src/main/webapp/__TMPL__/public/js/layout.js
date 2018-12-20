var insertTable = function (btn, opt) {
    if (!btn) {
        return;
    }
    this.btn = btn;
    opt = opt || {};
    this.box = null;//弹框
    this.inBox = null;
    this.pickUnLight = null;
    this.pickLight = null;
    this.status = null;
    this.minSize = opt.min || [5, 5];//最小列数和行数
    this.maxSize = opt.max || [15, 15];//最大列数和行数
    this.insert = opt.insert;//回调
    this.nowSize = [];//当前选择尺寸
    this.isInit = {create: false, bind: false};
    this.bind();
}
insertTable.prototype = {
    init: function () {
        if (this.isInit.create) {
            return;
        }
        this.isInit.create = true;
        var id = 'in_tab_box_' + String(Math.ceil(Math.random() * 100000) + String(new Date().getTime())),
            html = '<div class="in_tab_box" id="' + id + '">';
        html += '<div class="itb_con">';
        html += '<div class="itb_picker_unlight"></div>';
        html += '<div class="itb_picker_light"></div>';
        html += '</div>';
        html += '<div class="itb_picker_status"></div>';
        html += '</div>';
        $("body").append(html);
        this.box = $("#" + id);
        this.inBox = this.box.find(".itb_con");
        this.pickAll = this.box.find(".itb_picker_all");
        this.pickUnLight = this.box.find(".itb_picker_unlight");
        this.pickLight = this.box.find(".itb_picker_light");
        this.status = this.box.find(".itb_picker_status");
        this.setBg(this.minSize[0], 0);
        this.setBg(this.minSize[1], 1);
        this.status.text(0 + '列 x ' + 0 + '行');
    },
    bind: function () {
        var T = this,
            pos,//弹框显示位置
            m,
            bPos,//弹框可选区域位置
            mPos;//鼠标位置
        this.btn.click(function () {
            if (!T.isInit.create) {
                T.init();
            }//初始化弹框
            if (!T.isInit.bind) {
                B();
            }//初始化事件
            m = $(this);
            if (T.box.is(":hidden")) {
                pos = {
                    top: m.offset().top,
                    left: m.offset().left + m.outerWidth() + 2
                }
                T.box.css({
                    "top": pos.top,
                    "left": pos.left
                }).fadeIn(100);
                bPos = {
                    top: T.inBox.offset().top,
                    left: T.inBox.offset().left
                }
                $(document).bind("click", function () {
                    T.hide();
                });
            } else {
                T.hide();
            }
            return false;
        })
        function B() {
            T.isInit.bind = true;
            T.inBox.mousemove(function (e) {
                mPos = {
                    x: e.clientX,
                    y: e.clientY
                }
                if (mPos.x < bPos.left || mPos.y < bPos.top) {
                    return;
                }
                T.nowSize[0] = Math.ceil((mPos.x - bPos.left) / 18);//列数
                T.nowSize[1] = Math.ceil((mPos.y - bPos.top) / 18);//行数
                if (T.nowSize[0] >= T.minSize[0] && T.nowSize[0] < T.maxSize[0]) {
                    T.setBg(T.nowSize[0] + 1, 0);
                } else if (T.nowSize[0] < T.minSize[0]) {
                    T.setBg(T.minSize[0], 0);
                } else {
                    T.nowSize[0] = T.maxSize[0];
                }
                if (T.nowSize[1] >= T.minSize[1] && T.nowSize[1] < T.maxSize[1]) {
                    T.setBg(T.nowSize[1] + 1, 1);
                } else if (T.nowSize[1] < T.minSize[1]) {
                    T.setBg(T.minSize[1], 1);
                } else {
                    T.nowSize[1] = T.maxSize[1];
                }
                T.pickLight.css({
                    "width": T.nowSize[0] + 'em',
                    "height": T.nowSize[1] + 'em'
                })
                T.status.text(T.nowSize[0] + '列 x ' + T.nowSize[1] + '行');
            })
//单击确认插入表格
            T.box.click(function () {
                if (T.nowSize[0] > 0 && T.nowSize[0] <= T.maxSize[0] && T.nowSize[1] > 0 && T.nowSize[1] <= T.maxSize[1]) {
                    var rows = T.nowSize[1],
                        cols = T.nowSize[0];
                    try {
                        T.insert(rows, cols);
                    } catch (e) {
                    }
                }
            })
        }
    },
//调整背景区域
    setBg: function (size, t) {
        if (t == 0) {
            this.inBox.width(size + 'em');
            this.pickUnLight.width(size + 'em');
        } else {
            this.inBox.height(size + 'em');
            this.pickUnLight.height(size + 'em');
        }
    },
//隐藏弹框
    hide: function () {
        var T = this;
        this.box.fadeOut(100, function () {
//重置
            T.setBg(T.minSize[0], 0);
            T.setBg(T.minSize[1], 1);
            T.pickLight.css({
                "width": '0',
                "height": '0'
            })
        });
    }
}