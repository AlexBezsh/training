angular.module("registration_form",[])
    .controller("AppCtrl", function ($scope, $http) {
        $scope.newUser = {};
        $scope.sendForm = function(newUser){
            $http({
                method: "POST",
                url: "/api/reg_form",
                data: $.param(newUser),
                headers: { "Content-Type" : "application/x-www-form-urlencoded" }
            }).then(
                function(data) {
                    window.alert("Registered successfully");
                },
                function(error) {
                    window.alert("Error occurred during registration");
                }
            );
        }
    });