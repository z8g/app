function regCheck() {
    var checkDiv = document.getElementById("checkDiv");
    var reg_name = document.getElementById('reg_name').value;
    var url = "regCheck?reg_name=" + reg_name;
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