$(document).ready(function() {
    $(".xoa-user").click(function(){

        var self = $(this);
        var id = $(this).closest("tr").attr("data-id");
        $.ajax({
            url:"http://localhost:8081/home-admin/delete",
            type:"GET",
            data:{
                id:id,

            },
            success: function(value){
                self.closest("tr").remove();
            }

        })
});



