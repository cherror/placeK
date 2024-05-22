document.addEventListener("DOMContentLoaded", function() {
    // 반납 모달 열기
    const returnPopup = document.getElementById("returnPopup");
    const returnButton = document.getElementById("returnButton");
    const cancelButton = document.getElementById("cancelButton");

    returnButton.addEventListener("click", function() {
        returnPopup.style.display = "flex";
    });

    // 반납하기 버튼 클릭 시
    const confirmButton = document.getElementById("confirmButton");
    confirmButton.addEventListener("click", function() {
        fetch("/servlet/returnSeat")
            .then(response => {
                if (response.ok) {
                    return response.json();
                }
                throw new Error("Failed to return seat.");
            })
            .then(data => {
                // 반납이 완료되면 이동 및 알림
                window.location.href = "/html/chooseOption.html";
                alert("반납이 완료되었습니다.");
            })
            .catch(error => {
                console.error("Error returning seat:", error);
                // 실패할 경우 알림
                alert("반납에 실패했습니다. 다시 시도해주세요.");
            });
    });

    // 취소 버튼 클릭 시
    cancelButton.addEventListener("click", function() {
        returnPopup.style.display = "none";
    });

    // 사용자 정보 불러오기
    fetch("/servlet/checkMySeat")
        .then(response => response.json())
        .then(user => {
            if (user) {
                document.getElementById("userID").textContent = `ID: ${user.userID}`;
                document.getElementById("major").textContent = `Major: ${user.major}`;
                document.getElementById("location").textContent = `Location: ${user.location}`;
                document.getElementById("seatNum").textContent = `Seat Number: ${user.seatNum}`;
                document.getElementById("rentedTime").textContent = `Check-in time: ${user.rentedTime}`;
            } else {
                document.getElementById("welcomeMessage").textContent = "No user is logged in.";
            }
        })
        .catch(error => {
            console.error("Error fetching user session:", error);
            document.getElementById("welcomeMessage").textContent = "Error loading user information.";
        });
});
