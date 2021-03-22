var app = angular.module('myApp', ['ngFileUpload', 'ui-filters', 'ngRoute', 'ngTouch', 'ui.grid', 'ui.grid.pagination', 'ui.bootstrap']);

app.service('MyService', function($rootScope, $window, $http, $q)
{
    this.setLoadingPage = function (loadingTemplateStatus, loadingTemplateText)
    {
        if (loadingTemplateStatus != 'none')
        {
            $window.scrollTo(0, 0);
        }
        $rootScope.loadingTemplate = loadingTemplateStatus;
        $rootScope.loadingText = loadingTemplateText;
    }
});

app.run(['$window', '$location', '$rootScope', '$http', '$routeParams', 'MyService', function($window, $location, $rootScope, $http, $routeParams, MyService)
{
  
  
}]);