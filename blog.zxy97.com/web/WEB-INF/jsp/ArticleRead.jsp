<%-- 
    Document   : read
    Created on : 2018-11-13, 19:28:32
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>${article.title}</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/editormd.css" />
        <link rel="shortcut icon" href="http://zxy97.com/favicon.ico" type="image/x-icon" />
    </head>
    <body>
        <div id="layout">
            <header>
                <h1>${article.title}</h1>
                <h4>预览窗口主题：<select id="preview-area-theme-select"></select></h4>
            </header>
            <div class="btns">
                <button id="return-btn" type="button">返回</button>
            </div>
            <div id="test-editormd">
                <textarea style="display:none;" id="textarea1">${article.markdown}</textarea>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/editormd.js"></script>
        <script>
 var testEditor;

function themeSelect(id, themes, lsKey, callback)
{
    var select = $("#" + id);

    for (var i = 0, len = themes.length; i < len; i++)
    {
        var theme = themes[i];
        var selected = (localStorage[lsKey] === theme) ? " selected=\"selected\"" : "";

        select.append("<option value=\"" + theme + "\"" + selected + ">" + theme + "</option>");
    }

    select.bind("change", function() {
        var theme = $(this).val();

        if (theme === "")
        {
            alert("theme == \"\"");
            return false;
        }

        console.log("lsKey =>", lsKey, theme);

        localStorage[lsKey] = theme;
        callback(select, theme);
    });

    return select;
}

$(function() {
    testEditor = editormd("test-editormd", {
        width: "90%",
        height: 740,
        //autoHeight      : true,

        path: '${pageContext.request.contextPath}/lib/',
        codeFold: true,
        //syncScrolling : false,
        saveHTMLToTextarea: true, // 保存 HTML 到 Textarea
        searchReplace: true,
        //watch : false,                // 关闭实时预览
        htmlDecode: "style,script,iframe|on*", // 开启 HTML 标签解析，为了安全性，默认不开启    
        //toolbar  : false,             //关闭工具栏
        emoji: true,
        taskList: true,
        tocm: true, // Using [TOCM]
        tex: true, // 开启科学公式TeX语言支持，默认关闭
        flowChart: true, // 开启流程图支持，默认关闭
        sequenceDiagram: true, // 开启时序/序列图支持，默认关闭,
        //dialogLockScreen : false,   // 设置弹出层对话框不锁屏，全局通用，默认为true
        //dialogShowMask : false,     // 设置弹出层对话框显示透明遮罩层，全局通用，默认为true
        //dialogDraggable : false,    // 设置弹出层对话框不可拖动，全局通用，默认为true
        //dialogMaskOpacity : 0.4,    // 设置透明遮罩层的透明度，全局通用，默认值为0.1
        //dialogMaskBgColor : "#000", // 设置透明遮罩层的背景颜色，全局通用，默认为#fff
        placeholder: "",
        theme: (localStorage.theme) ? localStorage.theme : "dark",
        previewTheme: (localStorage.previewTheme) ? localStorage.previewTheme : "dark",
        editorTheme: (localStorage.editorTheme) ? localStorage.editorTheme : "pastel-on-dark",
        readOnly: true,
        onload: function() {
            console.log('onload', this);
            testEditor.previewing();
            //this.fullscreen();

            //自定义快捷键
            var keyMap = {
                "Ctrl-S": function(cm) {

                },
                "Ctrl-A": function(cm) { // default Ctrl-A selectAll
                    cm.execCommand("selectAll");
                }
            };
            var keyMap2 = {
                "Ctrl-T": function(cm) {
                    alert("Ctrl+T");
                }
            };

            this.addKeyMap(keyMap);
            this.addKeyMap(keyMap2);
            this.removeKeyMap(keyMap2);  // remove signle key

        }
    });

    themeSelect("editormd-theme-select", editormd.themes, "theme", function($this, theme) {
        testEditor.setTheme(theme);
    });

    themeSelect("editor-area-theme-select", editormd.editorThemes, "editorTheme", function($this, theme) {
        testEditor.setCodeMirrorTheme(theme);
    });

    themeSelect("preview-area-theme-select", editormd.previewThemes, "previewTheme", function($this, theme) {
        testEditor.setPreviewTheme(theme);
    });

    $("#return-btn").bind("click", function() {
        window.location.href="${pageContext.request.contextPath}/index";
    });
});           
        </script>
    </body>
</html>