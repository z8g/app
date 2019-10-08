function sortTable(table, idx) {
    var otable = document.getElementById(table),
            otody = otable.tBodies[0],
            otr = otody.rows,
            tarr = [];
    for (var i = 1; i < otr.length; i++) {
        tarr[i - 1] = otr[i];
    }
    ;

    if (otody.sortCol === idx) {
        tarr.reverse();
    } else {
        tarr.sort(function(tr1, tr2) {
            var value1 = tr1.cells[idx].innerHTML;
            var value2 = tr2.cells[idx].innerHTML;
            if (!isNaN(value1) && !isNaN(value2)) {
                return value1 - value2;
            } else {
                return value1.localeCompare(value2);
            }
        });
    }
    var fragment = document.createDocumentFragment();
    for (var i = 0; i < tarr.length; i++) {
        fragment.appendChild(tarr[i]);
    }
    otody.appendChild(fragment);
    otody.sortCol = idx;
}