(function () {
    var app = angular.module('CalificarEstablecimiento', []);

    app.service('CalificarEstablecimientoRestAPI', function ($http) {
        
        this.califiEst = function ( idCliente, idEnsayo, calificacion, descripcion ) {    
             return $http({
                method: 'POST',
                url: 'rest/calificacion/',data:{'idCliente':idCliente,'idEnsayo':idEnsayo, 'calificacion':calificacion, 'descripcion': descripcion}
            });            
        };
    }
    );

})();

