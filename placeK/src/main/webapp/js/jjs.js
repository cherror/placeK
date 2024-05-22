document.addEventListener('DOMContentLoaded', () => {
    // 좌석 상태를 나타내는 예제 데이터 (실제로는 서버에서 받아와야 함)
    const seatStatus = {
        seat1: 'available',
        seat2: 'occupied',
        seat3: 'available',
        seat4: 'occupied',
        seat5: 'available',
        seat6: 'occupied',
        seat7: 'available',
        seat8: 'occupied',
        seat9: 'available',
        seat10: 'occupied',
        seat11: 'available',
        seat12: 'occupied',
        seat13: 'available',
        seat14: 'occupied',
        seat15: 'available',
        seat16: 'occupied',
        seat17: 'available',
        seat18: 'occupied',
        seat19: 'available',
        seat20: 'occupied',
        seat21: 'available',
        seat22: 'occupied',
        seat23: 'available',
        seat24: 'occupied',
        seat25: 'available',
        seat26: 'occupied',
        seat27: 'available',
        seat28: 'occupied',
        seat29: 'available',
        seat30: 'occupied',
        seat31: 'available',
        seat32: 'occupied',
        seat33: 'available',
        seat34: 'occupied',
        seat35: 'available',
        seat36: 'occupied',
        seat37: 'available',
        seat38: 'occupied',
        seat39: 'available',
        seat40: 'occupied'
    };

    // 좌석 상태에 따라 색상 변경
    for (const [seatId, status] of Object.entries(seatStatus)) {
        const seatElement = document.getElementById(seatId);
        if (seatElement) {
            seatElement.classList.add(status);
        }
    }

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

