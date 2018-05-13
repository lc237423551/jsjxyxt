<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %> 
<div class="hbox hbox-auto-xs hbox-auto-sm">
  <div class="col">
    <div class="clearfix padder-md">
      <div id="count" class="wrapper">
      <c:if test="${role=='学生'}">
     	 <iframe  width=100% height=600  scrolling='no' frameborder='0' src='/jsjxyxt/src/pdf/student.pdf' ></iframe>
      </c:if>
      <c:if test="${role=='辅导员'}">
     	 <iframe  width=100% height=600  scrolling='no' frameborder='0' src='/jsjxyxt/src/pdf/fdy.pdf' ></iframe>
      </c:if>
      <c:if test="${role=='教师'}">
     	 <iframe  width=100% height=600  scrolling='no' frameborder='0' src='/jsjxyxt/src/pdf/teacher.pdf' ></iframe>
      </c:if>
      <c:if test="${role=='班级负责人'}">
     	 <iframe  width=100% height=600  scrolling='no' frameborder='0' src='/jsjxyxt/src/pdf/teacher_cla.pdf' ></iframe>
      </c:if>
      <c:if test="${role=='专业负责人'}">
     	 <iframe  width=100% height=600  scrolling='no' frameborder='0' src='/jsjxyxt/src/pdf/teacher_spec.pdf' ></iframe>
      </c:if>
       <c:if test="${role=='专业班级负责人'}">
     	 <iframe  width=100% height=600  scrolling='no' frameborder='0' src='/jsjxyxt/src/pdf/teacher_cla_spec.pdf' ></iframe>
      </c:if>
      <c:if test="${role=='管理员'}">
     	 <iframe  width=100% height=600  scrolling='no' frameborder='0' src='/jsjxyxt/src/pdf/admin.pdf' ></iframe>
      </c:if>
      </div>
    </div>
  </div>
</div>