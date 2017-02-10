document.addEventListener("DOMContentLoaded", function() {
    pageLoad.init();
    list.init();
    card.init();
});

class Sort {
    constructor(arr) {
        this.arr = arr;
    }

    sorting() {
        this.arraySort(this.arr.lists);
        for(let i = 0; i < this.arr.lists.length; i++) {
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

const pageLoad = {

    pageRender: function (res) {
        if (res.target.status === 200) {
            const resObj = JSON.parse(res.target.responseText);

            /* */
            const boardTitle = document.querySelector(".board-header-btn-text");
            boardTitle.innerHTML = boardTitle.innerHTML.replace("{{board.title}}", resObj.title);

            const objSort = new Sort(resObj);
            objSort.sorting();


            for (let i = 0; i < resObj.lists.length; i++) {

                const listWrapper = document.createElement("div");
                listWrapper.classList.add("list-wrapper");

                let list = document.querySelector("#listTemplate").innerHTML;
                list = list.replace("{{title}}", resObj.lists[i].title);
                listWrapper.innerHTML = list;

                const board = document.querySelector("#board");
                board.insertBefore(listWrapper, board.lastElementChild);

                const cards = listWrapper.querySelector(".cards");
                for (let j = 0; j < resObj.lists[i].cards.length; j++) {
                    const node = document.createElement("div");
                    node.classList.add("card");

                    node.innerHTML = "<div class='card-title'>" + resObj.lists[i].cards[j].title + "</div><div class='card-delete'>X</div>";

                    cards.insertBefore(node, cards.lastElementChild);
                }
            }
        }
    },

    init: function () {
        const ajax = new XMLHttpRequest();
        ajax.addEventListener("load", this.pageRender);
        ajax.open("POST", location.href + "/con");
        ajax.send();
    }
};

const list = {

    listAdd: function (evt) {
        evt.preventDefault();

        const title = evt.target.firstElementChild.value;
        if(title.length === 0) return;

        const board = document.querySelector(".board-header-btn-text").innerText;
        const number = document.querySelector("#board").childElementCount;

        // ajax call
        const ajax = new XMLHttpRequest();
        ajax.addEventListener("load", function (res) {
            if (res.target.status === 200) {

                const listWrapper = document.createElement("div");
                listWrapper.classList.add("list-wrapper");

                let list = document.querySelector("#listTemplate").innerHTML;
                list = list.replace("{{title}}", title);

                listWrapper.innerHTML = list;

                const board = document.querySelector("#board");
                board.insertBefore(listWrapper, board.lastElementChild);
            }

        });
        ajax.open("POST", "http://localhost:8080/board/list/save");
        ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        ajax.send("title=" + title + "&position=" + number + "&boards=" + board);
    },



    init: function () {
        const saveButton = document.querySelector("#save");
        saveButton.addEventListener("submit", this.listAdd);
    }
};

const card = {

    cardAdd: function (evt) {
        if (!(evt.target.className === "add-card")) return;

        const addCard = evt.target;
        const list = addCard.closest(".list");

        const addtitle = list.querySelector(".add-title").value;
        if(addtitle.length === 0) return;

        const listTitle = list.querySelector(".list-title").innerText;
        const cards = list.querySelector(".cards");
        const count = cards.childElementCount;

        const board = document.querySelector(".board-header-btn-text").innerText;

        const ajax = new XMLHttpRequest();
        ajax.addEventListener("load", function (res) {
            if (res.target.status === 200) {
                const card = document.createElement("div");
                card.classList.add("card");

                card.innerHTML = "<div class='card-title'>" + addtitle + "</div><div class='card-delete'>X</div>";

                cards.insertBefore(card, cards.lastElementChild);
            }
        });
        ajax.open("POST", "http://localhost:8080/card/add");
        ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        ajax.send("title=" + addtitle + "&boards=" + board + "&list=" + listTitle + "&position=" + count);
    },

    cardDelete : function(evt) {
        var cards = evt.target.closest(".cards");
        var card = evt.target.closest(".card");
        var cardTitle = evt.target.previousElementSibling.innerText;

        console.log(cardTitle);

        //cards.removeChild(card);
        var ajax = new XMLHttpRequest();
        ajax.addEventListener("load", function(res) {
            if(res.target.status === 200) {
                cards.removeChild(card);
            }
        });
        ajax.open("POST", "http://localhost:8080/card/delete");
        ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        ajax.send("cardtitle="+cardTitle);
    },

    listDelete : function(evt) {
        var listWrapper = evt.target.closest(".list-wrapper");
        var listTitle = listWrapper.querySelector(".list-title").innerText;
        var board = document.querySelector("#board");


        var ajax = new XMLHttpRequest();
        ajax.addEventListener("load", function(res) {
           if(res.target.status === 200) {
               board.removeChild(listWrapper);
           }
        });
        ajax.open("POST", "http://localhost:8080/list/delete");
        ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        ajax.send("listtitle="+listTitle);

    },

    clicked : function(evt) {
        console.log(evt.target);
        if(evt.target.className === "add-card") return this.cardAdd(evt);
        if(evt.target.className === "card-delete") return this.cardDelete(evt);
        if(evt.target.className === "list-delete") return this.listDelete(evt);
    },

    init: function () {
        const board = document.querySelector("#board");
        board.addEventListener("click", this.clicked.bind(card));
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
