document.addEventListener('DOMContentLoaded', () => {
    //getAllStudents('${isStudent}'==='true');


});

function getAllStudents(isStudent, imie, nazwisko) {
    fetch("http://localhost:8082/students")
            .then((response) => {
                if (response.status !== 200) {
                    return Promise.reject('Coś poszło nie tak!');
                }
                return response.json();
            })
            .then((data) => {
                pokazTabele(data, isStudent, imie, nazwisko);
            })
            .catch((error) => {
                console.log(error);
            });
}

function pokazTabele(response, isStudent, imie, nazwisko) {
    var tabela = document.getElementById('tabelaStudentow');
    var content = "<center><table > <thead> <tr> <th> Imię</th>" +
            "<th>Nazwisko</th><th>Oceny</th>";


    if (!isStudent) {
        content += "<th>Działania</th>";
    }
    content += "</tr></thead><tbody>";
    for (var st in response) {

        var id = response[st].id;
        var name = response[st].name;
        var surname = response[st].surname;
        var oceny = response[st].grades;
        console.log(name);
        console.log(imie);
        console.log(surname);
        console.log(nazwisko);
        if ((isStudent && imie === name && nazwisko === surname) || !isStudent) {

            if (isStudent)
                content += "<tr><td>" + name + "</td><td>" + surname +
                        "</td><td>" + oceny.toString() + '</td>';




            if (!isStudent) {
                content += "<tr><td>" + '<input type="text" id="name' + id + '" value="' + name + '" placeholder="Imię"/>' + "</td><td>" + '<input type="text" id="surname' + id + '" value="' + surname + '" placeholder="Nazwisko"/>' +
                        "</td><td>" + '<input type="text" id="grades' + id + '" value="' + oceny + '" placeholder="Oceny"/>' + '</td>';
                content += '</td><td><button class="btn" onclick="usun(' + id + ')">Usuń</button> <a class="btn" href="/student-page/' + id + '">Przejdz do profilu</a>';
                content += ' <button class="btn" onclick="zapisz(' + id + ')">Zapisz</button></td>';
            }
        }


    }
    content += "</tr></tbody></table></center>";
    tabela.innerHTML = content;
}

function zapisz(studentId) {
    var n = document.getElementById('name' + studentId + '').value;
    var s = document.getElementById('surname' + studentId + '').value;
    var o = document.getElementById('grades' + studentId + '').value;
    let student = {};
    student.id = studentId;
    student.name = n;
    student.surname = s;
    student.grades = o.split(',').map(x => Number.parseFloat(x));
    console.log(student.oceny);
    fetch("http://localhost:8082/student/" + studentId, {method: "put", body: JSON.stringify(student), headers: {"Content-type": "application/json; charset=UTF-8"}})
            .then(res => {
                getAllStudents(false, ' ', ' ');
            });
}

function usun(id) {
    fetch("http://localhost:8082/student" + "/" + id, {method: "delete", body: "", headers: {"Content-type": "application/json; charset=UTF-8"}})
            .then(res => {
                getAllStudents(false, ' ', ' ');
            });
}

function getKomentarze(studentId, isStudent,show) {
if(show)
    {
    fetch("http://localhost:8082/comments/" + studentId)
            .then((response) => {
                if (response.status !== 200) {
                    return Promise.reject('Coś poszło nie tak!');
                }
                return response.json();
            })
            .then((data) => {
                pokazKomentarze(data, isStudent, studentId);
            })
            .catch((error) => {
                console.log(error);
            });

}}
function pokazKomentarze(response, isStudent, studentId) {
    let komentarze = document.getElementById('komentarzeStudentow');
    let content = "<br><center><table > <thead> <tr> <th> Komentarz</th>" +
            "<th>Działania</th>";
    content += "</tr></thead><tbody>";
   

    if (!isStudent) {
        
        content += "<tr><td>" + "<input type='text' value='aaaa' id='commentAdd' placeholder='addComment'/> "
                + "</td><td>" + '<button class="btn" onclick="addKomentarz(' + studentId + ')">Dodaj</button><br>' + "</td></tr>"
                ;
  
    }

    for (var st in response) {
           console.log('current response');
    console.log( response[st].content);
        content += "<tr><td>" + '<input type="text"  id="comment' +  response[st].id  +'" value="' + response[st].content + '" placeholder="Komentarz"/>' + "</td>";
                       
       
        if (!isStudent)
            content += '<td><button class="btn" onclick="zapiszKomentarz(' + studentId + ',' + response[st].id + ')">Zapisz</button></td>';
        else
            content+'<td></td>';
       
    }
   content += "</tr></tbody></table></center>";
    komentarze.innerHTML = content;
    console.log('current content');
    console.log(content.typeof);
    console.log(content);
    console.log(komentarze.innerHTML);
}

function zapiszKomentarz(studentId, id) {
    var c = document.getElementById('comment' + id + '').value;

    let student = {};
    student.id = id;
    student.content = c;
    student.studentId = studentId;


    fetch("http://localhost:8082/comment/" + id, {method: "put", body: JSON.stringify(student), headers: {"Content-type": "application/json; charset=UTF-8"}})
            .then(res => {
                getKomentarze(studentId, true, false);
            });
}

function addKomentarz(studentId) {
    var c = document.getElementById('commentAdd').value;
    let comment = {};
    comment.content = c;
    comment.studentId = studentId;
    fetch("http://localhost:8082/comment/add/" + studentId, {method: "post", body: JSON.stringify(comment), headers: {"Content-type": "application/json; charset=UTF-8"}})
            .then(res => {
                getKomentarze(studentId, true, false);
            });

}