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
        this.habilitarEstablecimiento = function (nombre) {            
            return $http({
                method: 'GET',
                url: 'rest/establecimientos/habilitar/'+nombre
            });            
        };        
        
        /**
         * 
         * @returns {List} lista de todos los establecimientos registrados y sin habilitar
         */
        this.consultarTodosEstablecimientosSinHabilitar = function () {            
            return $http({
                method: 'GET',
                url: 'rest/establecimientos/sinhabilitar'
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
        this.registrarEstablecimiento = function (idEstablecimiento, nombre, nit, descripcion, direccion, horaInicio, horaCierre, multa, localidad, telefono) {            
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
        this.registrarSala = function (idSala, idEstablecimiento, nombreEstablecimiento, nit, descripcionEstablecimiento, direccion, horaInicio, horaCierre, multa, localidad, telefono, precio, descripcion, nombre) {            
            return $http({
                method: 'POST',
                url: 'rest/establecimientos/',
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
                url: 'rest/establecimientos/sala/'+idsala
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
            var calificacion={'idCalificacion':0,'ensayo':ensayo,'calificacionBanda':0,'calificacionEstablecimiento':calificacione, 'descripcion':descripcions};
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
                url: 'rest/reservacion/clienteReserva/cliente/'+clienteid
            });            
        };
        
        /**
         * 
         * @param {type} idSala
         * @param {type} fecha
         * @param {type} duracion
         * @returns {unresolved}
         */
        this.registroReserva = function (idSala, fecha, duracion) {            
            var sala = $http.get('rest/establecimientos/sala/'+idSala);
            var reserv = {"idReserva":0,"sala":sala,"fecha":fecha,"tiempo":duracion};
            return $http({
                method: 'POST',
                url: 'rest/reservacion/registroReserv',
                data:reserv
            });
        };
        /**
         * 
         * @param {type} idCliente
         * @param {type} idReserva
         * @param {type} descripcion
         * @returns {unresolved}
         */
        this.alquilerCliente = function (idCliente, idReserva, descripcion) {      
            //var cliente = $http.get('/rest/reservacion/cliente/'+idCliente);
            var reserva = $http.get('rest/reservacion/'+idCliente);
            var alq = {"idAlquiler":0,"ensayo":null,"reservacion":reserva,"tipoDePago":null,"valorMulta":0,"captacion":0};
            return $http({
                method: 'POST',
                url: 'rest/reservacion/alquilerCliente',
                data:alq
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
        
        this.registrarCliente = function (idCliente, nombre, descripcion) {            
            var cliente={"idCliente":idCliente,"nombre":nombre,"descripcion":descripcion,"ensayos":[]};
            return $http({
                method: 'POST',
                url: 'rest/reservacion/cliente',
                data:cliente
            });            
        };
    }
            
    );

})();




