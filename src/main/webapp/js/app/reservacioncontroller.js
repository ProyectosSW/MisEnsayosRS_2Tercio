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
        
        this.registroReserva = function (idSala, fecha, duracion) {            
            var sala = $http.get('rest/establecimientos/sala/'+idSala);
            var reserv = {'idReserva':0,"sala":sala,'fecha':fecha,'tiempo':duracion};
            return $http({
                method: 'POST',
                url: 'rest/reservacion/registroReserv',
                data:reserv
            });
        };
        
        this.alquilerCliente = function (idCliente, idReserva, descripcion) {      
            //var cliente = $http.get('/rest/reservacion/cliente/'+idCliente);
            var reserva = $http.get('/rest/reservacion/'+idCliente);
            var alq = {'idAlquiler':0,'ensayo':null,'reservacion':reserva,'tipoDePago':null,'valorMulta':0,'captacion':0};
            return $http({
                method: 'POST',
                url: 'rest/products/',
                data:alq
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

