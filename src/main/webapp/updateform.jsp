<%-- 
    Document   : producto.jsp
    Created on : 11 nov 2022, 0:21:15
    Author     : manuc
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="mvm.daw.uf3.Music" %>
<%@ page import="mvm.daw.uf3.MusicService" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.List" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MVC Sample</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    </head>
    <body>

        <%
            out.println(request.getAttribute("single_song"));
            out.println(request.getParameter("id"));

            if (request.getAttribute("single_song") != null) {
                Music song = (Music) request.getAttribute("single_song");
        %>
        <div class="container" style="max-width: 600px;"> 
                    <h1>Update a song</h1>
        <form action="MusicServlet?todo=U&id=<%= song.getId()%>" method="POST">
            <div class="mb-3">
                    <label for="name" class="form-label">Song name:</label>
                    <input type="text" class="form-control" id="name" name="name" value="<%= song.getName()%>" required>
                </div>
                
                <div class="mb-3">
                    <label for="author" class="form-label">Song author:</label>
                    <input type="text" class="form-control" id="author" name="author" value="<%= song.getAuthor()%>" required>
                </div>
                <div class="mb-3">
                    <label for="album" class="form-label">Song album:</label>
                    <input type="text" class="form-control" id="album" name="album" value="<%= song.getAlbum()%>" required>
                </div>
                <div class="mb-3">
                    <label for="price" class="form-label">Song price:</label>
                    <input type="text" class="form-control" id="price" name="price" value="<%= song.getPrice()%>" required>
                </div>
                <div>
                    <label for="customRange2" class="form-label">Song rating</label>
                    <input type="range" class="form-range" min="1" max="5" id="customRange2" name="rating" value="<%= song.getRating()%>">
                </div>
            <input type="submit" class="btn btn-primary" value="update" name="submit">
        </form>            
        <%
            }
        %>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js" integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js" integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ" crossorigin="anonymous"></script>
        </body>
</html>
