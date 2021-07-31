const box = document.getElementById('box');

function reqList() {
    const input = document.getElementById('input').value;
    $.ajax({
        url:'http://localhost:8080'
        , type : 'POST'
        , data: 'originuri=' + input
        , dataType : 'json'
        , success : function(data) {render(data)}
    })
}

function render(data) {
    if(data.hasOwnProperty('err')){
        let resp = data.err + "는 " + data.message;
        box.innerHTML += "<p>"+ resp +"</p>";
    } else {
        console.log(data);
        let resp ="http://localhost:8080/sa/" + data.data.changedUri + "변환되었습니다.";
        box.innerHTML += "<p>"+ resp +"</p>";
    }
}