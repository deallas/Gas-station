$().ready(function() {
    $('#modal-from-dom').bind('show', function() { 
        $(this).find('.btn-yes').attr('href', $(this).data('href'));                                
    });

    $('.btn-confirm').click(function(e) {
        e.preventDefault();

        $('#modal-from-dom').data('href', $(this).attr('href')).modal('show');
    });
    
    $('.btn-no , .close').click(function(e) {
        e.preventDefault();
        
        $('#modal-from-dom').modal('hide');
    });
});