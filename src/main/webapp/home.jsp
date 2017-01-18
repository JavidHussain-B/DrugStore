<!doctype html>
<html ng-app="myApp">
   <head>
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <!-- Bootstrap version 3.3.7 -->
      <link rel="stylesheet" href="./resources/css/bootstrap.min.css">
      <link rel="stylesheet" href="./resources/css/app.css">
      <!-- Jquery version 3.1.1 -->
      <script src="./resources/js-libraries/jquery.min.js"></script>
      <!-- Bootstrap version 3.3.7 -->
      <script src="./resources/js-libraries/bootstrap.min.js"></script>
      <!-- Angular Js version 1.4.8 -->
      <script src="./resources/js-libraries/angular.min.js"></script>
      <!-- Root Application Js -->
      <script src="./resources/js/main.js"></script>
      <script src="./resources/controller/mainController.js"></script>
   </head>
   <body ng-controller="mainController" style=" background: {{color}}">
      <div class="container">
         <div class="alert" style="position: absolute;">
            <a href="#" class="close">&times;</a>{{alertMsg}}
         </div>
         
         <section>
                      
            <div class="row">
               <div class="col-sm-6 col-sm-6">
            		<button type="submit" class="btn btn-info" ng-click="authenticate()">Login</button>
            		<button type="submit" class="btn btn-success" ng-click="signUp()">Sign Up</button>
               </div>
            </div>
         </section>
      </div>
   </body>
</html>