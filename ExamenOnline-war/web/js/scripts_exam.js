$(function () {
    $('#main-wrapper').html('Hola k ase.');

    $.ajax({
        type: "POST",
        url: "ExamServlet",
        dataType: "json",
        success: function (jsonResponse) {
            console.log('Response received from ExamServlet!');
            loadData(jsonResponse);
        }
    });

    $('#exam').submit(function (evt) {
        $examAnswers = JSON.stringify($(this).serializeArray());
        $('#main-content').html('Enviando respuestas... Por favor, espere.');
        console.log('Mandando respuestas...');
        $.ajax({
            type: "POST",
            url: "CorrectionServlet",
            dataType: "json",
            data: $examAnswers,
            success: function (jsonResponse) {
                $('#main-content').html(jsonResponse);
                loadCorrection(jsonResponse);
            }
        });
        evt.preventDefault();
    });
});

function loadData($resp) {
    console.log('Loading questions...');
    $exam = '';
    $.each($resp, function (i, item) {
        $id = $(this).attr('questionsId');
        $q = $resp[i];
        switch ($q.questionsIdType.typesId) {
            case 2: // Checkbox
                $exam += '<div class="form-group">';
                $exam += '<b>' + (i + 1) + ' - ' + $q.questionsTitle + '</b><br/>';
                $.each($q.optionsList, function (j) {
                    $exam += '<div class="checkbox">';
                    $exam += '<label>';
                    $exam += '<input type="checkbox" name="' + $id + '" value="' + j + '" />' + $q.optionsList[j].optionsOption;
                    $exam += '</label>';
                    $exam += '</div>';
                });
                $exam += '</div>';
                console.log('Checkbox!');
                break;
            case 3: // Select
                $exam += '<div class="form-group">';
                $exam += '<label for="quest_' + $id + '">';
                $exam += (i + 1) + ' - ' + $q.questionsTitle;
                $exam += '</label>';
                $exam += '<select class="form-control" name="' + $id + '" id="' + $id + '">';
                $.each($q.optionsList, function (j) {
                    $exam += '<option value="' + j + '">';
                    $exam += $q.optionsList[j].optionsOption;
                    $exam += '</option>';
                });
                $exam += '</select>';
                $exam += '</div>';
                console.log('Select!');
                break;
            case 4: // Text
                $exam += '<div class="form-group">';
                $exam += '<label for="quest_' + $id + '">';
                $exam += (i + 1) + ' - ' + $q.questionsTitle;
                $exam += '</label>';
                $exam += '<input type="text" class="form-control" name="' + $id + '" id="' + $id + '" required />';
                $exam += '</div>';
                console.log('Text!');
                break;
            case 5: // Radio
                $exam += '<div class="form-group">';
                $exam += '<b>' + (i + 1) + ' - ' + $q.questionsTitle + '</b><br/>';
                $.each($q.optionsList, function (j) {
                    $exam += '<div class="radio">';
                    $exam += '<label>';
                    $exam += '<input type="radio" name="' + $id + '" value="' + j + '" required />' + $q.optionsList[j].optionsOption;
                    $exam += '</label>';
                    $exam += '</div>';
                });
                $exam += '</div>';
                console.log('Radio!');
                break;
            case 6: // Multiple
                $exam += '<div class="form-group">';
                $exam += '<label for="quest_' + $id + '">';
                $exam += (i + 1) + ' - ' + $q.questionsTitle;
                $exam += '</label>';
                $exam += '<select multiple class="form-control" name="' + $id + '" id="' + $id + '" required>';
                $.each($q.optionsList, function (j) {
                    $exam += '<option value="' + j + '">';
                    $exam += $q.optionsList[j].optionsOption;
                    $exam += '</option>';
                });
                $exam += '</select>';
                $exam += '</div>';
                console.log('Multiple!');
                break;
            default:
                // No debería darseeste caso
                console.log('Pregunta de tipo no esperado.');
                break;
        }
        $exam += '<hr/>';
    });
    $exam += '<input type="hidden" name="startTime" value="' + $.now() + '" />';
    $exam += '<input type="submit" class="btn btn-block btn-primary" value="Corregir"/>';
    $('#exam').html($exam);
}

function loadCorrection($corrData) {
    console.log('Loading questions...');
    $score = 0;
    $corr = '<table class="table table-responsive"><tr class="info"><th width="65%">Pregunta</th><th>Resp. correcta</th><th>Resp. dada</th><th>Punt.</th></tr>';
    $.each($corrData, function (i) {
        if ($corrData[i].score === 0) {
            $corr += '<tr class="danger">';
        } else {
            $corr += '<tr>';
        }
        $corr += '<td><b>' + $corrData[i].question + '</b></td>';
        $corr += '<td>' + $corrData[i].correctAnswer + '</td>';
        $corr += '<td>' + $corrData[i].userAnswer + '</td>';
        $corr += '<td>' + $corrData[i].score + '</td>';
        $corr += '</tr>';
        $score += $corrData[i].score;
    });
    $corr += '</table>';
    $score = (1232424.2242).toFixed(2);
    $('#main-content').append($corr);
    $('#main-content').append('<div style="width: 100%; text-align:center; font-size: 25px; font-weight: bold; margin: 15px;" >Nota: ' + $score + '</div>');
    $('#main-content').append('<a href="../index.html" class="btn btn-block btn-primary">Volver al inicio</a>');
    $('#main-content').append('<a href="../summary.html" class="btn btn-block btn-primary">Ver resumen de resultados</a>');
}