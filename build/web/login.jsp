<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TechNova - Iniciar Sesión</title>
        <style>
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }
            
            body {
                font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Helvetica, Arial, sans-serif;
                background: linear-gradient(135deg, #F5F5F5 0%, #FFFFFF 100%);
                min-height: 100vh;
                display: flex;
                justify-content: center;
                align-items: center;
                padding: 20px;
            }
            
            /* Contenedor principal - más ancho para web */
            .login-container {
                background-color: #FFFFFF;
                border-radius: 32px;
                width: 100%;
                max-width: 480px;
                padding: 48px 40px;
                box-shadow: 0 20px 40px rgba(0,0,0,0.08);
                border: 1px solid #EEEEEE;
            }
            
            /* Logo y título */
            .logo {
                text-align: center;
                margin-bottom: 40px;
            }
            
            .logo h1 {
                font-size: 40px;
                font-weight: 700;
                color: #FF8A00;
                letter-spacing: -1px;
                margin-bottom: 12px;
            }
            
            .logo p {
                font-size: 15px;
                color: #777777;
            }
            
            /* Mensaje de bienvenida */
            .welcome-message {
                text-align: center;
                margin-bottom: 32px;
            }
            
            .welcome-message h2 {
                font-size: 24px;
                font-weight: 600;
                color: #333333;
                margin-bottom: 8px;
            }
            
            .welcome-message p {
                font-size: 14px;
                color: #777777;
            }
            
            /* Campos del formulario */
            .form-group {
                margin-bottom: 24px;
            }
            
            .form-group label {
                display: block;
                font-size: 14px;
                font-weight: 600;
                color: #333333;
                margin-bottom: 8px;
            }
            
            .input-wrapper {
                display: flex;
                align-items: center;
                border: 1px solid #E0E0E0;
                border-radius: 16px;
                padding: 14px 18px;
                background-color: #FFFFFF;
                transition: all 0.3s ease;
            }
            
            .input-wrapper:focus-within {
                border-color: #FF8A00;
                box-shadow: 0 0 0 3px rgba(255,138,0,0.1);
            }
            
            .input-icon {
                margin-right: 14px;
                font-size: 20px;
            }
            
            .input-wrapper input {
                flex: 1;
                border: none;
                outline: none;
                font-size: 16px;
                font-family: inherit;
                background: transparent;
            }
            
            .input-wrapper input::placeholder {
                color: #BBBBBB;
            }
            
            /* Opciones: recordar y olvidó contraseña */
            .options-row {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 28px;
                font-size: 14px;
            }
            
            .checkbox-label {
                display: flex;
                align-items: center;
                gap: 8px;
                color: #777777;
                cursor: pointer;
            }
            
            .checkbox-label input {
                width: 18px;
                height: 18px;
                accent-color: #FF8A00;
                cursor: pointer;
            }
            
            .forgot-link {
                color: #FF8A00;
                text-decoration: none;
                font-weight: 500;
                transition: opacity 0.2s;
            }
            
            .forgot-link:hover {
                opacity: 0.8;
                text-decoration: underline;
            }
            
            /* Botón de inicio de sesión */
            .login-btn {
                width: 100%;
                background-color: #FF8A00;
                color: white;
                border: none;
                border-radius: 16px;
                padding: 16px;
                font-size: 16px;
                font-weight: 600;
                font-family: inherit;
                cursor: pointer;
                transition: all 0.3s ease;
                margin-bottom: 28px;
            }
            
            .login-btn:hover {
                background-color: #E07D00;
                transform: translateY(-2px);
                box-shadow: 0 4px 12px rgba(255,138,0,0.3);
            }
            
            .login-btn:active {
                transform: translateY(0);
            }
            
            /* Separador */
            .divider {
                text-align: center;
                margin: 24px 0;
                position: relative;
            }
            
            .divider::before {
                content: "";
                position: absolute;
                top: 50%;
                left: 0;
                right: 0;
                height: 1px;
                background-color: #EEEEEE;
            }
            
            .divider span {
                background-color: #FFFFFF;
                padding: 0 16px;
                position: relative;
                color: #777777;
                font-size: 13px;
            }
            
            /* Enlace de registro */
            .register-link {
                text-align: center;
            }
            
            .register-link a {
                color: #FF8A00;
                text-decoration: none;
                font-weight: 600;
                font-size: 15px;
                transition: opacity 0.2s;
            }
            
            .register-link a:hover {
                opacity: 0.8;
                text-decoration: underline;
            }
            
            .register-link p {
                color: #777777;
                font-size: 13px;
                margin-top: 8px;
            }
            
            /* Mensaje de error */
            .error-message {
                background-color: #FFF3F3;
                color: #E53E3E;
                padding: 14px;
                border-radius: 16px;
                margin-top: 24px;
                text-align: center;
                font-size: 14px;
                border: 1px solid #FFCDCD;
            }
        </style>
    </head>
    <body>
        <div class="login-container">
            <!-- Logo -->
            <div class="logo">
                <h1>TechNova</h1>
                <p>Tecnología que transforma</p>
            </div>
            
            <!-- Mensaje de bienvenida (sin nombre específico) -->
            <div class="welcome-message">
                <h2>Bienvenido</h2>
                <p>Inicia sesión para continuar</p>
            </div>
            
            <!-- Formulario de login -->
            <form action="login" method="POST">
                <!-- Campo de correo electrónico / usuario -->
                <div class="form-group">
                    <label>Correo electrónico</label>
                    <div class="input-wrapper">
                        <span class="input-icon">📧</span>
                        <input type="email" name="usuario" placeholder="tucorreo@ejemplo.com" required>
                    </div>
                </div>
                
                <!-- Campo de contraseña -->
                <div class="form-group">
                    <label>Contraseña</label>
                    <div class="input-wrapper">
                        <span class="input-icon">🔒</span>
                        <input type="password" name="contraseña" placeholder="••••••••" required>
                    </div>
                </div>
                
                <!-- Opciones: recordar y olvidó contraseña -->
                <div class="options-row">
                    <label class="checkbox-label">
                        <input type="checkbox" name="recordar"> Recordar sesión
                    </label>
                    <a href="#" class="forgot-link">¿Olvidaste tu contraseña?</a>
                </div>
                
                <!-- Botón de inicio de sesión -->
                <button type="submit" class="login-btn">Iniciar sesión</button>
            </form>
            
            <!-- Separador -->
            <div class="divider">
                <span>¿No tienes cuenta?</span>
            </div>
            
            <!-- Enlace de registro -->
            <div class="register-link">
                <a href="#">Crear cuenta nueva</a>
                <p>Regístrate y empieza a comprar</p>
            </div>
            
            <!-- Mensaje de error (si existe) -->
            <% if (request.getAttribute("error") != null) { %>
                <div class="error-message">
                    <%= request.getAttribute("error") %>
                </div>
            <% } %>
        </div>
    </body>
</html>