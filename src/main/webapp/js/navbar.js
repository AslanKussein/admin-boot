function logout() {
    $.post("/login.jsp", function () {
        window.location.href = "/logout";
    });
}