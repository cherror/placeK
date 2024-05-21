function submitForm() {
    const selectLocation = document.getElementById("selectLocation");
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
