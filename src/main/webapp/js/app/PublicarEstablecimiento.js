/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function () {

    var app = angular.module('PublicarEstablecimiento', []);

    app.service('PublicarEstablecimientoRestAPI', function ($http) {
        
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
    }
    );

})();


