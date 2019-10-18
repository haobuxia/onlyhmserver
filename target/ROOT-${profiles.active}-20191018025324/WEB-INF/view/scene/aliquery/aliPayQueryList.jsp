<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


            <table class="table table-striped">
                <thead>
                <tr>
                    <th>序号</th>
                    <th style="width:360px">系统单号</th>
                    <th style="width:260px">支付宝单号</th>
                    <th style="width:160px">天远单号</th>
                    <th>交易类型</th>
                    <th>收款人</th>
                    <th>买家账号</th>
                    <th>订单金额</th>
                    <th>实收金额</th>
                    <th>付款金额</th>
                    <th>发票金额</th>
                    <th>支付时间</th>
                </tr>
                </thead>
                <tbody id="payListBody">
                <c:forEach var="pay" items="${vo.list}" varStatus="status">
                    <tr>
                        <td>${status.count}</td>
                        <td><span title="${pay.outTradeNo}">${pay.outTradeNo}</span></td>
                        <td><span title="${pay.tradeNo}">${pay.tradeNo}</span></td>
                        <td><span title="${pay.tyTradeNo}">${pay.tyTradeNo}</span></td>
                        <td data-tyTradeType>${pay.tyTradeType}</td>
                        <td data-oprtId="${pay.tyOprtId}">${pay.tyOprtName}</td>
                        <td data-buyerUserId = "${pay.buyerUserId}">${pay.buyerLogonId}</td>
                        <td>${pay.totalAmount}</td>
                        <td>${pay.receiptAmount}</td>
                        <td>${pay.buyerPayAmount}</td>
                        <td>${pay.invoiceAmount}</td>
                        <td><fmt:formatDate type="both" value="${pay.gmtPayment}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
 
    <jsp:include page="../../include/new-page-pager.jsp"></jsp:include>

<script>
    $(function () {
        var tds = $("#payListBody td[data-tyTradeType]");
        $.each(tds,function(idx,td){
            var txt = $(td).text();
            var val = transTyTradeType(txt);
            $(td).text(val);
        });
    })
</script>