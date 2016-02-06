<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.w3.org/1999/xhtml">


<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

    <title>Spring Boot Sample</title>

    <style>
        .table-striped>tbody>tr:nth-child(odd) {
        background-color: #eef;
        }
        .table-striped>tbody>tr:nth-child(even) {
        background-color: #ffe;
        }
    </style>
    <sitemesh:write property='head'/>
</head>

<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <nav class="navbar navbar-default" role="navigation">
                <div class="navbar-header">

                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span
                            class="icon-bar"></span><span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Brand</a>

                </div>

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">


                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="#">Link</a>
                        </li>

                    </ul>
                </div>

            </nav>
        </div>
    </div>

    <div class="row">
        <div class="col-md-3">
            <div class="panel-group" id="panel-475052">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <a class="panel-title" data-toggle="collapse" data-parent="#panel-475052"
                           href="#panel-element-276232">安全管理</a>
                    </div>
                    <div id="panel-element-276232" class="panel-collapse collapse in">
                        <div class="panel-body">
                            <ul>

                                <li><a href="user/add" th:href="@{'/user/add'}">新增用户</a></li>
                                <li><a href="user/list" th:href="@{'/user/list'}">列表用户</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <a class="panel-title collapsed in" data-toggle="collapse" data-parent="#panel-475052"
                           href="#panel-element-274689">系统管理</a>
                    </div>
                    <div id="panel-element-274689" class="panel-collapse collapse">
                        <div class="panel-body">
                            此功能待发掘
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <div class="col-md-9">
             <sitemesh:write property='body'/>

        </div>
    </div>
</div>

<script th:src="@{/js/jquery.min.js}" src="js/jquery.min.js"></script>
<script th:src="@{/js/bootstrap.min.js}" src="js/bootstrap.min.js"></script>
<script th:src="@{/js/scripts.min.js}" src="js/scripts.min.js"></script>

<script>
        jQuery(document).ready(function () {
            jQuery("button[name=deleteById]").click(function() {
                var id = this.value;
                jQuery.ajax({
                    type : 'DELETE',
                    url  : 'delete/' + id,
                }).done(function( msg ) {
                     alert(msg );
                });
            })

             jQuery("#toggleCheck").click(function() {
                jQuery("input[name=ids]").prop('checked', this.checked)
            })

            jQuery("#deleteChoose").click(function() {
                var ids = jQuery('input[name=ids]:checked').map(function(i,n) {
                    return jQuery(n).val();
                }).get();
                jQuery.ajax({
                    type : 'POST',
                    traditional : true,
                    data : {ids : ids},
                    url  : 'deletes',
                }).done(function( msg ) {
                     alert(msg );
                });
            })

        });
</script>
</body>

</html>
