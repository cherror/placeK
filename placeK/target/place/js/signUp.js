document.addEventListener("DOMContentLoaded", function () {
    const signUpForm = document.querySelector(".sign-form");

    signUpForm.addEventListener("submit", function (event) {
        event.preventDefault();

        const username = document.getElementById("username").value;
        const major = document.getElementById("major").value;
        const password = document.getElementById("password").value;
        const passwordCheck = document.getElementById("passwordCheck").value;

        if (!username || !major || !password || !passwordCheck) {
            alert("모든 정보를 기입해 주세요");
            return;
        }

        if (!username.match(/^\d+$/)) {
            alert("ID는 숫자로만 구성되어야 합니다.");
            return;
        }

        if (password !== passwordCheck) {
            alert("비밀번호가 일치하지 않습니다.");
            return;
        }

        fetch(signUpForm.action, {
            method: signUpForm.method,
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            },
            body: new URLSearchParams(new FormData(signUpForm)),
        })
            .then(response => {
                if (!response.ok) {
                    return response.text().then(text => { throw new Error(text); });
                }
                return response.json();
            })
            .then(data => {
                if (data.status) {
                    window.location.href = "/html/signIn.html";
                } else {
                    if (data.error === "USER_ALREADY_EXISTS") {
                        alert("이미 존재하는 ID입니다.");
                    } else if (data.error === "PASSWORD_MISMATCH") {
                        alert("비밀번호가 일치하지 않습니다.");
                    } else {
                        alert("회원가입에 실패했습니다. 다시 시도해주세요.");
                    }
                }
            })
            .catch(error => {
                alert("회원가입에 실패했습니다. 다시 시도해주세요.");
                console.error("Error:", error);
            });
    });
});
