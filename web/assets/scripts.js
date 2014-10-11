var confirmElements = document.querySelectorAll('.confirm');
for (var i = 0; i < confirmElements.length; i++) {
    confirmElements[i].onclick = function () {
        if(! confirm("Are you sure?")) {
            return false;
        }
    };
}