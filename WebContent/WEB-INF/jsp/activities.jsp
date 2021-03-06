<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">行動記録</h1>
</div>
<div class="table-responsive">
	<table class="table table-striped table-sm">
		<thead>
			<tr>
				<th>日付</th>
				<th>時刻</th>
				<th>場所</th>
				<th>理由</th>
				<th>備考</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${actList}" var="act">
				<tr>
					<td><c:out value="${act.date}" /></td>
					<td><c:out value="${act.stime } - ${act.etime}" /></td>
					<td><c:out value="${act.place}" /></td>
					<td><c:out value="${act.reason}" /></td>
					<td><c:out value="${act.remarks}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
