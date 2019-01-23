<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.sql.*,java.lang.*,java.util.Date,table.*,connexion.*,fonction.*,html.*,forfait.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/all.min.css">
    <link rel="stylesheet" href="design.css">
    <script src="js/all.min.js"></script>
    <title>Jirama</title>
</head>
<body>
<div class="container-fluid">
<!--navbar -->
<nav class="navbar navbar-expand-lg navbar-light nav nav-tabs nav-border-color nav-info
">
  <a class="navbar-brand" href="offres.jsp">Jirama</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarText">
    <ul class="navbar-nav mr-auto">
	<li class="nav-item">
		<a class="nav-link" href="clients.jsp">Les clients </a>
    </li>
	<li class="nav-item">
		<a class="nav-link" href="tranche.jsp">Tranche individuelle </a>
    </li>
	<li class="nav-item">
		<a class="nav-link" href="offres.jsp">Offres prépayées</a>
    </li>
    <li class="nav-item">
		<a class="nav-link" href="index.jsp">Nouveau prélèvement</a>
    </li>
	<li class="nav-item">
		<a class="nav-link" href="facture.jsp">Prélèvements à facturer</a>
    </li>
	<li class="nav-item">
		<a class="nav-link" href="annuler.jsp">Annuler factures</a>
    </li>
	<!--<li class="nav-item">
		<a class="nav-link" href="nouveau.jsp">Prélèvements annulés</a>
    </li>-->
    </ul>
    <span class="navbar-text">
      Projet s3
    </span>
  </div>
</nav>
<!--navbar -->

</br>
