<!DOCTYPE html>
<html>
<#include "../common/header.ftlh">

<body>

<div id="wrapper" class="toggled">
    <#include "../common/nav.ftlh">

    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">

                <div class="col-md-4 column">
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>Order Id</th>
                            <th>Order Amount</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${orderDTO.getOrderId()}</td>
                            <td>${orderDTO.getOrderAmount()}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <div class="col-md-12 column">
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>Product Id</th>
                            <th>Product Name</th>
                            <th>Product Price</th>
                            <th>Qty</th>
                            <th>Total price</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list orderDTO.orderDetailList as orderDetail>
                            <tr>
                                <td>${orderDetail.productId}</td>
                                <td>${orderDetail.productName}</td>
                                <td>${orderDetail.productPrice}</td>
                                <td>${orderDetail.productQuantity}</td>
                                <td>${orderDetail.productPrice * orderDetail.productQuantity}</td>
                            </tr>
                        </#list>

                        </tbody>
                    </table>

                    <#if orderDTO.getOrderStatusEnum().getMsg() == "new order">
                        <a href="/sell/seller/order/finish?orderId=${orderDTO.orderId}" type="button" class="btn btn-default btn-primary">Finish order</a>
                        <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}" type="button" class="btn btn-default btn-danger">Cancel order</a>
                    </#if>

                </div>
            </div>
        </div>
    </div>
</div>





</body>
</html>