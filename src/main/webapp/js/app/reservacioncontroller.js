/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function () {
    var app = angular.module('ReservacionController', []);

    app.service('ProductsRestAPI', function ($http) {
        
        this.reservasCliente = function (clienteid) {            
            return $http({
                method: 'GET',
                url: 'rest/reservacion/clienteReserva/cliente'+clienteid
            });            
        };
        
        this.registroReserva = function (idEstablecimiento , idSala, fecha, duracion) {            
            return $http({
                method: 'POST',
                url: 'rest/products/',
                data:{'idEstablecimiento':idEstablecimiento,'idSala':idSala,'fecha':fecha, 'duracion':duracion}
            });            
        };
        
        this.alquilerCliente = function (idCliente, idReserva, descripcion) {            
            return $http({
                method: 'POST',
                url: 'rest/products/',
                data:{'idCliente':idCliente,'idReserva':idReserva,'descripcion':descripcion}
            });            
        };
        
        this.pagar = function (idAlquiler, monto, numtarjeta, tipoP) {            
            return $http({
                method: 'POST',
                url: 'rest/products/',
                data:{'idAlquiler':idAlquiler,'monto':monto,'numtarjeta':tipoP}
            });            
        };
               

    }
    );

})();

