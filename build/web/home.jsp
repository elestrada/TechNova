<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("usuario") == null) {
        response.sendRedirect("login.jsp");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Bienvenido - TechNova</title>
    <style>
        body {
            font-family: Arial;
            background: #f0f2f5;
            margin: 0;
            padding: 0;
        }
        .navbar {
            background: #667eea;
            color: white;
            padding: 15px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .container {
            padding: 20px;
        }
        .logout-btn {
            background: #e53e3e;
            color: white;
            padding: 8px 15px;
            text-decoration: none;
            border-radius: 5px;
        }
        .logout-btn:hover {
            background: #c53030;
        }
    </style>
</head>
<body>
    <div class="navbar">
        <h2>TechNova</h2>
        <a href="logout" class="logout-btn">Cerrar Sesión</a>
    </div>
    
    <div class="container">
        <h1>¡Bienvenido, <%= session.getAttribute("usuario") %>!</h1>
        <p>Has iniciado sesión correctamente en el sistema TechNova.</p>
    </div>
</body>
</html>