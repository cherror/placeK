document.addEventListener('DOMContentLoaded', () => {
    const popup = document.getElementById('rentedSeatModal');
    const confirmButton = document.getElementById('confirm-button');
    const cancelButton = document.getElementById('cancel-button');
    let selectedSeatID = null;
    const locationID = document.getElementById('locationID').value;

    function formatTime(minutes) {
        const hours = Math.floor(minutes / 60);
        const mins = minutes % 60;
        return `[${hours.toString().padStart(2, '0')}:${mins.toString().padStart(2, '0')}]`;
    }

    fetch(`/servlet/displaySeatInfo?locationID=${locationID}`)
        .then(response => response.json())
        .then(data => {
            data.forEach(seat => {
                const seatID = `seat${seat.seatNum}`;
                const seatElement = document.getElementById(seatID);
                const status = seat.isRented ? 'occupied' : 'available';
                // const remainingTime = seat.remainingTime;
                seatElement.classList.add(status);

                const seatInfoTextElement = seatElement.querySelector('.seat-info-text');
                const remainingTimeElement = seatInfoTextElement.querySelector('.remaining-time');
                if (seat.isRented) {
                    seatInfoTextElement.classList.add('seat-rented');
                    remainingTimeElement.textContent = seat.remainingTime ? seat.remainingTime : '';
                } else {
                    seatInfoTextElement.classList.remove('seat-rented');
                    remainingTimeElement.textContent = '';
                }
            });
        })
        .catch(error => console.error('Error fetching seat data:', error));

    document.querySelectorAll('.seat').forEach(seat => {
        seat.addEventListener('click', (event) => {
            selectedSeatID = event.currentTarget.id;
            fetch("/servlet/seatDetail?seatID=" + selectedSeatID + "&locationID=" + locationID)
                .then(response => response.json())
                .then(data => {
                    document.getElementById("seatLocation").textContent = data.locationName;
                    document.getElementById("seatNum").textContent = data.seatNum;
                    document.getElementById("seatMajor").textContent = data.availableMajors;
                    document.getElementById("seatStatus").textContent = data.isRented ? "대여 중" : "사용 가능";

                    if(data.isRented){
                        alert("해당 좌석은 사용중입니다.");
                        location.reload();
                        popup.style.display = 'none';
                    } else{
                        popup.style.display = 'flex';
                    }
                })
                .catch(error => console.error("Error fetching seat data:", error));
        });
    });

    cancelButton.addEventListener('click', () => {
        popup.style.display = 'none';
    });

    confirmButton.addEventListener('click', () => {
        fetch("/servlet/confirm?seatID=" + selectedSeatID + "&locationID=" + locationID)
            .then(response => response.json())
            .then(data => {
                if (data.status === "success") {
                    alert("좌석 대여가 완료되었습니다.");
                    location.reload();
                    popup.style.display = 'none';
                    window.location.href = "/html/chooseOption.html";
                } else if (data.status === "major_mismatch") {
                    alert("이용 가능 학부가 아닙니다.");
                    popup.style.display = 'none';
                } else if (data.status === "seat_already_assigned") {
                    alert("사용 중인 좌석을 반납 후 이용해 주세요.");
                    popup.style.display = 'none';
                } else {
                    alert("좌석 대여에 실패했습니다.");
                    location.reload();
                    popup.style.display = 'none';
                }
            })
            .catch(error => console.error('Error renting seat:', error));
    });

    window.addEventListener('click', (event) => {
        if (event.target === popup) {
            popup.style.display = 'none';
        }
    });
});