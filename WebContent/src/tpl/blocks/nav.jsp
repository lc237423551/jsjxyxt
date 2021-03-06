<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
           <%@page import="com.java.entity.User" %>


<ul class="nav">
  <li ng-class="{active:$state.includes('app')|| $state.includes('apps')}" id="js-shixun"><!-- 实训 -->
    <a href class="auto">
      <span class="pull-right text-muted">
        <i class="fa fa-fw fa-angle-right text"></i>
        <i class="fa fa-fw fa-angle-down text-active"></i>
      </span>
      <i class="glyphicon glyphicon-home text-success"></i>
      
      <span class="font-bold">一级菜单</span>
    </a>
    <ul class="nav nav-sub dk"> <!-- 实训列表 -->
    
    <!-- 学生 -->
    
	   <c:if test="${user.role=='学生'}" var="condition" scope="page">
	     <li ui-sref-active="active">
	     	<a ui-sref="app.buttons">
	     	<i class="icon-compass"></i>
	     		<span>二级菜单</span>
	     	</a>
	     </li>
		 <li ui-sref-active="active">
	     	<a ui-sref="app.icons">
	     	<i class="fa fa-users"></i>
	     		<span>二级菜单</span>
	     	</a>
	     </li>
	     <li ui-sref-active="active">
	     	<a ui-sref="app.grid">
	     	<i class="fa fa-users"></i>
	     		<span>二级菜单grid</span>
	     	</a>
	     </li>
	     <li ui-sref-active="active">
	     	<a ui-sref="app.widgets">
	     	<i class="fa fa-users"></i>
	     		<span>二级菜单widget</span>
	     	</a>
	     </li>
	     <li ui-sref-active="active">
	     	<a ui-sref="app.bootstrap">
	     	<i class="fa fa-users"></i>
	     		<span>二级菜单bootstrap</span>
	     	</a>
	     </li>

	    </c:if>
	    <!-- 学生结束 -->
	    
	    <!-- 管理员 -->
	    
	    <c:if test="${user.role=='管理员'||user.role=='辅导员'}" var="condition" scope="page">
	     <li ui-sref-active="active">
	     	<a ui-sref="app.buttons">
	     	<i class="icon-compass"></i>
	     		<span>二级菜单</span>
	     	</a>
	     </li>
	     <li ui-sref-active="active">
	     	<a ui-sref="app.icons">
	     	<i class="fa fa-users"></i>
	     		<span>二级菜单</span>
	     	</a>
	     </li>
	    </c:if>
	    <!-- 管理员结束 -->
	    
	    <!-- 教师 -->
	    
	    <c:if test="${user.role=='教师'}" var="condition" scope="page">
	    <!-- 专业负责人 -->
	      <c:if test="${role=='专业负责人'||role=='专业班级负责人'}" var="condition" scope="page">
		    <li ng-class="{active:$state.includes('app.specMasterTeacher')}">
		    <a href class="auto">
		      <span class="pull-right text-muted">
		        <i class="fa fa-fw fa-angle-right text"></i>
		        <i class="fa fa-fw fa-angle-down text-active"></i>
		      </span>
		      <i class="glyphicon glyphicon-th "></i>
		      <span class="font-bold">专业</span>
		    </a>
		    <ul class="nav nav-sub dk"> 
		    <li ui-sref-active="active">
		        <a ui-sref="app.specMasterTeacher.trainSubject">
		      <i class="fa fa-thumb-tack"></i>
		          <span>实训题目</span> 
		        </a>
		      </li>
		      <li ui-sref-active="active">
		       <a ui-sref="app.specMasterTeacher.trainManagerLeader">
		        <i class="fa fa-flag"></i>
		          <span>实训分配</span> 
		        </a>
		      </li>
		      
		      <li ui-sref-active="active">
		     	<a ui-sref="app.specMasterTeacher.trainCompanyState">
		     	<i class="icon-compass"></i>
		     		<span>实训单位情况</span>
		     	</a>
		     </li>
		     <li ui-sref-active="active">
		     	<a ui-sref="app.specMasterTeacher.trainStudentState">
		     	<i class="fa fa-users"></i>
		     		<span>实训学生情况</span>
		     	</a>
		     </li>
		     <li ui-sref-active="active">
		     	<a ui-sref="app.specMasterTeacher.applyStateSpecMaster">
		     	<i class="fa fa-pencil-square-o"></i>
		     		<span>实训审批</span>
		     	</a>
		     </li>
		     
		     </ul>
		     </li>
	     </c:if>
	     <!-- 专业负责人结束 -->
	     
	     <!-- 班级负责人 -->
	      <c:if test="${role=='班级负责人'||role=='专业班级负责人'}" var="condition" scope="page">
		    <li ng-class="{active:$state.includes('app.classMasterTeacher')}">
		    <a href class="auto">
		      <span class="pull-right text-muted">
		        <i class="fa fa-fw fa-angle-right text"></i>
		        <i class="fa fa-fw fa-angle-down text-active"></i>
		      </span>
		      <i class="glyphicon glyphicon-th-large"></i>
		      <span class="font-bold">班级</span>
		    </a>
		    <ul class="nav nav-sub dk "> 
		     <li ui-sref-active="active">
		        <a ui-sref="app.classMasterTeacher.trainStudentStateClass">
		        <i class="fa fa-users"></i>
		          <span>学生实训情况</span>
		        </a>
		      </li>
		    <li ui-sref-active="active">
		        <a ui-sref="app.classMasterTeacher.practicePdf">
		        <i class="fa fa-file-pdf-o"></i>
		          <span>实训报告管理</span> 
		        </a>
		      </li>
		    <li ui-sref-active="active">
		        <a ui-sref="app.classMasterTeacher.outputWeekSum">
		        <i class="fa fa-cloud-download"></i>
		          <span>导出其他文档</span> 
		        </a>
		      </li>
		      
		    <!--    <li ui-sref-active="active">
		        <a ui-sref="app.classMasterTeacher.trainCompanyState">
		        <i class="icon-compass"></i>
		          <span>实训单位情况</span> 
		        </a>
		      </li>-->
		       
		     
		     </ul>
		    <li>
		    </c:if>
	    <!-- 班级负责人结束 -->
	    <!-- 实训指导教师 -->
	     <c:if test="${user.role=='教师'}" var="condition" scope="page">
		    <li ng-class="{active:$state.includes('apps') || $state.includes('app.trainMasterTeacher')}">
		    <a href class="auto">
		      <span class="pull-right text-muted">
		        <i class="fa fa-fw fa-angle-right text"></i>
		        <i class="fa fa-fw fa-angle-down text-active"></i>
		      </span>
		      <i class="glyphicon glyphicon-star"></i>
		      <span class="font-bold">实训</span>
		    </a>
		    <ul class="nav nav-sub dk"> 
		      <li ui-sref-active="active">
		        <a ui-sref="apps.contact">
		         <i class="fa fa-file-word-o"></i>
		          <span>周总结管理</span>
		        </a>
		      </li>
		    <!--   <li ui-sref-active="active">
		        <a ui-sref="apps.renwushu">
		        <i class="glyphicon glyphicon-eye-open"></i>
		       
		          <span>编辑任务书</span>
		        </a>
		      </li> -->
		      
		       <li ui-sref-active="active">
		     	<a ui-sref="app.trainMasterTeacher.applyStateTeacher">
		     	<i class="fa fa-pencil-square-o"></i>
		     		<span>实训审批</span>
		     	</a>
		     </li>
		     </ul>
		    <li> 
	    </c:if><!-- 实训指导教师结束 -->
	      <li ui-sref-active="active">
		        <a ui-sref="app.history">
		        <i class="fa fa-history"></i>
		          <span>历史管理</span>
		        </a>
		      </li>
	   </c:if><!-- 教师结束 -->
    </ul><!-- 实训列表结束 -->
  </li><!-- 实训结束 -->


