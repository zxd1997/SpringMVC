<%--
  Created by IntelliJ IDEA.
  User: zxd19
  Date: 2018/06/08
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
    DIV.flickr {
        PADDING-RIGHT: 3px;
        PADDING-LEFT: 3px;
        PADDING-BOTTOM: 3px;
        MARGIN: 3px;
        PADDING-TOP: 3px;
        TEXT-ALIGN: center
    }

    DIV.flickr A {
        BORDER-RIGHT: #dedfde 1px solid;
        PADDING-RIGHT: 6px;
        BACKGROUND-POSITION: 50% bottom;
        BORDER-TOP: #dedfde 1px solid;
        PADDING-LEFT: 6px;
        PADDING-BOTTOM: 2px;
        BORDER-LEFT: #dedfde 1px solid;
        COLOR: #0061de;
        MARGIN-RIGHT: 3px;
        PADDING-TOP: 2px;
        BORDER-BOTTOM: #dedfde 1px solid;
        TEXT-DECORATION: none
    }

    DIV.flickr A:hover {
        BORDER-RIGHT: #000 1px solid;
        BORDER-TOP: #000 1px solid;
        BACKGROUND-IMAGE: none;
        BORDER-LEFT: #000 1px solid;
        COLOR: #fff;
        BORDER-BOTTOM: #000 1px solid;
        BACKGROUND-COLOR: #0061de
    }

    DIV.meneame A:active {
        BORDER-RIGHT: #000 1px solid;
        BORDER-TOP: #000 1px solid;
        BACKGROUND-IMAGE: none;
        BORDER-LEFT: #000 1px solid;
        COLOR: #fff;
        BORDER-BOTTOM: #000 1px solid;
        BACKGROUND-COLOR: #0061de
    }

    DIV.flickr SPAN.current {
        PADDING-RIGHT: 6px;
        PADDING-LEFT: 6px;
        FONT-WEIGHT: bold;
        PADDING-BOTTOM: 2px;
        COLOR: #ff0084;
        MARGIN-RIGHT: 3px;
        PADDING-TOP: 2px
    }

    DIV.flickr SPAN.disabled {
        PADDING-RIGHT: 6px;
        PADDING-LEFT: 6px;
        PADDING-BOTTOM: 2px;
        COLOR: #adaaad;
        MARGIN-RIGHT: 3px;
        PADDING-TOP: 2px
    }

</style>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 align="center">Comments</h1>
<hr>
<h3>1.</h3><a href="add.jsp">Leave Comment</a>
<hr>
<h3>2.</h3><a href="/find?page=1">View Comments</a>
<hr>
<c:forEach items="${list}" var="m">
    <h3>id:${m.id}</h3>
    <h3>Name:${m.name}</h3>
    <h3>Title:${m.title}</h3>
    <h3>Time:${m.time}</h3>
    <h3>Content:${m.content}</h3>
    <h3><a href="/delete?id=${m.id}&page=${page.page}">Delete</a></h3>
</c:forEach>

<div class="flickr">
    <c:choose>
        <c:when test="${page.page==1}">
            <span class="disabled">首页</span>
            <span class="disabled">上一页</span>
        </c:when>
        <c:otherwise>
            <span class="nextpage"><a href="/find?page=1">首页</a></span>
            <span class="nextpage"><a href="/find?page=${page.page-1}">上一页</a></span>
        </c:otherwise>
    </c:choose>
    <c:forEach begin="${page.startPage}" end="${page.endPage}" var="i">
        <c:choose>
            <c:when test="${page.page==i}">
                <span class="current">${i}</span>
            </c:when>
            <c:otherwise>
                <span><a href="/find?page=${i}">${i}</a></span>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:choose>
        <c:when test="${page.page==page.maxPage}">
            <span class="disabled">下一页</span>
            <span class="disabled">尾页</span>
        </c:when>
        <c:otherwise>
            <span class="nextpage"><a href="/find?page=${page.page+1}">下一页</a></span>
            <span class="nextpage"><a href="/find?page=${page.maxPage}">尾页</a></span>
        </c:otherwise>
    </c:choose>
    <br>
    <form name="page" action="/change" method="post">
        <span class="A">更改单页显示条数</span>
        <input type="number" name="num">
        <span class="A"><input type="submit" value="更改"></span>
    </form>
</div>
</body>
</html>
