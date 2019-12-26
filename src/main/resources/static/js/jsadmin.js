$(document).ready(function () {
    $(".call-del").click(function () {
        var id = $(this).closest("tr").attr("data-id");

        $('#myModal').modal('toggle');
        $('#myModal').find('.btn-danger').attr("data-id", id);
    })

    $("#myModal .btn-danger").click(function () {
        var self = $(this);
        var id = $('#myModal').find('.btn-danger').attr("data-id");
        $.ajax({
            url: "/home-admin/delete",
            type: "DELETE",
            data: JSON.stringify({
                id: parseInt(id)
            }),
            contentType: 'application/json; charset=utf-8',
            success: function (value) {
                $('.table').find('.table-' + id).remove();

                $('#myModal').modal('toggle');
            },
            error: function () {
                alert('error')
            }
        })
    })


    $('.activeUser').change(function () {
        if (this.checked) {
            var id = $(this).closest("tr").attr("data-id");
            var thiss = $(this);

            $.ajax({
                url: "/home-admin/editActiveUserA",
                type: "GET",
                data: {
                    id: id,
                },
                success: function (data) {
                    thiss.closest("tr").find("select").val("ROLE_USER")
                },
                error: function () {
                    alert('error')

                }
            })
        }
        else {
            var id = $(this).closest("tr").attr("data-id");
            var thiss = $(this);
            $.ajax({
                url: "/home-admin/editActiveUserA",
                type: "GET",
                data: {
                    id: id,
                },
                success: function (data) {
                    thiss.closest("tr").find("select").val("ROLE_GUEST")
                },
                error: function () {
                    alert('error')
                }
            })
        }
    })

});

$('.form-control').change(function () {
    if (this.change) {
        var id = $(this).closest("tr").attr("data-id");
        var role = $(this).val();
        var thiss = $(this);
        $.ajax({
            type: "PUT",
            url: "/home-admin/change-role",
            data: JSON.stringify({
                id: parseInt(id),
                role: role
            }),
            contentType: 'application/json',
            dataType: 'json',
            responseType: 'application/json',
            crossDomain: true,
            success: function (data) {
                thiss.closest("tr").find("input").prop('checked', data.enabled)

            },
            error: function (e) {
            }
        });
    } else {
        var id = $(this).closest("tr").attr("data-id");
        var role = $(this).val();
        var thiss = $(this);
        $.ajax({
            type: "PUT",
            url: "/home-admin/change-role",
            data: JSON.stringify({
                id: parseInt(id),
                role: role
            }),
            contentType: 'application/json',
            dataType: 'json',
            responseType: 'application/json',
            crossDomain: true,
            success: function (data) {

                thiss.closest("tr").find("input").prop('checked', data.enabled)

            },
            error: function (e) {
            }
        });
    }
});

function myFunction() {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
};