<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
      <!-- navbar header -->
      <div class="navbar-header bg-info dker">
        <button class="pull-right visible-xs dk" ui-toggle-class="show" data-target=".navbar-collapse">
          <i class="glyphicon glyphicon-cog"></i>
        </button>
        <button class="pull-right visible-xs" ui-toggle-class="off-screen" data-target=".app-aside" ui-scroll-to="app">
          <i class="glyphicon glyphicon-align-justify"></i>
        </button>
        <!-- brand -->
        <a href="#/" class="">
          <img  src="img/log2.png" alt="计算机学院实训过程管理系统">
         
        </a>
        <!-- / brand -->
      </div>
      <!-- / navbar header -->

      <!-- navbar collapse -->
      <div class="collapse pos-rlt navbar-collapse box-shadow bg-info dker">
        <!-- buttons -->
        <div class="nav navbar-nav hidden-xs">
          <a href class="btn no-shadow navbar-btn" ng-click="app.settings.asideFolded = !app.settings.asideFolded">
            <i class="fa {{app.settings.asideFolded ? 'fa-indent' : 'fa-dedent'}} fa-fw"></i>
          </a>
          
          <!-- <a href class="btn no-shadow navbar-btn" ui-toggle-class="show" target="#aside-user">
            <i class="icon-user fa-fw"></i>
          </a> -->
          
        </div>
      	
        <ul class="nav navbar-nav navbar-middle">
        	<li>
        	<!-- 这个链接在中小屏上隐藏，保证header不折行 Li -->
        		<a href="#/" class="hidden-sm">angular 模板</a>
        	</li>
        </ul>
        <!-- / link and dropdown -->      
        <ul class="nav navbar-nav navbar-right t-c">
             

 		 <li class="hidden-xs">
            <a ui-sref="" ><i class="fa fa-book"></i>系统说明书</a>
          </li>
              
               <!-- search form -->
      <c:if test="${user.role!='学生'}" >      
        <form class="navbar-form navbar-form-sm navbar-left shift" ng-controller="ModalSearch">
          <script type="text/ng-template" id="search.html">
            <div ng-include="'tpl/modal.search.html'"></div>
          </script>
          <div class="form-group" id="js-search">
            <div class="input-group">
              <input type="text" ng-model="sname" class="form-control input-sm bg-light no-border rounded padder" placeholder="输入学生姓名...">
              <span class="input-group-btn">
              
              <!-- 去掉了border,上下padding+1,尽量减小右半圆错位问题 style="border: 0; padding-top: 6px; padding-bottom: 6px;" 
                 设置高度，看起来解决了错位。
              -->
                <button ng-click="open('lg',sname)"  class="btn btn-sm bg-light rounded" style="height: 30px; border: 0; padding-top: 6px; padding-bottom: 6px;"><i class="fa fa-search"></i></button>
              </span>
            </div>
          </div>
        </form>
        </c:if>
        <!-- / search form -->         
		    
          <li class="dropdown" dropdown id="js-password">    
            <a href class="dropdown-toggle clear" dropdown-toggle>
            <!-- 下面的span去掉pull-right，解决360浏览器错位 -->
              <span class="thumb-sm avatar m-t-n-sm m-b-n-sm m-l-sm clearfix">
                <img src="img/t2.jpg" alt="..." style="width: 32px; height: 32px;">
                <i class="on md b-white bottom"></i>
              </span>
              <span class="hidden-sm hidden-md ">${role }[${user.username }]</span> <b class="caret"></b>
              
            </a>
            
            <!-- dropdown -->
            <ul class="dropdown-menu animated fadeInRight w">     
              <li class="wrapper b-b m-b-sm bg-light m-t-n-xs">
                <div class="panel-heading center">
                <i class="fa fa-gear text-dark " ></i> 
                 <span class="h4">修改密码</span>
               </div>
              </li>
              <li class="wrapper b-b m-b-sm bg-light m-t-n-xs">
              <div ng-controller="password">
              <form name="form" class="form-group">
                <div class="">
                  <input type="password" class="form-control m-t-xs no-border " name="old" ng-model="old" placeholder="原密码" required > 
                  <input type="password" class="form-control m-t-xs no-border" name="n1" ng-model="n1" placeholder="新密码" required > 
                  <input type="password" class="form-control m-t-xs no-border" name="n2" required ng-model="n2" placeholder="确认密码 " ui-validate=" '$value==n1' " ui-validate-watch=" 'n1' ">
                  <span ng-show='form.n2.$error.validator' mg-if="form.n2.$touched" class="text-danger"> <i class="fa fa-exclamation-triangle "></i>两次不一致</span>         
                </div>          
               <button type="submit" class="btn  btn-sm btn-success btn-block m-t-xs " ng-disabled="form.$invalid" ng-click="change()" aria-disabled="true" disabled="disabled">确认修改</button>
              </form>
           
              </div>
              </li>
            </ul>
            <!-- / dropdown -->
          </li>
          <li>
          <a href="/jsjxyxt/log/logout.do" class="dropdown-toggle" >
              <i class="icon-logout"></i>
              <!-- 去掉了badge-sm和 up类，添加 style="margin-left: 8px"，解决“注销”过高问题 Li-->
              <span class="pull-right-xs" style="margin-left: 8px">注销</span>
            </a>
          </li>
        </ul>
        <!-- / navbar right -->

      </div>
      <!-- / navbar collapse -->
    