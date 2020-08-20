<header class="main-header">
    <!-- Logo -->
    <a href="${request.contextPath}/login/auth" class="logo">
        <!-- mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-mini"><b>C</b>B</span>
        <!-- logo for regular state and mobile devices -->
        <span class="logo-lg"><b>Console</b>Backend</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
            <span class="sr-only">Toggle navigation</span>
        </a>
        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                <sec:ifLoggedIn>
                    <li class="dropdown user user-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <span class="hidden-xs">${sec.username()}</span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- User image -->
                            <li class="user-header">
                            <g:img uri="${assetPath(src: 'layout/user-default.png')}" class="img-circle"/>
                                <p>
                                    ${sec.username()}
                                    <small>Welcom to console system.</small>
                                </p>
                            </li>
                            <!-- Menu Body -->
                            <li class="user-body">
                            </li>
                            <!-- Menu Footer-->
                            <li class="user-footer">
                                <div class="pull-right">
                                    <a href="${request.contextPath}/logout" class="btn btn-default btn-flat">Loggout</a>
                                </div>
                            </li>
                        </ul>
                    </li>
                </sec:ifLoggedIn>
                <sec:ifNotLoggedIn>
                    <li class="dropdown user user-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <span class="hidden-xs">Guest Please Login</span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- User image -->
                            <li class="user-header">
                            <g:img uri="${assetPath(src: 'layout/user-default.png')}" class="img-circle"/>
                                <p>
                                    Guest
                                    <small>please loggin...</small>
                                </p>
                            </li>
                            <!-- Menu Body -->
                            <li class="user-body">
                            </li>
                            <!-- Menu Footer-->
                            <li class="user-footer">
                                <div class="pull-right">
                                    <a href="${request.contextPath}/login" class="btn btn-default btn-flat">Loggin</a>
                                </div>
                            </li>
                        </ul>
                    </li>
                </sec:ifNotLoggedIn>
            </ul>
        </div>
    </nav>
</header>