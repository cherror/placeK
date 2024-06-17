document.addEventListener("DOMContentLoaded", function () {
    const returnButton = document.getElementById("returnButton");
    const extendButton = document.getElementById("extendButton");
    const beforePageBtn = document.getElementById("beforePageBtn");

    const returnPopup = document.getElementById("returnPopup");
    const extendPopup = document.getElementById("extendPopup");
    const cancelButton = document.getElementById("cancelButton");
    const confirmButton = document.getElementById("confirmButton");
    const cancelButton2 = document.getElementById("cancelButton2");
    const confirmButton2 = document.getElementById("confirmButton2");

    let userSeatInfo = null;

    // 사용자 정보 불러오기
    fetch("/servlet/checkMySeat")
        .then(response => response.json())
        .then(user => {
            if (user) {
                document.getElementById("userID").textContent = `ID: ${user.userID}`;
                document.getElementById("major").textContent = `Major: ${user.major}`;
                document.getElementById("location").textContent = `${user.location}`;
                document.getElementById("seatNum").textContent = `${user.seatNum}`;
                document.getElementById("rentedTime").textContent = `${user.rentedTime}`;
                document.getElementById("returnTime").textContent = `${user.returnTime}`;
                userSeatInfo = user;
            }
        })
        .catch(error => {
            console.error("Error fetching user session:", error);
            document.getElementById("welcomeMessage").textContent = "Error loading user information.";
        });

    // 반납 버튼 클릭 시
    if (returnButton) {
        returnButton.addEventListener("click", function () {
            returnPopup.style.display = "flex";
        });
    } else {
        console.error("Element with id 'returnButton' not found");
    }

    // 연장 버튼 클릭 시
    if (extendButton) {
        extendButton.addEventListener("click", function () {
            if (userSeatInfo) {
                fetch(`/servlet/checkExtendable?seatID=${userSeatInfo.seatNum}&locationID=${userSeatInfo.locationID}`)
                    .then(response => response.json())
                    .then(data => {
                        if (data.status === "extendable") {
                            extendPopup.style.display = "flex";
                        } else {
                            alert("연장 가능한 시간이 아닙니다.");
                        }
                    })
                    .catch(error => {
                        console.error("Error checking extendable time:", error);
                        alert("연장 가능한 시간 확인에 실패했습니다.");
                    });
            }
        });
    } else {
        console.error("Element with id 'extendButton' not found");
    }

    // 반납 팝업 취소 버튼 클릭 시
    if (cancelButton) {
        cancelButton.addEventListener("click", function () {
            returnPopup.style.display = "none";
        });
    } else {
        console.error("Element with id 'cancelButton' not found");
    }

    // 반납 팝업 확인 버튼 클릭 시
    if (confirmButton) {
        confirmButton.addEventListener("click", function () {
            fetch(`/servlet/returnSeat?seatID=${userSeatInfo.seatNum}&locationID=${userSeatInfo.locationID}`)
                .then(response => {
                    if (response.ok) {
                        alert("반납이 완료되었습니다.");
                        window.location.href = "/html/chooseOption.html";
                    } else {
                        throw new Error("반납 실패");
                    }
                })
                .catch(error => {
                    console.error("Error returning seat:", error);
                    alert("반납에 실패했습니다. 다시 시도해주세요.");
                });
        });
    } else {
        console.error("Element with id 'confirmButton' not found");
    }

    // 연장 팝업 취소 버튼 클릭 시
    if (cancelButton2) {
        cancelButton2.addEventListener("click", function () {
            extendPopup.style.display = "none";
        });
    } else {
        console.error("Element with id 'cancelButton2' not found");
    }

    // 연장 팝업 확인 버튼 클릭 시
    if (confirmButton2) {
        confirmButton2.addEventListener("click", function () {
            fetch(`/servlet/extendSeat?seatID=${userSeatInfo.seatNum}&locationID=${userSeatInfo.locationID}`)
                .then(response => response.json())
                .then(data => {
                    if (data.status === "success") {
                        alert("좌석 연장이 완료되었습니다.");
                        location.reload();
                    } else if (data.status === "notime") {
                        alert("좌석 시간이 30분 미만으로 남았을 때 연장 가능합니다.");
                        location.reload();
                    } else {
                        alert("좌석 연장에 실패하였습니다.");
                        location.reload();
                    }
                })
                .catch(error => {
                    console.error("Error extending seat:", error);
                    alert("연장에 실패했습니다. 다시 시도해주세요.");
                });
        });
    } else {
        console.error("Element with id 'confirmButton2' not found");
    }

    // 이전 페이지 버튼 클릭 시
    if (beforePageBtn) {
        beforePageBtn.addEventListener("click", function () {
            window.location.href = "/html/chooseOption.html";
        });
    } else {
        console.error("Element with id 'beforePageBtn' not found");
    }
});