document.addEventListener('DOMContentLoaded', () => {
    const popup = document.getElementById('rentedSeatModal');
    const confirmButton = document.getElementById('confirm-button');
    const cancelButton = document.getElementById('cancel-button');
    let selectedSeatID = null;
    const locationID = document.getElementById('locationID').value;

    //DB 값 불러와서 seatNum, isRented 표시
    fetch(`/servlet/displaySeatInfo?locationID=${locationID}`)
        .then(response => response.json())
        .then(data => {
            data.forEach(seat => {
                const seatID = `seat${seat.seatNum}`;
                const seatElement = document.getElementById(seatID);
                const status = seat.isRented ? 'occupied' : 'available';
                seatElement.classList.add(status);
            });
        })
        .catch(error => console.error('Error fetching seat data:', error));

    // 좌석 클릭 이벤트
    document.querySelectorAll('.seat').forEach(seat => {
        seat.addEventListener('click', (event) => {
            selectedSeatID = event.currentTarget.id;
            fetch("/servlet/rentedSeat?seatID=" + selectedSeatID + "&locationID=" + locationID)
                .then(response => response.json())
                .then(data => {
                    document.getElementById("seatLocation").textContent = data.locationName;
                    document.getElementById("seatNum").textContent = data.seatNum;
                    document.getElementById("seatMajor").textContent = data.availableMajors;
                    document.getElementById("seatStatus").textContent = data.isRented ? "대여 중" : "사용 가능";

                    if (data.isRented) {
                        confirmButton.addEventListener('click', () => {
                            alert('이 좌석은 이미 대여 중입니다.');
                        });
                    }
                })
                .catch(error => console.error("Error fetching seat data:", error));
            popup.style.display = 'flex';
        });
    });

    // 모달 닫기 버튼 이벤트
    cancelButton.addEventListener('click', () => {
        popup.style.display = 'none';
    });

    // Confirm 버튼 클릭 이벤트
    confirmButton.addEventListener('click', () => {
        fetch("/servlet/confirm?seatID=" + selectedSeatID + "&locationID=" + locationID)
            .then(response => response.json())
            .then(data => {
                alert(data.message)
                //새로고침하기
                location.reload();
                popup.style.display = 'none';
            })
            .catch(error => console.error('Error renting seat:', error));
    });

    // 모달 외부 클릭 시 모달 닫기
    window.addEventListener('click', (event) => {
        if (event.target === popup) {
            popup.style.display = 'none';
        }
    });
});

