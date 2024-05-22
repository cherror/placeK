document.addEventListener('DOMContentLoaded', function() {
    const selectSeatBtn = document.getElementById('selectSeatBtn');
    const checkSeatBtn = document.getElementById('checkMySeatBtn');

    selectSeatBtn.addEventListener('click', function() {
        window.location.href = '/html/displaySeat.html';
    });

    checkSeatBtn.addEventListener('click', function() {
        fetch('/servlet/chooseOption', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        })
            .then(response => response.json())
            .then(data => {
                if (data.isRented) {
                    window.location.href = '/html/checkMySeat.html';
                } else {
                    alert('좌석 배정 후 이용 가능합니다');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('오류가 발생했습니다. 다시 시도해주세요.');
            });
    });
});
