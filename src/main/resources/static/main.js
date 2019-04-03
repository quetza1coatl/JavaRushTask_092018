$('document').ready(function() {

            var value = $(".value").val();
            if(value === "all"){
                $(".r1").prop('checked', true);
            }
            if(value === "necessary"){
                $(".r2").prop('checked', true);
            }
            if(value === "unnecessary"){
                $(".r3").prop('checked', true);
            }

        });