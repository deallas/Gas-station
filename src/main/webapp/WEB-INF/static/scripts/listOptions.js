$(function() {
    $("#b-options-container").click(function() {
        if($("#options-container-form").is(":hidden")) {
            $("#b-options-container i").removeClass();
            $("#b-options-container i").addClass("icon-caret-down");
        } else {
            $("#b-options-container i").removeClass();
            $("#b-options-container i").addClass("icon-caret-right");                    
        }
        $("#options-container-form").slideToggle();
        return false;
    });

    $("#b-options-container-right").click(function() {
        $("#options-container-orders-availables option:selected").remove().appendTo("#options-container-orders");
        return false;
    });
    $("#b-options-container-left").click(function() {
        $("#options-container-orders option:selected").remove().appendTo("#options-container-orders-availables");
        return false;
    });
    $("#b-options-container-up").click(function() {
        $("#options-container-orders option:selected").each( function() {
            var newPos = $("#options-container-orders option").index(this) - 1;
            if (newPos > -1) {
                $("#options-container-orders option").eq(newPos).before('<option value="' + $(this).val() + '" selected="selected">' + $(this).text() + '</option>');
                $(this).remove();
            }
        });            

        return false;
    });
    $("#b-options-container-down").click(function() {
        var countOptions = $("#options-container-orders option").size();
        $("#options-container-orders option:selected").each( function() {
            var newPos = $("#options-container-orders option").index(this) + 1;
            if (newPos < countOptions) {
                $("#options-container-orders option").eq(newPos).after('<option value="' + $(this).val() + '" selected="selected">' + $(this).text() + '</option>');
                $(this).remove();
            }
        });            

        return false;
    });
    $("#b-options-container-save").click(function() {
        $("#options-container-orders option").prop("selected", "selected");
    });
    $("#b-options-container-reset").click(function() {
        $("#options-container-orders option").prop("selected", "selected");
    });
});