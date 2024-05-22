document.addEventListener('DOMContentLoaded', () => {
    fetch('/servlet/displaySeat/jjs')
        .then(response => response.json())
        .then(data => {
            console.log('Received data from server:', data);
            data.forEach(seat => {
                const seatID = `seat${seat.seatNum}`;
                const seatElement = document.getElementById(seatID);
                const status = seat.isRented ? 'occupied' : 'available';
                seatElement.classList.add(status);
            });
        })
        .catch(error => console.error('Error fetching seat data:', error));

    const popup = document.getElementById('rentedSeatModal');
    const confirmButton = document.getElementById('confirm-button');
    const cancelButton = document.getElementById('cancel-button');
    let selectedSeatId = null;

    // 좌석 클릭 이벤트
    document.querySelectorAll('.seat').forEach(seat => {
        seat.addEventListener('click', (event) => {
            selectedSeatId = event.currentTarget.id;
            document.getElementById('seatNum').textContent = selectedSeatId.replace('seat', '');
            popup.style.display = 'flex';
        });
    });

    // 모달 닫기 버튼 이벤트
    cancelButton.addEventListener('click', () => {
        popup.style.display = 'none';
    });

    // Confirm 버튼 클릭 이벤트
    confirmButton.addEventListener('click', () => {
        const locationId = document.getElementById('selectLocation').value;
        console.log(`Location ID: ${locationId}, Seat ID: ${selectedSeatId}`);
        popup.style.display = 'none';
    });

    // 모달 외부 클릭 시 모달 닫기
    window.addEventListener('click', (event) => {
        if (event.target === popup) {
            popup.style.display = 'none';
        }
    });
});

