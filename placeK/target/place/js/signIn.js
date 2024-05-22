document.addEventListener("DOMContentLoaded", function () {
    const signInForm = document.getElementById("sign-in-form");
    const moveToSignUpButton = document.querySelector(".move-to-signup-button");

    signInForm.addEventListener("submit", function (event) {
        event.preventDefault();

        const username = document.getElementById("username").value;
        const password = document.getElementById("password").value;

        if (!username || !password) {
            alert("ID 혹은 password를 입력하세요.");
            return;
        }

        fetch(signInForm.action, {
            method: signInForm.method,
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            },
            body: new URLSearchParams(new FormData(signInForm)),
        })
            .then(response => {
                return response.json().then(data => {
                    if (!response.ok) {
                        throw new Error(data.error || 'UNKNOWN_ERROR');
                    }
                    return data;
                });
            })
            .then(data => {
                if (data.status) {
                    window.location.href = "../../html/chooseOption.html";
                }
            })
            .catch(error => {
                if (error.message === "USER_NOT_FOUND") {
                    alert("아이디가 존재하지 않습니다.");
                } else if (error.message === "WRONG_PASSWORD") {
                    alert("비밀번호가 일치하지 않습니다.");
                } else {
                    alert("로그인에 실패했습니다. 다시 시도해주세요.");
                }
                console.error("Error:", error);
            });
    });

    moveToSignUpButton.addEventListener("click", function () {
        window.location.href = "/html/signUp.html";
    });
});
