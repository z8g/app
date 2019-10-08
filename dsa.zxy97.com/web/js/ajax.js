function AesSetKey(isEncode) {
    var checkDiv = document.getElementById("AesSetKeyResponse");
    var AesKey = document.getElementById("AesKey").value;
    var url = "AesSetKeyServlet?AesKey=" + AesKey +"&isEncode="+isEncode;

    if (AesKey !== "") {
        var xmlHttp;
        if (window.XMLHttpRequest) {
            xmlHttp = new XMLHttpRequest();
        } else {
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlHttp.open("POST", url, true);
        xmlHttp.onreadystatechange = function() {
            if (xmlHttp.readyState === 4) {
                checkDiv.innerHTML = xmlHttp.responseText;
            } else {
                checkDiv.innerHTML = "...";
            }
        };
        xmlHttp.send();
    }else{
        checkDiv.innerHTML = "";   
    }
}

function regCheck() {
    var checkDiv = document.getElementById("checkDiv");
    var reg_name = document.form_reg.reg_name.value;
    var url = "../CaesarServlet?reg_name=" + reg_name;
//var xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");

    if (reg_name !== "") {
        var xmlHttp;
        if (window.XMLHttpRequest) {
            xmlHttp = new XMLHttpRequest();
        } else {
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlHttp.open("POST", url, true);
        xmlHttp.onreadystatechange = function() {
            if (xmlHttp.readyState === 4) {
                checkDiv.innerHTML = xmlHttp.responseText;
            } else {
                checkDiv.innerHTML = "...";
            }
        };
        xmlHttp.send();
    }else{
        checkDiv.innerHTML = "";   
    }

}