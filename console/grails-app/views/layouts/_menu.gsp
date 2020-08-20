<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    
    <section class="sidebar">

        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu" data-widget="tree">
            <sec:ifLoggedIn>
                <li class="header">Site Maintain</li>
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-table"></i> <span>Data Maintain</span>
                        <span class="pull-right-container">
                            <i class="fa fa-angle-left pull-right"></i>
                        </span>
                    </a>
                    <ul class="treeview-menu">
                        <sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_DEV,ROLE_USER'>
                            <li><a href="${request.contextPath}/FastApi"><i class="fa fa-circle-o"></i> FastApi</a></li>
                        </sec:ifAnyGranted>
                    </ul>
                </li>
                <li class="header">System</li>
                <li class="treeview">
                  <a href="#">
                    <i class="fa fa-laptop"></i>
                    <span>System Maintain</span>
                    <span class="pull-right-container">
                      <i class="fa fa-angle-left pull-right"></i>
                    </span>
                  </a>
                  <ul class="treeview-menu">
                    <sec:ifAnyGranted roles='ROLE_ADMIN'>
                        <li><a href="${request.contextPath}/User"><i class="fa fa-circle-o"></i> User Manage</a></li>
                        <li><a href="${request.contextPath}/Role"><i class="fa fa-circle-o"></i> Role Manage</a></li>
                        <li><a href="${request.contextPath}/UserRole"><i class="fa fa-circle-o"></i> User and Role bind</a></li>
                    </sec:ifAnyGranted>
                    <li><a href="${request.contextPath}/logout"><i class="fa fa-circle-o"></i> Logout system</a></li>
                  </ul>
                </li>
            </sec:ifLoggedIn>
            <sec:ifNotLoggedIn>
                <li class="header">System</li>
                <li class="treeview">
                  <a href="#">
                    <i class="fa fa-laptop"></i>
                    <span>Login</span>
                    <span class="pull-right-container">
                      <i class="fa fa-angle-left pull-right"></i>
                    </span>
                  </a>
                  <ul class="treeview-menu">
                    <li><a href="${request.contextPath}/login"><i class="fa fa-circle-o"></i> Login System</a></li>
                  </ul>
                </li>
            </sec:ifNotLoggedIn>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>