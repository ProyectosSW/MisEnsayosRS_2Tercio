(function () {

    var app = angular.module('MisEnsayosRS', []);

    app.service('MisEnsayosRSRestAPI', function ($http) {
        
        /**
         * 
         * @param {Integer} idEsta identificacion del establecimiento a consultar
         * @returns {Establecimiento} establecimiento solicitado
         */
        this.consultaEstablecimientoId = function (idEsta) {            
            return $http({
                method: 'GET',
                url: 'rest/establecimientos/'+idEsta
            });            
        };
        
        /**
         * 
         * @param {type} nit
         * @returns {unresolved}
         */
        this.verificarlegalidad = function (nit) {     
            var nnit = nit.substring(0,13)
            return $http({
                method: 'GET',
                url: 'https://ccbogotaonline.herokuapp.com/empresas/'+nnit
            });            
        };
        
        /**
         * 
         * @param {type} nit
         * @returns {unresolved}
         */
        this.consultarEstablecimientosporNit = function (nit) {            
            return $http({
                method: 'GET',
                url: 'rest/establecimientos/nit/'+nit+nit
            });            
        };
        
        /**
         * 
         * @param {type} mombre
         * @returns {unresolved}
         */
        this.consultarEstablecimientosNombre = function (nombre) {            
            return $http({
                method: 'GET',
                url: 'rest/establecimientos/nombre/'+nombre
            });            
        };
        
        /**
         * 
         * @param {type} mombre
         * @returns {unresolved}
         */
        this.consultarEstablecimientosLocalidad = function (localidad) {            
            return $http({
                method: 'GET',
                url: 'rest/establecimientos/localidad/'+localidad
            });            
        };
        
        /**
         * 
         * @returns {List} lista de todos los establecimientos registrados
         */
        this.consultarTodosEstablecimientos = function () {            
            return $http({
                method: 'GET',
                url: 'rest/establecimientos/todos'
            });            
        };        
        
        /**
         * 
         * @returns {List} lista de todos los establecimientos registrados y habilitados
         */
        this.consultarEstablecimientosHabilitados = function () {            
            return $http({
                method: 'GET',
                url: 'rest/establecimientos/habilitados'
            });            
        };        
        
        /**
         * 
         * @param {String} nombre del establecimiento a habilitar
         * @returns {HttpStatus} respuesta a la operacion realizada de habilitacion de establecimiento
         */
        this.habilitarEstablecimiento = function (id) {            
            return $http({
                method: 'GET',
                url: 'rest/establecimientos/inhabilitados/'+id
            });            
        };        
        
        /**
         * 
         * @returns {List} lista de todos los establecimientos registrados y sin habilitar
         */
        this.consultarTodosEstablecimientosSinHabilitar = function () {            
            return $http({
                method: 'GET',
                url: 'rest/establecimientos/inhabilitados'
            });            
        };
        
        /**
         * 
         * @returns {unresolved}
         */
        this.consultarCantidadEstablcimientos = function () {            
            return $http({
                method: 'GET',
                url: 'rest/establecimientos/cantidad'
            });            
        };
        
        /**
         * 
         * @returns {unresolved}
         */
        this.consultarCantidadSalas = function () {            
            return $http({
                method: 'GET',
                url: 'rest/establecimientos/sala/cantidad'
            });            
        };
        
        /**
         * 
         * @param {type} id
         * @returns {unresolved}
         */
        this.consultarSalaPorEstablecimiento = function (id) {            
            return $http({
                method: 'GET',
                url: 'rest/establecimientos/sala/grupo/'+id
            });            
        };        
        
        /**
         * 
         * @param {Integer} idEstablecimiento identificacion del establecimiento a registrar
         * @param {String} nombre
         * @param {String} nit
         * @param {String} descripcion
         * @param {String} direccion
         * @param {Integer} horaInicio
         * @param {Integer} horaCierre
         * @param {Double} multa
         * @param {String} localidad
         * @param {String} telefono
         * @returns {HttpStatus} respuesta a la operacion realizada de registro de establecimiento
         */
        this.registrarEstablecimiento = function (idEstablecimiento, nombre, nit, descripcion, direccion, horaInicio, horaCierre, multa, localidad, telefono, cuenta) {            
            return $http({
                method: 'POST',
                url: 'rest/establecimientos/',
                data:   {
                            "idEstablecimiento": idEstablecimiento,
                            "nombre": nombre,
                            "nit": nit,
                            "descripcion": descripcion,
                            "direccion": direccion,
                            "horaInicio": horaInicio,
                            "horaCierre": horaCierre,
                            "multa": multa,
                            "localidad": localidad,
                            "telefono": telefono,
                            "cuenta": cuenta,
                            "instrumentos": [],
                            "salas": []
                        }
            });            
        };
        
        /**
         * 
         * @param {Integer} idSala identificacion de la sala a registrar
         * @param {type} idEstablecimiento
         * @param {Integer} idEstablecimiento identificacion del establecimiento a registrar
         * @param {String} nombreEstablecimiento
         * @param {String} nit
         * @param {String} descripcionEstablecimiento
         * @param {String} direccion
         * @param {Integer} horaInicio
         * @param {Integer} horaCierre
         * @param {Double} multa
         * @param {String} localidad
         * @param {String} telefono
         * @param {String} precio de la sala
         * @param {String} descripcion de la sala
         * @param {String} nombre de la sala
         * @returns {Http}
         */
        this.registrarSala = function (idSala, idEstablecimiento, nombreEstablecimiento, nit, descripcionEstablecimiento, direccion, horaInicio, horaCierre, multa, localidad, telefono, cuenta, precio, descripcion, nombre) {            
            return $http({
                method: 'POST',
                url: 'rest/establecimientos/sala/',
                data:   {
                            "idSala": idSala,
                            "establecimiento": {
                                "idEstablecimiento": idEstablecimiento,
                                "nombre": nombreEstablecimiento,
                                "nit": nit,
                                "descripcion": descripcionEstablecimiento,
                                "direccion": direccion,
                                "horaInicio": horaInicio,
                                "horaCierre": horaCierre,
                                "multa": multa,
                                "localidad": localidad,
                                "telefono": telefono,
                                "cuenta": cuenta,
                                "instrumentos": [],
                                "salas": []
                            },
                            "precio": precio,
                            "descripcion": descripcion,
                            "nombre": nombre,
                            "reservacions": []
                        }
            });            
        };        
        
        /**
         * 
         * @param {type} idsala
         * @returns {unresolved}
         */
        this.consultarSala = function (idsala) {            
            return $http({
                method: 'GET',
                url: 'rest/establecimientos/sala/consulta/'+idsala
            });            
        };        
        
        /**
         * 
         * @param {type} idCliente
         * @param {type} idEnsayo
         * @param {type} calificacione
         * @param {type} descripcions
         * @returns {unresolved}
         */
        this.califiEst = function ( idCliente, idEnsayo, calificacione, descripcions ) {    
            var ensayo=$http.get('rest/calificacion/ensayo/'+idEnsayo);
            var calificacion={"idCalificacion":0,"ensayo":ensayo,"calificacionBanda":0,"calificacionEstablecimiento":calificacione, "descripcion":descripcions};
             return $http({
                method: 'POST',
                url: 'rest/calificacion/',data:calificacion
            });            
        };
        
        /**
         * 
         * @param {type} clienteid
         * @returns {unresolved}
         */
        this.reservasCliente = function (clienteid) {            
            return $http({
                method: 'GET',
                url: 'rest/reservacion/clientereserva/'+clienteid
            });            
        };
        
        /**
         * 
         * @param {type} idReservacion
         * @returns {unresolved}
         */
        this.consultarReserva=function(idReservacion){
            return $http({
                method:'GET',
                url:'rest/reservacion/'+idReservacion
            });
        };        
        
        /**
         * 
         * @param {type} idSala
         * @param {type} fecha
         * @param {type} duracion
         * @returns {unresolved}
         */
        this.registroReserva = function (idSala, idEstablecimiento, nombreEstablecimiento, nit, descripcionEstablecimiento, direccion, horaInicio, horaCierre, multa, localidad, telefono, cuenta, precio, descripcion, nombre, fecha, hora, duracion) {            
            
            return $http({
                method: 'POST',
                url: 'rest/reservacion/registrors',
                data:{
                            "idReservacion": 10,
                            "sala": {
                              "idSala": idSala,
                              "establecimiento": {
                                    "idEstablecimiento": idEstablecimiento,
                                    "nombre": nombreEstablecimiento,
                                    "nit": nit,
                                    "descripcion": descripcionEstablecimiento,
                                    "direccion": direccion,
                                    "horaInicio": horaInicio,
                                    "horaCierre": horaCierre,
                                    "multa": multa,
                                    "localidad": localidad,
                                    "telefono": telefono,
                                    "cuenta":cuenta,
                                    "instrumentos": [],
                                    "salas": []
                              },
                              "precio": precio,
                              "descripcion": descripcion,
                              "nombre": nombre,
                              "reservacions": []
                            },
                            "fecha": fecha,
                            "tiempo": duracion,
                            "hora": hora,
                            "alquilers": []
                    }
            });
        };
        /**
         * 
         * @param {type} idCliente
         * @param {type} idReserva
         * @param {type} descripcion
         * @returns {unresolved}
         */
        this.alquilerCliente = function (idCliente, idReserva, fecha,tiempo,hora, descripcion) {      
            return $http({
                method: 'POST',
                url: 'rest/reservacion/alquilercliente',
                data:{
                        "idAlquiler":1,
                        "ensayo":{
                            "idEnsayo":1,
                            "cliente":{
                                "idCliente":idCliente,
                                "nombre":"ORCA",
                                "descripcion":"primo de delfines",
                                "ensayos":[]
                            },
                            "descripcion":descripcion,
                            "fechaCancelacion":null,
                            "calificacions":[],
                            "instrumentos":[],
                            "alquilers":[]
                        },
                        "reservacion":{
                            "idReservacion":idReserva,
                            "fecha":fecha,
                            "tiempo":tiempo,
                            "hora":hora,
                            "sala":{
                                "idSala":10,
                                "establecimiento":{
                                    "idEstablecimiento":1,
                                    "nombre":"ssss",
                                    "nit":"123456789",
                                    "direccion":"calle falsa 123",
                                    "horaInicio":1200,
                                    "horaCierre":1700,
                                    "multa":0,
                                    "localidad":"fontibon",
                                    "telefono":1234567,
                                    "instrumentos":[],
                                    "salas":[]
                                },
                                "precio":"30000",
                                "descripcion":"sala de orcas",
                                "nombre":"sala de orcas"
                            },
                            "alquilers":[]
                        },
                        "tipoDePago":"ninguno",
                        "valorMulta":0,
                        "captacion":"5%"
                    }
            });            
        };
        /**
         * 
         * @param {type} idAlquiler
         * @param {type} monto
         * @param {type} numtarjeta
         * @param {type} tipoP
         * @returns {unresolved}
         */
        this.pagar = function (idAlquiler, monto, numtarjeta, tipoP) {            
            var pago={"idAlquiler":idAlquiler,"monto":monto,"numtarjeta":numtarjeta,"tipoP":tipoP};
            return $http({
                method: 'POST',
                url: 'rest/reservacion/pagar',
                data:pago
            });            
        };
        
        /**
         * 
         * @param {type} idCliente
         * @param {type} nombre
         * @param {type} descripcion
         * @returns {unresolved}
         */
        this.registrarCliente = function (idCliente, nombre, descripcion) {            
            var cliente={"idCliente":idCliente,"nombre":nombre,"descripcion":descripcion,"ensayos":[]};
            return $http({
                method: 'POST',
                url: 'rest/reservacion/cliente',
                data:cliente
            });            
        };
        
        /**
         * 
         * @param {type} idCliente
         * @returns {unresolved}
         */
        this.consultarCliente = function (idCliente) {            
            return $http({
                method: 'GET',
                url: 'rest/reservacion/cliente/'+idCliente
            });            
        };        
        
        /**
         * 
         * @param {type} idCliente
         * @returns {unresolved}
         */        
        this.EstablecimientosEnsayados = function (idCliente) {            
            return $http({
                method: 'GET',
                url: 'rest/calificacion/ensayo/cliente/'+idCliente,
            });            
        };
        
        this.EnsayosEstablecimientos=function(nit){
            return $http({
                method: 'GET',
                url: 'rest/calificacion/ensayo/establecimiento/'+nit+nit+''
            });
        };
        
        /**
         * 
         * @param {type} idEnsayo
         * @param {type} idCliente
         * @param {type} nombre
         * @param {type} descripcion1
         * @param {type} descripcion2
         * @param {type} fechaCancelacion
         * @param {type} calificacionBanda
         * @param {type} calificacionEstablecimiento
         * @param {type} descripcion3
         * @returns {unresolved}
         */
        this.registrarCalificacionEstablecimiento = function (idEnsayo,idCliente, nombre, descripcion1, descripcion2, fechaCancelacion, calificacionBanda, calificacionEstablecimiento, descripcion3){
            var cali={
                        "idCalificacion": idEnsayo,
                        "ensayo": {
                          "idEnsayo": idEnsayo,
                          "cliente": {
                            "idCliente": idCliente,
                            "nombre": nombre,
                            "descripcion": descripcion1,
                            "ensayos": []
                          },
                          "descripcion": descripcion2,
                          "fechaCancelacion": fechaCancelacion,
                          
                        },
                        "calificacionBanda": calificacionBanda,
                        "calificacionEstablecimiento": calificacionEstablecimiento,
                        "descripcion": descripcion3
                     }
            return $http({
                method: 'POST',
                url: 'rest/calificacion/',
                data:cali
            });
            
        }
        
        this.registrarCalificacionCliente = function (idEnsayo,idCliente, nombre, descripcion1, descripcion2, fechaCancelacion, calificacionBanda, calificacionEstablecimiento, descripcion3){
            var cali={
                        "idCalificacion": idEnsayo,
                        "ensayo": {
                          "idEnsayo": idEnsayo,
                          "cliente": {
                            "idCliente": idCliente,
                            "nombre": nombre,
                            "descripcion": descripcion1,
                            "ensayos": []
                          },
                          "descripcion": descripcion2,
                          "fechaCancelacion": fechaCancelacion,
                          
                        },
                        "calificacionBanda": calificacionBanda,
                        "calificacionEstablecimiento": calificacionEstablecimiento,
                        "descripcion": descripcion3
                     }
            return $http({
                method: 'POST',
                url: 'rest/calificacion/estCalif',
                data:cali
            });
            
        }
        
        /**
         * 
         * @param {type} tarjeta
         * @returns {unresolved}
         */
        this.realizarPago = function (numeroTarjeta, codigoSeguridad, tipo, nombreCliente, cuentaDestino, descripcion, montoTransaccion) {            
            return $http({
                method: 'POST',
                url: 'rest/stubs/pasarelapagos/',
                data: {"numeroTarjeta":numeroTarjeta,
                    "codigoSeguridad":codigoSeguridad,
                    "tipo":tipo,
                    "nombreCliente":nombreCliente,
                    "cuentaDestino":cuentaDestino,
                    "descripcion":descripcion,
                    "montoTransaccion":montoTransaccion}
            });            
        }; 
    }
            
    );

})();




