<%-- 
    Document   : index
    Created on : 04.11.2017, 15:03:13
    Author     : N3asu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	 crossorigin="anonymous">

	<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
	 crossorigin="anonymous"></script>

	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	 crossorigin="anonymous"></script>

	<!-- FontAwesome -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
        
        <link href="https://fonts.googleapis.com/css?family=Fira+Sans" rel="stylesheet"> 

	<script src="assets/js/script.js"></script>
	<link rel="stylesheet" href="assets/css/style.css" />
        
        <title>JSP Page</title>
    </head>
    <body>
        <nav>
            
        </nav>
        <main>
            <div class="container">
                <div class="col-md-6"></div>
                <div class="col-md-6 panel panel-default">
                    <form class="panel-body">
                        <div class="form-group row">
                            <div class="col-xs-6">
                                <input class="form-control" name="serie" type="text" placeholder="Serie" />
                            </div>
                            <div class="col-xs-3">
                                <input class="form-control" name="nummer" type="number" placeholder="Nummer" min="1" />
                            </div>
                            <div class="col-xs-3">
                                <input class="form-control" name="jahr" type="number" placeholder="Jahr" min="1" minlength="4" />
                            </div>
                        </div>
                        <div class="form-group row">
                            <input class="form-control" name="titel" type="text" placeholder="Titel" />
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-6">
                                <input class="form-control" name="zeichner" type="text" placeholder="Zeichner" />
                            </div>
                            <div class="col-xs-6">
                                <input class="form-control" name="texter" type="text" placeholder="Texter" />
                            </div>
                        </div>
                        <div class="row">
                            <button type="button" class="btn btn-default" onclick="newComic()">Comic anlegen</button>
                        </div>
                    </form>
                </div>
            </div>
        </main>
        <footer>
            
        </footer>
    </body>
</html>
