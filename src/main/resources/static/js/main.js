document.addEventListener("DOMContentLoaded", function() {
    pageLoad.init();
    listMake.init();
    cardMake.init();
});

class Sort {
    constructor(arr) {
        this.arr = arr;
    }

    sorting() {
        this.arraySort(this.arr.lists);
        for(var i = 0; i < this.arr.lists.length; i++) {
            this.arraySort(this.arr.lists[i].cards);
        }
    }

    arraySort(arr) {
        arr.sort(function(a, b) {
            if(a.position*1 < b.position*1) {
                return -1;
            }
            if(a.position*1 > b.position*1) {
                return 1;
            }
            return 0;
        });
    }
}

var pageLoad = {

    pageRender : function(res) {
        if(res.target.status === 200) {
            var resObj = JSON.parse(res.target.responseText);

            /* */
            var boardTitle = document.querySelector(".board-title");
            boardTitle.innerHTML = boardTitle.innerHTML.replace("{{board.title}}", resObj.title);

            var objSort = new Sort(resObj);
            objSort.sorting();


            for(var i = 0; i < resObj.lists.length; i++) {
                var temp = document.createElement("div");
                temp.classList.add("list");

                var template = document.querySelector("#listTemplate");
                var templateText = template.innerHTML;
                var wrapper = document.querySelector(".list-wrapper");

                templateText = templateText.replace("{{title}}", resObj.lists[i].title);
                temp.innerHTML = templateText;

                wrapper.insertBefore(temp, wrapper.lastElementChild);


                var cards = temp.querySelector(".cards");
                for(var j = 0; j < resObj.lists[i].cards.length; j++) {
                    var node = document.createElement("div");
                    node.classList.add("card");

                    node.innerHTML = "<div class='card-title'>" + resObj.lists[i].cards[j].title + "</div>";

                    cards.insertBefore(node, cards.lastElementChild);
                }
            }
        }
    },

    init : function() {
        var ajax = new XMLHttpRequest();
        ajax.addEventListener("load", this.pageRender);
        ajax.open("POST", location.href + "/con");
        ajax.send();
    }
};

var listMake = {

    listAdd : function(evt) {
        evt.preventDefault();

        var title = evt.target.firstElementChild.value;
        var board = document.querySelector(".board-title").innerText;
        var number = document.querySelector(".list-wrapper").childElementCount;

        // ajax call
        var ajax = new XMLHttpRequest();
        ajax.addEventListener("load", function(res) {
            if(res.target.status === 200) {

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
        ajax.send("title="+title+"&position="+number+"&boards="+board);
    },

    init : function() {
        var saveButton = document.querySelector("#save");
        saveButton.addEventListener("submit", this.listAdd);
    }
};

var cardMake = {

    cardAdd : function(evt) {
        if(!(evt.target.className === "add-card")) return;

        var title = evt.target.parentNode.previousElementSibling;
        var board = document.querySelector(".board-title").innerText;
        var list = evt.target.parentNode.parentNode.parentNode.previousElementSibling.firstElementChild.innerText;
        var count = evt.target.parentNode.parentNode.parentNode.childElementCount;


        var ajax = new XMLHttpRequest();
        ajax.addEventListener("load", function(res) {


            var cards = evt.target.parentNode.parentNode.parentNode;

            var node = document.createElement("div");
            node.classList.add("card");

            node.innerHTML = "<div class='card-title'>" + title.value + "</div>";

            cards.insertBefore(node, cards.lastElementChild);

        });
        ajax.open("POST", "http://localhost:8080/board/list/card");
        ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        ajax.send("title="+title.value+"&boards="+board+"&list="+list+"&position="+count);
    },

    init : function() {
        var listWrapper = document.querySelector(".list-wrapper");
        listWrapper.addEventListener("click", this.cardAdd);
    }
};



/*class Ajax {
    constructor() {
        this.ajax = new XMLHttpRequest();
    }

    event(func) {
        this.ajax.addEventListener("load", func);
    }

    open(url) {
        this.ajax.open("POST", url);
    }
}*/
