
// 通用初始化函数
$(function () {
    // 处理AJAX的时候防止跨站攻击的问题，需要在HTML的head里面增加两个meta
    // 分别是_csrf和_csrf_header
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    if (token && header) {
        var csrfToken = {};
        csrfToken[header] = token;

        $.ajaxSetup({
            beforeSend: function (xhr, settings) {
                xhr.setRequestHeader(header, token);
            }
        });
    }
});

var fk = {};
fk.alert = function (msg, callback) {
    if ($("#_fk_alert").length === 0) {
        // 插入新的HTML内容作为对话框
        var html = '\
<div class="modal fade" tabindex="-1" role="dialog" id="_fk_alert">\
	<div class="modal-dialog" role="document">\
	    <div class="modal-content">\
	        <div class="modal-header">\
	            <button type="button" class="close" data-dismiss="modal" aria-label="Close">\
	            	<span aria-hidden="true">&times;</span>\
            	</button>\
	            <h4 class="modal-title">提示</h4>\
	        </div>\
	        <div class="modal-body">\
	        </div>\
	        <div class="modal-footer">\
	            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>\
	        </div>\
	    </div>\
	</div>\
</div>';
        $(html).appendTo($("body"));
    }

    $("#_fk_alert .modal-body").html(msg);
    // 绑定点击事件的处理器
    if (callback) {
        $("#_fk_alert .modal-footer button").bind("click", function () {
            // 执行回调函数
            callback();
            // 点击一次以后，取消点击事件绑定
            $("#_fk_alert .modal-footer button").unbind("click");
        });
    }
    // 显示对话框
    $("#_fk_alert").modal();
};


fk.confirm = function (msg, yesCallback, noCallback) {
    if ($("#_fk_confirm").length === 0) {
        // 插入新的HTML内容作为对话框
        var html = '\
<div class="modal fade" tabindex="-1" role="dialog" id="_fk_confirm">\
	<div class="modal-dialog" role="document">\
	    <div class="modal-content">\
	        <div class="modal-header">\
	            <button type="button" class="close" data-dismiss="modal" aria-label="Close">\
	            	<span aria-hidden="true">&times;</span>\
            	</button>\
	            <h4 class="modal-title">确认</h4>\
	        </div>\
	        <div class="modal-body">\
	        </div>\
	        <div class="modal-footer">\
	            <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>\
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>\
	        </div>\
	    </div>\
	</div>\
</div>';
        $(html).appendTo($("body"));
    }

    $("#_fk_confirm .modal-body").html(msg);
    // 绑定点击事件的处理器
    if (yesCallback) {
        $("#_fk_confirm .modal-footer button.btn-primary").bind("click", function () {
            //console.log("确定按钮");
            // 执行回调函数
            yesCallback();
            // 点击一次以后，取消点击事件绑定
            $("#_fk_confirm .modal-footer button.btn-primary").unbind("click");
        });
    }
    if (noCallback) {
        $("#_fk_confirm .modal-footer button.btn-default").bind("click", function () {
            //console.log("取消按钮");
            // 执行回调函数
            noCallback();
            // 点击一次以后，取消点击事件绑定
            $("#_fk_confirm .modal-footer button.btn-default").unbind("click");
        });
    }
    // 显示对话框
    $("#_fk_confirm").modal();
};

