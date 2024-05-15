const btnaddproduct = document.getElementById("calladdform");
const boxaddform = document.getElementById("boxaddform");
btnaddproduct.addEventListener("click", (e) => {
    console.log("open");
    boxaddform.style.display = "block";
})
const btncloseaddform = document.getElementById("closeAdd");
btncloseaddform.addEventListener("click", (e) => {
    console.log("close");
    boxaddform.style.display = "none";
})
const btnDelete = document.querySelectorAll(".delete")
btnDelete.forEach(button =>{
    button.addEventListener("click", (e) => {
        console.log("delete");
        const confirm = window.confirm("bạn có chắc chắn muốn xóa sản phẩm ");
        const id = button.getAttribute("data-id");
        if (confirm){
            console.log("deleted");
            window.location.href="/deleteProduct/" + id;
        }
    })
})


