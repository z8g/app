<%-- 
    Document   : edit
    Created on : 2018-11-10, 16:20:14
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:choose>
    <c:when test="${user!=null}">
    </c:when>
    <c:otherwise>
        <c:redirect url="login"></c:redirect>
    </c:otherwise>
</c:choose>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>zxy97博客 - 写文章</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/editormd.css" />   
        <link rel="shortcut icon" href="https://zxy97.com/favicon.ico" type="image/x-icon" />
    </head>
    <body>
        <div id="layout">
            <header>
                <h1>zxy97博客 | 写文章</h1>
            </header>
                     <c:choose>
                        <c:when test="${article.id==null}">
                        </c:when>
                        <c:otherwise>
                            <div class="btns">
                            <form action="${pageContext.request.contextPath}/${author.username}/article/remover/${article.id}" method="post">
                                <button id="delete-btn" type="submit">删除文章</button>
                            </form>
                            </div>
                        </c:otherwise>
                    </c:choose>  
                <div class="btns">
                    工具栏主题：<select id="editormd-theme-select"></select>
                    编辑器主题：<select id="editor-area-theme-select"></select>
                    预览区主题：<select id="preview-area-theme-select"></select>
                </div>
            <form action="${pageContext.request.contextPath}/${author.username}/article/publisher" method="post">
                
                <div class="btns">
                    <strong>文章分类：</strong>
                    <select name="article_type" id="article_type">
                        <c:forEach var="articlePackageVO" items="${articlePackageVOList}">
                            <option value="${articlePackageVO.type}" ${article.type == articlePackageVO.type ? "selected='selected'" : ""}>${articlePackageVO.type}</option>
                        </c:forEach>
                    </select>
                    <input id="article_new_type" name="article_new_type" type="text" placeholder="填写新分类" maxlength="20" />
                    
                    <strong>是否公开：</strong>
                    <select name="article_auth">
                        <option value="0" ${article.auth==0 ? "selected='selected'" : ""}>公开</option>
                        <option value="1" ${article.auth==1 ? "selected='selected'" : ""}>登录可见</option>
                        <option value="2" ${article.auth==2 ? "selected='selected'" : ""}>仅自己可见</option>
                    </select>
                </div>
            
                <div class="btns">
                    <strong>文章标题：</strong><input value="${article.title}" id="article_title" name="article_title" type="text" placeholder="请输入文章标题" required="required"/>
                    <button id="publish-btn" type="submit">发表文章</button>
                    <button id="return-btn" type="button" onclick="if(confirm('取消发表？'))window.location.href='${pageContext.request.contextPath}/index'">退出编辑</button>
                    
                </div>
                  
                <div id="test-editormd">
                    <textarea style="display:none;" name="article_markdown" required="required">${article.markdown}</textarea>
                </div>
                <input type="hidden" name="article_html" id="article_html"/>
                <input type="hidden" name="article_id" id="article_id" value="${article.id}"/>
                <input type="hidden" name="author_username" id="author_username" value="${author.username}"/>
                <input type="hidden" name="article_html" id="article_html"/>
                
            </form>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/editormd.js"></script>   
        <script>
var testEditor;

$(function() {

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
            //previewCodeHighlight : false, // 关闭预览 HTML 的代码块高亮，默认开启
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
            placeholder: "请输入文章的内容……",
            theme: (localStorage.theme) ? localStorage.theme : "dark",
            previewTheme: (localStorage.previewTheme) ? localStorage.previewTheme : "dark",
            editorTheme: (localStorage.editorTheme) ? localStorage.editorTheme : "pastel-on-dark",
            imageUpload: true,
            imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
            imageUploadURL: "ImageUpload",
            onload: function() {
                //console.log('onload', this);
                //this.fullscreen();
                //this.unwatch();
                //this.watch().fullscreen();

                //this.setMarkdown("#PHP");
                //this.width("100%");
                //this.height(480);
                //this.resize("100%", 640);


                //自定义快捷键
                var keyMap = {
                    "Ctrl-S": function(cm) {

                    },
                    "Ctrl-A": function(cm) { // default Ctrl-A selectAll
                        cm.execCommand("selectAll");
                    }
                };
                this.addKeyMap(keyMap);
            }

        });
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

    $("#publish-btn").bind('click', function() {
        if ($("#article_title").val() !== "") {
            if (testEditor.getMarkdown() !== "") {
                //alert(testEditor.getHTML());
                window.event.returnValue = confirm("是否发表？");
            } else {
                alert("文章内容不能为空");
            }
        } else {
            alert("文章标题不能为空");
        }
    });
    
    $("#delete-btn").bind('click', function() {
        window.event.returnValue = confirm("确定删除？");
    });
});

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
            return false;
        }
        console.log("lsKey =>", lsKey, theme);
        localStorage[lsKey] = theme;
        callback(select, theme);
    });
    return select;
}
            
        </script>
    </body>
</html>