document.addEventListener("DOMContentLoaded", function() {
    var first = new XMLHttpRequest();
    first.addEventListener("load", function(res) {
        if(res.target.status === 200) {
            console.log(res.target.responseText);
            var resObj = JSON.parse(res.target.responseText);
            console.log(resObj);
            var boardTitle = document.querySelector(".board-title");

            boardTitle.innerHTML = boardTitle.innerHTML.replace("{{board.title}}", resObj[0].title);


            for(var i = 0; i < resObj[0].lists.length; i++) {
                var temp = document.createElement("div");
                temp.classList.add("list");
                var template = document.querySelector("#listTemplate");
                var templateText = template.innerHTML;
                var wrapper = document.querySelector(".list-wrapper");

                templateText = templateText.replace("{{title}}", resObj[0].lists[i].title);
                temp.innerHTML = templateText;

                console.log(temp);

                wrapper.insertBefore(temp, wrapper.lastElementChild);
                resObj[0].lists[i].cards.sort(function(a, b) {
                    if(a.position*1 < b.position*1) {
                        return -1;
                    }
                    if(a.position*1 > b.position*1) {
                        return 1;
                    }
                    return 0;
                });
                var cards = temp.querySelector(".cards");
                for(var j = 0; j < resObj[0].lists[i].cards.length; j++) {
                    var node = document.createElement("div");
                    node.classList.add("card");

                    node.innerHTML = "<div class='card-title'>" + resObj[0].lists[i].cards[j].title + "</div>";

                    cards.insertBefore(node, cards.lastElementChild);
                }
            }


        }

    });
    first.open("POST", location.href + "/con");
    first.send();


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




    var card = document.querySelector(".list-wrapper");
    card.addEventListener("click", function(evt) {
        if(!(evt.target.className === "add-card")) return;

        var title = evt.target.parentNode.previousElementSibling;

        var ajax = new XMLHttpRequest();
        ajax.addEventListener("load", function(res) {

            console.log(title);
            //var title = document.querySelector(".add-title");
            var cards = evt.target.parentNode.parentNode.parentNode;

            var node = document.createElement("div");
            node.classList.add("card");

            node.innerHTML = "<div class='card-title'>" + title.value + "</div>";

            cards.insertBefore(node, cards.lastElementChild);

        });
        ajax.open("POST", "http://localhost:8080/board/list/card");
        ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        ajax.send("title="+title.value+"&number=1");

    });
});


