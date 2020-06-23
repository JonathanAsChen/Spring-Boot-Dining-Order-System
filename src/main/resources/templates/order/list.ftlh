<html>
    <#include "../common/header.ftlh">

    <body>

    <div id="wrapper" class="toggled">
<#--        sidebar-->
        <#include "../common/nav.ftlh">
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>Order ID</th>
                                <th>Name</th>
                                <th>Phone</th>
                                <th>Address</th>
                                <th>Amount</th>
                                <th>Order Status</th>
                                <th>Pay Status</th>
                                <th>Create Time</th>
                                <th colspan="2">Operation</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list orderDTOPage.content as orderDTO>
                                <tr>
                                    <td>${orderDTO.orderId}</td>
                                    <td>${orderDTO.buyerName}</td>
                                    <td>${orderDTO.buyerPhone}</td>
                                    <td>${orderDTO.buyerAddress}</td>
                                    <td>${orderDTO.orderAmount}</td>
                                    <td>${orderDTO.getOrderStatusEnum().getMsg()}</td>
                                    <td>${orderDTO.getPayStatusEnum().getMsg()}</td>
                                    <td>${orderDTO.createTime}</td>
                                    <td><a href="/sell/seller/order/detail/${orderDTO.getOrderId()}">Detail</a></td>
                                    <td>
                                        <#if orderDTO.getOrderStatusEnum().getMsg() == "new order">
                                            <a href="/sell/seller/order/cancel?orderId=${orderDTO.getOrderId()}">
                                                Cancel
                                            </a>
                                        </#if>
                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                        <#-- page -->
                        <div class="col-md-12 column">
                            <ul class="pagination pull-right">
                                <#if currentPage lte 1>
                                    <li class="disabled"><a href="#">Prev</a></li>
                                <#else>
                                    <li><a href="/sell/seller/order/list?page=${currentPage - 1}&size=${pageSize}">Prev</a></li>
                                </#if>

                                <#assign leftDot = false, rightDot = false>
                                <#list 1..orderDTOPage.getTotalPages() as index>

                                    <#if currentPage == index>
                                        <li class="disabled " style="fill: orange"><a href="/sell/seller/order/list?page=${index}&size=${pageSize}">${index}</a></li>

                                    <#elseif (currentPage - index)?abs lt 3 || index == orderDTOPage.getTotalPages() || index == 1>
                                        <li><a href="/sell/seller/order/list?page=${index}&size=${pageSize}">${index}</a></li>
                                    <#elseif index lte currentPage - 3 && leftDot == false>
                                        <li class="disabled"><a>...</a></li>
                                        <#assign leftDot = true>
                                    <#elseif index gte currentPage + 3 && rightDot == false>
                                        <li class="disabled"><a>...</a></li>
                                        <#assign rightDot = true>

                                    </#if>



                                </#list>

                                <#if currentPage gte orderDTOPage.getTotalPages()>
                                    <li class="disabled"><a href="#">Next</a></li>
                                <#else>
                                    <li><a href="/sell/seller/order/list?page=${currentPage + 1}&size=${pageSize}">Next</a></li>
                                </#if>
                            </ul>
                        </div>

                    </div>
                </div>
            </div>
        </div>

    </div>



    </body>
</html>

