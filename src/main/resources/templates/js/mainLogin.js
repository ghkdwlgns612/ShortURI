const origin = document.getElementById('origin');
const encoding = document.getElementById('encoding');

function reqListLogin() {
    const input = document.getElementById('input').value;
    const name = document.getElementById('named').value;
    $.ajax({
        url:'http://localhost:8080/login'
        , type : 'POST'
        , data: {originUrl:input, name:name}
        , dataType : 'json'
        , success : function(data) {render(data)}
    })
}

function render(data) {
    if(data.hasOwnProperty('err')){
        if (data.statusCode == 404) {
            alert("유효하지 않은 URL입니다.");
        } else {
            alert("이미 변환된 URL입니다.");
        }
    } else {
        let resp ="http://localhost:8080/sa/" + data.data.encodedValue;
        let resp_origin = data.data.originUrl;
        origin.innerHTML += "<p>"+ resp_origin +"</p>";
        encoding.innerHTML += "<p>"+ resp +"</p>";
    }
}