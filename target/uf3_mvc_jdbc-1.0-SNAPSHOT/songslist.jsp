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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MVC Sample</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    </head>
    <body>
        <%
            //out.println(request.getAttribute("todo"));
            if (request.getAttribute("todo") == null) {
                if (request.getAttribute("single_song") != null) {
                    Music song = (Music) request.getAttribute("single_song");
        %>

        <div class="container" style="max-width: 600px;"> 
            <h1>Song Details</h1>

            <div class="card" >
                <div class="card-body">
                    <div class="float-end">ID: <%= song.getId()%></div>
                    <h5 class="card-title">Name: <%= song.getName()%></h5>
                    <h6 class="card-subtitle mb-2 text-body-secondary">Author: <%= song.getAuthor()%></h6>
                    <p class="card-text">Album: <%= song.getAlbum()%></p>
                    <p class="card-text">Price: $ <%= new DecimalFormat("#0.00").format(song.getPrice())%></p>
                    <div class="progress">
                        <div class="progress-bar" role="progressbar"  style="width: <%=song.getRating() * 20%>%" aria-valuenow="<%=song.getRating()%>" aria-valuemin="0" aria-valuemax="5"><%=song.getRating()%>/5</div>
                    </div>

                </div>
            </div>

            <br>
            <div><a class="btn btn-warning" href="MusicServlet?todo=U&id=<%= song.getId()%>">Update</a> </div><br>
            <div><a class="btn btn-danger" href="MusicServlet?todo=D&id=<%= song.getId()%>">Delete</a> </div><br>
            <div><a class="btn btn-primary" href="MusicServlet">Go Back</a></div>
        </div>
        <% } else { %>
        <div class="container" style="max-width: 600px;"> 
            <h1>Music List</h1>

            <ul class="list-group">
                <%
                    List<Music> songsList = (List<Music>) request.getAttribute("songs_list");
                    for (Music song : songsList) {
                %>
                <li class="list-group-item">
                    <%= song.getName()%>
                    <a href="MusicServlet?id=<%= song.getId()%>" class="float-end btn btn-info">Details...</a>
                </li>
                <% } %>
            </ul>
            <br>
            <a href="MusicServlet?todo=C" class="btn btn-primary">+ Add song</a>
            <a href="MusicServlet?todo=F" class="btn btn-primary">+ Search</a>
            <a href="MusicServlet" class="btn btn-primary"> - See all</a>
        </div>
        <%      }
        } else {
            if (request.getAttribute("todo") == "C") { %>
        <div class="container" style="max-width: 600px;"> 
            <h1>Add new song</h1>
            <form action="MusicServlet" method="POST">
                Song name <input type="text" name="name"/><br>    
                Song author <input type="text" name="author"/><br>    
                Song album <input type="text" name="album"/><br>    
                Price <input type="text" name="price"/><br>    
            </form>    
        </div>
        <%            }

            }
        %>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js" integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js" integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ" crossorigin="anonymous"></script>
    </body>
</html>
