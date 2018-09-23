$('document').ready(function() {

            var isChecked = $(".hiddenNecessary").val();
            if(isChecked == "true"){
                $(".ch").prop('checked', true);
            }

        });