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
            var boardTitle = document.querySelector(".board-header-btn-text");
            boardTitle.innerHTML = boardTitle.innerHTML.replace("{{board.title}}", resObj.title);

            var objSort = new Sort(resObj);
            objSort.sorting();


            for(var i = 0; i < resObj.lists.length; i++) {

                var listWrapper = document.createElement("div");
                listWrapper.classList.add("list-wrapper");

                var list = document.querySelector("#listTemplate").innerHTML;
                list = list.replace("{{title}}", resObj.lists[i].title);
                listWrapper.innerHTML = list;

                var board = document.querySelector("#board");
                board.insertBefore(listWrapper, board.lastElementChild);

                var cards = listWrapper.querySelector(".cards");
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
        var board = document.querySelector(".board-header-btn-text").innerText;
        var number = document.querySelector("#board").childElementCount;

        // ajax call
        var ajax = new XMLHttpRequest();
        ajax.addEventListener("load", function(res) {
            if(res.target.status === 200) {

                var listWrapper = document.createElement("div");
                listWrapper.classList.add("list-wrapper");

                var list = document.querySelector("#listTemplate").innerHTML;
                list = list.replace("{{title}}", title);

                listWrapper.innerHTML = list;

                var board= document.querySelector("#board");
                board.insertBefore(listWrapper, board.lastElementChild);
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

        var addCard = evt.target;
        var list = addCard.closest(".list");

        var addtitle = list.querySelector(".add-title").value;
        var listTitle = list.querySelector(".list-title").innerText;
        var cards = list.querySelector(".cards");
        var count = cards.childElementCount;

        var board = document.querySelector(".board-header-btn-text").innerText;

        var ajax = new XMLHttpRequest();
        ajax.addEventListener("load", function(res) {
            if(res.target.status === 200) {
                var card = document.createElement("div");
                card.classList.add("card");

                card.innerHTML = "<div class='card-title'>" + addtitle + "</div>";

                cards.insertBefore(card, cards.lastElementChild);
            }
        });
        ajax.open("POST", "http://localhost:8080/board/list/card");
        ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        ajax.send("title="+addtitle+"&boards="+board+"&list="+listTitle+"&position="+count);
    },

    init : function() {
        var board = document.querySelector("#board");
        board.addEventListener("click", this.cardAdd);
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
