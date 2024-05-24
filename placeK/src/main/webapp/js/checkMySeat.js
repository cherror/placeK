document.addEventListener("DOMContentLoaded", function() {
    let locationID;
    let seatNum;

    // 반납 모달 열기
    const returnPopup = document.getElementById("returnPopup");
    const returnButton = document.getElementById("returnButton");
    const cancelButton = document.getElementById("cancelButton");
    const confirmButton = document.getElementById("confirmButton");
    const extendButton = document.getElementById("extendButton");
    const beforePageBtn = document.getElementById("beforePageBtn");

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

                locationID = user.locationID;
                seatNum = user.seatNum;

            }
        })
        .catch(error => {
            console.error("Error fetching user session:", error);
            document.getElementById("welcomeMessage").textContent = "Error loading user information.";
        });

    if (returnButton) {
        returnButton.addEventListener("click", function() {
            returnPopup.style.display = "flex";
        });
    }

    // 반납하기 버튼 클릭 시
    if (confirmButton) {
        confirmButton.addEventListener("click", function() {
            fetch("/servlet/returnSeat?seatID="  + seatNum + "&locationID=" + locationID)
                .then(response => {
                    if (response.ok) {
                        return response.json();
                    }
                    throw new Error("Failed to return seat.");
                })
                .then(data => {
                    // 반납이 완료되면 이동 및 알림
                    alert("반납이 완료되었습니다.");
                    location.reload();
                })
                .catch(error => {
                    console.error("Error returning seat:", error);
                    // 실패할 경우 알림
                    alert("반납에 실패했습니다. 다시 시도해주세요.");
                });
        });
    }

    // 취소 버튼 클릭 시
    if (cancelButton) {
        cancelButton.addEventListener("click", function() {
            returnPopup.style.display = "none";
        });
    }

    // 연장하기 버튼 클릭 시
    if (extendButton) {
        extendButton.addEventListener("click", function() {
            fetch("/servlet/extendSeat?seatID="  + seatNum + "&locationID=" + locationID)
                .then(response => response.json())
                .then(data => {
                    if(data.status === "success"){
                        alert("좌석 연장이 완료되었습니다.");
                        location.reload();
                    } else {
                        alert("좌석 연장에 실패했습니다.");
                        location.reload();
                    }
                })
                .catch(error => console.error('Error renting seat:', error))
        });
    }

    // 이전 페이지 버튼 클릭 시
    if (beforePageBtn) {
        beforePageBtn.addEventListener("click", function() {
            window.location.href = "/html/chooseOption.html";
        });
    }
});
