$(function () {
    $('#main-wrapper').html('Hola k ase.');

    $.ajax({
        type: "POST",
        url: "SummaryServlet",
        dataType: "json",
        success: function (jsonResponse) {
            loadSummary(jsonResponse);
        }
    });
});

function loadSummary($summData) {
    $summ = '<h2>Resumen de exámenes</h2>';
    $summ += '<table class="table table-responsive"><tr class="info"><th width="40%">Usuario</th><th>Inicio</th><th>Final</th><th>Punt.</th></tr>';
    $.each($summData, function (i) {
        if ($summData[i].score < 5) {
            $summ += '<tr class="danger">';
        } else {
            $summ += '<tr>';
        }
        $summ += '<td><b>' + $summData[i].user + '</b></td>';
        $summ += '<td>' + $summData[i].starttime + '</td>';
        $summ += '<td>' + $summData[i].endtime + '</td>';
        $summ += '<td><b>' + $summData[i].score + '</b></td>';
        $summ += '</tr>';
    });
    $summ += '</table';
    $('#main-content').html($summ);
    $('#main-content').append('<a href="index.html" class="btn btn-block btn-primary">Volver al inicio</a>');
}