document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('sign-in-form');
    const popupModal = document.getElementById('popupModal');
    const closeButton = document.querySelector('.close-button');
    const popupMessage = document.getElementById('popupMessage');

    form.addEventListener('submit', (event) => {
        event.preventDefault();  // 폼 제출 막기

        // 로그인 요청 보내기 (여기서는 간단히 메시지만 표시)
        // 실제로는 서버로 요청을 보내고 응답에 따라 메시지를 변경해야 함
        popupMessage.textContent = 'Sign in successful!';
        popupModal.style.display = 'block';
    });

    closeButton.addEventListener('click', () => {
        popupModal.style.display = 'none';
    });

    window.addEventListener('click', (event) => {
        if (event.target === popupModal) {
            popupModal.style.display = 'none';
        }
    });
});
