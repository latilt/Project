document.addEventListener("DOMContentLoaded", function() {
    var saveForm = document.querySelector("#save");

    saveForm.addEventListener("submit", function(evt) {
        evt.preventDefault();

        var title = evt.target.firstElementChild.value;

        // ajax call
        var ajax = new XMLHttpRequest();
        ajax.addEventListener("load", function(res) {
            console.log(res.target.status);
            if(res.target.status === 200) {
                console.log("ajax ok");

                var temp = document.createElement("div");
                temp.classList.add("list");
                var templete = document.querySelector("#listTemplate");
                var templeteText = templete.innerHTML;
                var wrapper = document.querySelector(".list-wrapper");

                templeteText = templeteText.replace("{{title}}", title);
                temp.innerHTML = templeteText;

                console.log(temp);

                wrapper.insertBefore(temp, wrapper.lastElementChild);
            }

        });
        ajax.open("POST", "http://localhost:8080/board/list/save");
        ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        ajax.send("title="+title);

    });
});


