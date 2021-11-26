$(document).ready(function () {
    $('.popup').click(function () {
        let src = $(this).attr('src')
        $('.modal-img').modal("show")
        $('#popup-img').attr('src', src)
    })

})

$(document).on("click", "#deleteB", function () {
    swal({
        title: "Are you sure?",
        text: "Once deleted, you will not be able to recover this comment!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((willDelete) => {
            if (willDelete) {
                let commentId = $(this).parent().find('input').val();
                let workingObject = $(this);

                $.ajax({
                    type: "DELETE",
                    url: "/films/comment" + commentId,
                    success: function () {
                        workingObject.closest("tr").remove()
                    }, error: function (e) {
                        alert(e)
                        console.log("ERROR", e)
                    }
                })
                swal("Comment has been deleted!", {
                    icon: "success",
                });
            } else {
                swal("Comment is safe!");
            }
        });
})