
let articulos = [];

let URL_BOL = 'http://localhost:1234/api/boligrafo';

let operacion_compraventa = 0; // 0 equivale a compra, 1 a reposición

$(document).ready(function () {


    // Borra y recarga abl con el maestro del inventario. 
    function refrescarListado() {

        $.get("http://my-json-server.typicode.com/desarrollo-seguro/dato/solicitudes",
            function (result) {

                $.get(URL_BOL, function (data) {

                    console.log(data);

                    let $padre = $('#listado');
                    let $maestro = $("maestro");

                    $padre.empty();

                    data.forEach(x => {
                        console.log("Processing item:", x);
                        let $linea = $('<tr>');

                        articulos.push({ id: x.id, nombre: x.nombre });
                        $linea.append($('<td class="renglon mt-3 md-3" style=display:none>').text(x.id));
                        $linea.append($('<td class="renglon mt-3 md-3">').text(x.color));
                        $linea.append($('<td class="renglon mt-3md-3">').text(x.escribe));
                        $linea.append($('<td class="renglon mt-3md-3 text-right">').text(x.nombre));
                        $linea.append($('<td class="text-center">').append($(`</button><button class="btn btn-warning btn-lg botonera boton_edicion">Editar</button>
                                </button><button class="btn btn-danger btn-lg botonera boton_baja">Borrar</button>
                                `)));

                        $padre.append($linea);

                    });

                    escondeDetalles();
                    $maestro.show();
                    $padre.show();

                    console.log(articulos);

                }).fail(function () {

                    console.log("Error");
                });

            });

        window.refrescarListado = refrescarListado;


    };



    //Esconde todos los detalles. Llamada por todos los procesos de invocado de detalle para luego solo llamar
    //el que interesa a la operación. 
    function escondeDetalles() {

        $("#nuevo").hide();

        window.escondeDetalles = escondeDetalles;

    }

    refrescarListado();

    // Refresca el detalle
    $("#boton_lista").on("click", function (event) {
        event.preventDefault();
        refrescarListado();

    });


    // Definiciones de eventos. (Clicks)


    // Habilita los controles para el cambio de precio
    $('#listado').on("click", ".boton_edicion", function (event) {
        event.preventDefault();

        let $row = $(this).closest('tr');
        let bolId = $row.find('td').eq(0).text();
        let bolColor = $row.find('td').eq(1).text();
        let bolEscribe = $row.find('td').eq(2).text();
        let bolNombre = $row.find('td').eq(3).text();

        escondeDetalles();
        let $precio = $("#edicion").show();

        $("#id_nuevo").val(bolId);
        $("#color_nuevo").val(bolColor);
        $("#escribe_nueevo").val(bolEscribe);
        $("#nombre_nuevo").val(prod);

    });

    // Graba el cambio de precio enviando petición al servidor.
    $("#boton_graba_edicion").on("click", function (event) {

        event.preventDefault();

        let $precio = $("#edicion");
        let bolId = Number($("#nuevo_id").val());
        let bolColor = $("#nuevo_color").val();
        let bolEscribe = $("#nuevo_escribe").val();
        let bolNombre = Number($("#nuevo_nombre").val());

        

        let envio = { id: bolId, color: bolColor, Escribe: bolEscribe, nombre: bolNombre};
        $.ajax({
            url: URL_BOL,
            method: "PUT",
            contentType: "application/json",
            data: JSON.stringify(envio),
            success: function (result) {
                console.log('Respuesta: ' + result);
                $precio.hide();
                refrescarListado();
            },
            error: function (xhr, status, error) {
                console.log('Error: ' + error);
            }
        });
        
    });

    // Cancela operación de cambio de precio. Borra el detalle y refresca el maestro. 
    $("#boton_cancela_edicion").on("click", function (event) {
        event.preventDefault();
        escondeDetalles();
    });


    // Dar de baja un producto. 
    $('#listado').on("click", ".boton_baja", function (event) {

        event.preventDefault()

        let $row = $(this).closest('tr');
        let solId = $row.find('td').eq(0).text();

        console.log("Id es: " + solId);

        if (confirm("Está seguro de que desea BORRAR este registro?")) {
            $.ajax({
                url: 'http://localhost:1234/api/boligrafo/' + solId,
                method: "DELETE",
                contentType: "application/json",
                success: function (result) {

                    console.log("resultado de la compra: " + result)

                },
                error: function (xhr, status, error) {

                    console.log("resultado de la compra: " + error)

                }
            });
        } else {
            // If the user clicked "Cancel", do nothing
            console.log("Operación de borrado cancelada.");
        }

        refrescarListado();

    });


    // Muestra los controles de detalle para la carga de datos del registro a dar de alta. 
    $("#boton_nuevo").on("click", function (event) {
        event.preventDefault();
        escondeDetalles();
        $("#nuevo").show();

    });


    // Graba los datos del registro a dar de alta. 
    $("#boton_graba_nuevo").on("click", function (event) {
        event.preventDefault();

        let color_nuevo = $('#color_nuevo').val();
        let escribe_nuevo = $('#escribe_nuevo').val();
        let nombre_nuevo = $("#nombre_nuevo").val();

        let envio = {
            id: 0,
            color: color_nuevo,
            escribe: escribe_nuevo,
            nombre: nombre_nuevo,
        };

        console.log(JSON.stringify(envio));

        $.ajax({
            url: URL_BOL, // Adjusted by Parcel to remove "/api"
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(envio),
            success: function (result) {
                console.log('Respuesta: ' + result);
                refrescarListado();
            },
            error: function (xhr, status, error, envio) {
                console.log('Error: ' + error + " . El envío era:" + JSON.stringify(envio));
                alert(xhr.responseText);
            }
        });

    });

    // Accionar del botón de Cancelar de la creaciñon de nuevo registro. 
    $("#boton_cancela_nuevo").on("click", function (event) {
        event.preventDefault();
        refrescarListado();
    });

    //Para el filtrado de la tabla. 
    $("#filtrado").on("keyup", function () {
        var value = $(this).val().toLowerCase();
        $("#listado tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });


});
