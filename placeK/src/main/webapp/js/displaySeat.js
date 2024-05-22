function submitForm() {
    const selectLocation = document.getElementById("selectLocation");
    if (!selectLocation) {
        console.error("Element with id 'selectLocation' not found");
        return;
    }
    const selectedValue = selectLocation.value;

    if (selectedValue) {
        fetch(`/servlet/displaySeat?locationID=${selectedValue}`)
            .then(response => response.text())
            .then(url => {
                if (url) {
                    window.location.href = url;
                } else {
                    console.error("Invalid URL");
                }
            })
            .catch(error => console.error("Error:", error));
    } else {
        alert("장소를 선택해 주세요");
    }
}

document.addEventListener("DOMContentLoaded", function () {
    const beforePageBtn = document.getElementById("beforePageBtn");

    // 이전 페이지 버튼 클릭 시
    if (beforePageBtn) {
        beforePageBtn.addEventListener("click", function () {
            window.location.href = "/html/chooseOption.html";
        });
    } else {
        console.error("Element with id 'beforePageBtn' not found");
    }
});
