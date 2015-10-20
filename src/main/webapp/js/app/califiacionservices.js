(function () {
    var app = angular.module('CalificarEstablecimiento', []);

    app.service('CalificarEstablecimientoRestAPI', function ($http) {
        
        this.califiEst = function ( idCliente, idEnsayo, calificacione, descripcions ) {    
            var ensayo=$http.get('rest/calificacion/ensayo/'+idEnsayo);
            var calificacion={'idCalificacion':0,'ensayo':ensayo,'calificacionBanda':0,'calificacionEstablecimiento':calificacione, 'descripcion':descripcions};
             return $http({
                method: 'POST',
                url: 'rest/calificacion/',data:calificacion
            });            
        };
    }
    );

})();