<!-- 基本信息管理 -->

 

<c:if test="${user.role=='管理员'}" var="condition" scope="page">
	<li ng-class="{active:$state.includes('app.baseMsg')}" id="js-xibenxinxi"><!-- 实训 -->
	    <a href class="auto">
	      <span class="pull-right text-muted">
	        <i class="fa fa-fw fa-angle-right text"></i>
	        <i class="fa fa-fw fa-angle-down text-active"></i>
	      </span>
	       
	      <i class="glyphicon glyphicon-wrench text-success"></i> 
	      <span class="font-bold">增删改查模板</span>
	    </a>
	    <ul class="nav nav-sub dk"> <!-- 实训列表 -->
	 	
	      <li ui-sref-active="active">
	        <a ui-sref="app.baseMsg.managerStu">
	        <i class="glyphicon glyphicon-edit"></i>
	          <span>学生管理</span> 
	        </a>
	      </li>
	      <li ui-sref-active="active">
	        <a ui-sref="app.baseMsg.managerTea">
	         <i class="glyphicon glyphicon-edit"></i>
	          <span>教师管理</span>
	        </a>
	      </li>
	   </ul>
	  </li>
 </c:if> 
  <c:if test="${user.role=='教师'}" var="condition" scope="page">
  <li ui-sref-active="active">
        <a ui-sref="app.personal.teacher">
         <i class="glyphicon glyphicon-user"></i>
          <span>教师个人信息</span>
        </a>
      </li>
  </c:if>



<c:if test="${user.role=='管理员'}" var="condition" scope="page">
      
      <li ui-sref-active="active">
        <a ui-sref="app.baseMsg.init_page">
      <i class="fa fa-refresh"></i>
          <span>系统初始化</span> 
        </a>
      </li>

 </c:if> 
 
<c:if test="${role=='辅导员'}" var="condition" scope="page">
	<li ui-sref-active="active">
        <a ui-sref="app.Counselor.shixunCompany">
         <i class="glyphicon glyphicon-edit"></i>
          <span>实训单位管理</span> 
        </a>
      </li>
	<li ui-sref-active="active">
        <a ui-sref="app.Counselor.checkCompany">
      <i class="fa fa-check-square"></i>
          <span>实训单位审核</span> 
        </a>
      </li>
      <li ui-sref-active="active">
        <a ui-sref="app.Counselor.apply">
       <i class="glyphicon glyphicon-edit"></i>
          <span>请假审批</span> 
        </a>
      </li>
      
 </c:if> 
  
</ul>
<!-- / list -->
      <div class="line dk hidden-folded"></div>

<div class="clearfix hidden-xs text-center  hide show js-weeknum"   id="aside-user" >
 <c:if test="${weeknum==0}" >
   <span class="h4 text-danger">实训未开始</span>
 </c:if>
  <c:if test="${weeknum!=0}" >
   <span class="h4">第</span><br>
        <span style="font-size:60px">${weeknum }</span><br>
         <span class="h4">周</span><br>
 </c:if>
     
    </div>
  <div class="line dk hidden-folded"></div>