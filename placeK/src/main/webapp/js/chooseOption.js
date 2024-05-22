document.addEventListener('DOMContentLoaded', function() {
    const selectSeatBtn = document.getElementById('selectSeatBtn');
    const checkSeatBtn = document.getElementById('checkSeatBtn');

    selectSeatBtn.addEventListener('click', function() {
        window.location.href = '/html/displaySeat.html';
    });

    checkSeatBtn.addEventListener('click', function() {
        window.location.href = '/html/checkMySeat.html';
    });
});
